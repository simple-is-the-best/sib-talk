package com.sib.message.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sib.message.application.port.out.cache.ChannelCachePort;
import com.sib.message.application.port.out.cache.PublishCachePort;
import com.sib.message.application.usecase.PublishMessageUseCase;
import com.sib.message.application.usecase.dto.Request;
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
    public void publish(Request.Message message, long channelId) {
        ChatMessage chatMessage = message.convert(channelId);
        String pubMessage;
        try {
            pubMessage = mapper.writeValueAsString(chatMessage);
        } catch (Exception e) {
            throw new RuntimeException("Json Passer Exception Object is::"+chatMessage);
        }
        channelCachePort.addMessage(channelId, chatMessage);
        publishCachePort.publish(RedisConst.ChannelKey(channelId), pubMessage);
    }
}
