package com.sib.message.application.usecase;

import com.sib.vo.ChatMessage;

public interface PublishMessageUseCase {
    void publish(ChatMessage message, Long channelId);
}
