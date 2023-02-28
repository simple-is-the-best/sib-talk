package com.sib.message.application.port.out.cache;

import com.sib.vo.ChatMessage;

public interface ChannelCachePort {

    void addMessage(long channelId, ChatMessage message);
}
