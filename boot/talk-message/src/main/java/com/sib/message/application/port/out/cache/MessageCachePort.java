package com.sib.message.application.port.out.cache;

import com.sib.vo.ChatMessage;

import java.util.List;

public interface MessageCachePort {

    List<ChatMessage> loadAllByChannelId(Long channelId);
}
