package com.sib.message.application.service;

import com.sib.message.application.port.out.cache.MessageCachePort;
import com.sib.message.application.usecase.QueryMessageUseCase;
import com.sib.message.application.usecase.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueryMessageService implements QueryMessageUseCase {

    private final MessageCachePort messageCachePort;

    @Override
    public List<Response.Message> queryAllByChannelId(Long channelId) {
        return messageCachePort.loadAllByChannelId(channelId).stream()
                .map(Response.Message::convert)
                .collect(Collectors.toList());
    }
}
