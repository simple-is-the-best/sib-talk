package com.sib.cache.redis.adapters.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.cache.redis.RedisUtils;
import com.sib.cache.redis.application.port.out.ChannelCachePort;
import com.sib.cache.redis.application.port.out.TopicCachePort;
import com.sib.vo.message.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheAdapter implements ChannelCachePort , TopicCachePort {

    private final RedisUtils redisUtils;
    private final ObjectMapper mapper;

    @Override
    public void setChannel(Long channelId) {
        ZSetOperations<String, String> zSet = redisUtils.opsForZSet();
        try {
            zSet.add(redisUtils.channelKey(channelId), "", redisUtils.timeScore());
        } catch (Exception e) {
            log.error("Redis Cashing Failure::Redis Key:{}::Error Message:{}", channelId, e.getMessage());
        }
    }

    @Override
    public void addMessage(Long channelId, ChatMessage message) {
        ZSetOperations<String, String> zSet = redisUtils.opsForZSet();
        try {
            String rawMessage = mapper.writeValueAsString(message);
            zSet.add(redisUtils.channelKey(channelId), rawMessage, redisUtils.timeScore());
        } catch (Exception e) {
            log.error("Redis SetMessage Failure::ChannelId:{}::Message:{}::Error Message:{}",
                    channelId, message, e.getMessage());
        }
    }

    @Override
    public void setTopic(Object delegate, String method, Long channelId) {
        ChannelTopic topic = ChannelTopic.of(redisUtils.channelKey(channelId));
        redisUtils.setAdapterTopic(delegate, method, topic);
    }
}