package com.sib.chat.application.services;

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

    @Override
    public Response.Create create(Request.Create request) {
        Chat chat = ChatFactory.create(request);
        Chat entity = createPort.create(chat);

        return new Response.Create(entity.getId(), entity.getChannel().getId());
    }

    @Override
    public void delete(Long chatId) {
    }
}