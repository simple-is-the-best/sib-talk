package com.sib.cache.redis.adapters.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.cache.redis.RedisUtils;
import com.sib.cache.redis.application.port.out.ChannelCachePort;
import com.sib.cache.redis.config.RedisKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisCacheAdapter implements ChannelCachePort {

    private final RedisUtils redisUtils;
    private final ObjectMapper mapper;

    @Override
    public void setChannel(Long channelId) {
        try {
            redisUtils.zAdd(channelKeyGen(channelId), "", scoreGen());
        } catch (Exception e) {
            log.error("Redis Cashing Failure::Redis Key:{}::Error Message:{}",channelKeyGen(channelId), e.getMessage());
        }
    }

    private double scoreGen() {
        LocalDateTime now = LocalDateTime.now();
        return now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private String channelKeyGen(Long id) {
        return RedisKey.PREFIX_CHANNEL + id;
    }
}