package com.sib.chat.application.services;

import com.sib.cache.redis.application.port.out.ChannelCachePort;
import com.sib.cache.redis.application.port.out.TopicCachePort;
import com.sib.chat.application.port.out.persistence.CreateChatPort;
import com.sib.chat.application.usecase.CreateChatUseCase;
import com.sib.chat.application.usecase.DeleteChatUseCase;
import com.sib.chat.application.usecase.dto.Request;
import com.sib.chat.application.usecase.dto.Response;
import com.sib.chat.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CommandService implements CreateChatUseCase, DeleteChatUseCase {

    private final CreateChatPort createPort;
    private final ChannelCachePort channelCachePort;
    private final TopicCachePort topicCachePort;

    @Override
    public Response.Create create(Request.Create request) {
        Chat saved = createPort.create(ChatFactory.create(request));

        Long channelId = saved.getChannel().getId();
        channelCachePort.setChannel(channelId);
        topicCachePort.setTopic(channelId);
        return new Response.Create(saved.getId(), channelId);
    }

    @Override
    public void delete(Long chatId) {
    }
}