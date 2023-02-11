package com.sib.message.adapters.out.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.message.application.port.out.cache.ChannelCachePort;
import com.sib.message.application.port.out.cache.PublishCachePort;
import com.sib.utils.generator.RedisConst;
import com.sib.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheAdapter implements ChannelCachePort, PublishCachePort {
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
}