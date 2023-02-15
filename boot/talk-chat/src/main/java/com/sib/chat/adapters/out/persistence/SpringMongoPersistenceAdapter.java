package com.sib.chat.adapters.out.persistence;

import com.sib.chat.application.port.out.persistence.CreateChatPort;
import com.sib.chat.application.port.out.persistence.LoadChatPort;
import com.sib.chat.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Component
public class SpringMongoPersistenceAdapter implements CreateChatPort, LoadChatPort {

    private final ChatRepository repository;

    @Override
    public Chat create(Chat chat) {
        return repository.save(chat);
    }

    @Override
    public Chat loadById(Long chatId) {
        return repository.findById(chatId)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Chat> loadAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }
}