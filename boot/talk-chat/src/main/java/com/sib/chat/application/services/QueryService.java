package com.sib.chat.application.services;

import com.sib.chat.application.port.out.persistence.LoadChatPort;
import com.sib.chat.application.usecase.QueryChatUseCase;
import com.sib.chat.application.usecase.dto.Response;
import com.sib.chat.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueryService implements QueryChatUseCase {

    private final LoadChatPort loadChatPort;

    @Override
    public Response.Chat query(Long chatId) {
        Chat chat = loadChatPort.loadById(chatId);
        return Response.Chat.convert(chat);
    }

    @Override
    public List<Response.Chat> queryAllByUserId(Long userId) {
        return loadChatPort.loadAllByUserId(userId).stream()
                .map(Response.Chat::convert)
                .collect(Collectors.toList());
    }
}
