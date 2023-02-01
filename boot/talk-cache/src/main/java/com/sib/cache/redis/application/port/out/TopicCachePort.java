package com.sib.cache.redis.application.port.out;


public interface TopicCachePort {

    void setTopic(Object delegate, String method, Long channelId);
}
