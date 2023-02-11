package com.sib.chat.application.services;

import com.sib.chat.domain.Channel;
import com.sib.utils.generator.IdGenerator;
import com.sib.vo.ChatMessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChannelFactory {

    public static Channel create() {
        List<Long> receiverIds = new ArrayList<>();
        List<ChatMessage> chatMessages = new ArrayList<>();
        return Channel.builder()
                .id(IdGenerator.nextId())
                .title("")
                .receiverIds(receiverIds)
                .chatMessages(chatMessages)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Channel create(List<Long> receiverIds) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        return Channel.builder()
                .id(IdGenerator.nextId())
                .title(receiverIds.get(0).toString())
                .receiverIds(receiverIds)
                .chatMessages(chatMessages)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
