package com.sib.chat.adapters.out.cache;

import com.sib.chat.application.port.out.cache.ChannelCachePort;

import com.sib.utils.generator.RedisConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheAdapter implements ChannelCachePort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void setChannel(Long channelId) {
        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        try {
            zSet.add(RedisConst.ChannelKey(channelId), "[Create Channel]", RedisConst.ScoreOfTime());
        } catch (Exception e) {
            log.error("Redis Cashing Failure::Redis Key:{}::Error Message:{}", channelId, e.getMessage());
        }
    }
}