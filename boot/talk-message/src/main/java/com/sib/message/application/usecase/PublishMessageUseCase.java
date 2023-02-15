package com.sib.message.application.usecase;

import com.sib.message.application.usecase.dto.Request;

public interface PublishMessageUseCase {
    void publish(Request.Message message, Long channelId);
}
