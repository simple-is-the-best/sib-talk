package com.sib.chat.adapters.out.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.chat.application.port.out.cache.CacheChannelPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheAdapter implements CacheChannelPort {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper mapper;
    private final String KEY_PREFIX = "channel:";

    @Override
    public void caching(Long channelId) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        LocalDateTime now = LocalDateTime.now();
        long score = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        String key = KEY_PREFIX + channelId;
        try {
            zSet.add(key, "", score);
        } catch (Exception e) {
            log.error("Redis Cashing Failure::Redis Key:{}",key);
        }
    }
}