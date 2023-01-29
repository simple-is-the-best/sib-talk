package com.sib.cache.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;

    public ZSetOperations<String, String> opsForZSet() {
        return redisTemplate.opsForZSet();
    }

    public void zAdd(String key, String value, double score) {
        opsForZSet().add(key, value, score);
    }

    public ChannelTopic genTopic(String topic) {
        return new ChannelTopic(topic);
    }
}