package com.sib.message.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscribeMessageService implements MessageListener {

    private final SimpMessageSendingOperations messageTemplate;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper mapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String pubMessage = convertToString(message);
        ChatMessage chatMessage = convertToMessage(pubMessage);
        messageTemplate.convertAndSend("/sub/channel/" + chatMessage.getChannelId(), chatMessage);
    }

    private String convertToString(Message message) {
        try {
            return redisTemplate.getStringSerializer().deserialize(message.getBody());
        } catch (Exception e) {
            throw new JsonParseException();
        }
    }

    private ChatMessage convertToMessage(String message) {
        try {
            return mapper.readValue(message, ChatMessage.class);
        } catch (Exception e) {
            throw new JsonParseException();
        }
    }
}
