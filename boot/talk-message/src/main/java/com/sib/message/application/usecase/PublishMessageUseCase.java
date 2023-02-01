package com.sib.message.application.usecase;

import com.sib.vo.message.ChatMessage;

public interface PublishMessageUseCase {
    void publish(ChatMessage message);
}
