package com.sib.cache.redis;

import com.sib.cache.redis.config.RedisKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisMessageListenerContainer redisListenerContainer;

    public ZSetOperations<String, String> opsForZSet() {
        return redisTemplate.opsForZSet();
    }

    public void convertAndSend(String topic, Object message) {
        redisTemplate.convertAndSend(topic, message);
    }

    public void setAdapterTopic(Object delegate, String method, ChannelTopic topic) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(delegate, method);
        redisListenerContainer.addMessageListener(adapter, topic);
    }

    public double timeScore() {
        LocalDateTime now = LocalDateTime.now();
        return now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public String channelKey(Long id) {
        return RedisKey.PREFIX_CHANNEL + id;
    }
}