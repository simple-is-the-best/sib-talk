package com.sib.message.adapters.out.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.message.application.port.out.cache.ChannelCachePort;
import com.sib.message.application.port.out.cache.MessageCachePort;
import com.sib.message.application.port.out.cache.PublishCachePort;
import com.sib.utils.generator.RedisConst;
import com.sib.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sib.utils.ObjectValidator.isNil;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheAdapter implements ChannelCachePort, PublishCachePort, MessageCachePort {
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper mapper;

    @Override
    public void addMessage(Long channelId, ChatMessage message) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        try {
            String rawMessage = mapper.writeValueAsString(message);
            zSet.add(RedisConst.ChannelKey(channelId), rawMessage, RedisConst.ScoreOfTime());
        } catch (Exception e) {
            log.error("Redis SetMessage Failure::ChannelId:{}::Message:{}::Error Message:{}",
                    channelId, message, e.getMessage());
        }
    }

    @Override
    public void publish(String topic, Object message) {
        redisTemplate.convertAndSend(topic, message);
    }

    @Override
    public List<ChatMessage> loadAllByChannelId(Long channelId) {
        long startMessageIndex = 0;
        long endMessageIndex = 100;
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

        Set<String> rawMessage = zSet.reverseRange(RedisConst.ChannelKey(channelId), startMessageIndex, endMessageIndex);
        if (isNil(rawMessage)) {
            return List.of();
        }

        return new ArrayList<>(rawMessage).stream()
                .filter(message -> !RedisConst.WELCOME_MESSAGE.equals(message))
                .map(this::messageConvertOrThrow)
                .collect(Collectors.toList());
    }

    private ChatMessage messageConvertOrThrow(String value) {
        try {
            return mapper.readValue(value, ChatMessage.class);
        } catch (Exception e) {
            log.error("ChatMessage Json Convert Failure::value:{}::Error Message:{}",
                    value, e.getMessage());
            return null;
        }
    }
}