package com.sib.chat.application.services;

import com.sib.chat.domain.Channel;
import com.sib.chat.domain.Message;
import generator.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChannelFactory {

    public static Channel create() {
        List<Long> receiverIds = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        return Channel.builder()
                .id(IdGenerator.nextId())
                .receiverIds(receiverIds)
                .messages(messages)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Channel create(List<Long> receiverIds) {
        List<Message> messages = new ArrayList<>();
        return Channel.builder()
                .id(IdGenerator.nextId())
                .receiverIds(receiverIds)
                .messages(messages)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
