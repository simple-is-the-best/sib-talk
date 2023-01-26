package com.sib.chat.application.services;

import com.sib.chat.application.usecase.dto.Request;
import com.sib.chat.domain.Channel;
import com.sib.chat.domain.Chat;
import com.sib.chat.domain.Status;
import com.sib.utils.generator.IdGenerator;

import java.time.LocalDateTime;

public class ChatFactory {

    public static Chat create(Request.Create request) {
        Channel channel = ChannelFactory.create(request.getReceiverIds());

        return Chat.builder()
                .id(IdGenerator.nextId())
                .userId(request.getUserId())
                .channel(channel)
                .method(request.getMethod())
                .status(Status.ACTIVATED)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
