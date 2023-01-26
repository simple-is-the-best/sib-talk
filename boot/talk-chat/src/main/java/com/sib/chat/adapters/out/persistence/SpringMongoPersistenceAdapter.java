package com.sib.chat.adapters.out.persistence;

import com.sib.chat.application.port.out.persistence.CreateChatPort;
import com.sib.chat.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SpringMongoPersistenceAdapter implements CreateChatPort {

    private final ChatRepository repository;

    @Override
    public Chat create(Chat chat) {
        return repository.save(chat);
    }
}