package com.sib.message.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.message.application.port.out.cache.ChannelCachePort;
import com.sib.message.application.port.out.cache.PublishCachePort;
import com.sib.message.application.usecase.PublishMessageUseCase;
import com.sib.utils.generator.RedisConst;
import com.sib.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublishMessageService implements PublishMessageUseCase {

    private final ChannelCachePort channelCachePort;
    private final PublishCachePort publishCachePort;
    private final ObjectMapper mapper;

    @Override
    public void publish(ChatMessage message, Long channelId) {
        String pubMessage;
        try {
            pubMessage = mapper.writeValueAsString(message);
        } catch (Exception e) {
            throw new RuntimeException("Json Passer Exception Object is::"+message);
        }
        channelCachePort.addMessage(channelId, message);
        publishCachePort.publish(RedisConst.ChannelKey(channelId), pubMessage);
    }
}
