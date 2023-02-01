package com.sib.cache.redis.application.port.out;

import com.sib.vo.message.ChatMessage;

public interface ChannelCachePort {

    void setChannel(Long channelId);

    void addMessage(Long channelId, ChatMessage message);
}
