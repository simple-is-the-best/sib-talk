package com.sib.cache.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.cache.redis.config.RedisKey;
import com.sib.vo.message.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisMessageListenerContainer redisListenerContainer;
    private final MessageListener messageListener;

    private final ObjectMapper mapper;

    public ZSetOperations<String, String> opsForZSet() {
        return redisTemplate.opsForZSet();
    }

    public void convertAndSend(String topic, Object message) {
        redisTemplate.convertAndSend(topic, message);
    }

    public void setAdapterTopic(ChannelTopic topic) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(messageListener, "onMessage");
        redisListenerContainer.addMessageListener(adapter, topic);
    }

    public double getScoreOfTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public String getChannelKey(Long id) {
        return RedisKey.PREFIX_CHANNEL + id;
    }

    public String convertToString(Message message) {
        return redisTemplate.getStringSerializer().deserialize(message.getBody());
    }

    public ChatMessage convertToMessage(String message) {
        try {
            return mapper.readValue(message, ChatMessage.class);
        } catch (Exception e) {
            throw new JsonParseException();
        }
    }
}