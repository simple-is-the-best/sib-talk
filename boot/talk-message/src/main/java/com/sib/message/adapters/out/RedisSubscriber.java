package com.sib.message.adapters.out;

import com.sib.cache.redis.RedisUtils;
import com.sib.vo.message.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

    private final RedisUtils redisUtils;
    private final SimpMessageSendingOperations messageTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String pubMessage = redisUtils.convertToString(message);
        ChatMessage chatMessage = redisUtils.convertToMessage(pubMessage);

        messageTemplate.convertAndSend("/chat/"+chatMessage.getChannelId(), chatMessage);
    }
}
