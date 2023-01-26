package com.sib.chat.application.services;

import com.sib.chat.application.usecase.LoadChatUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoadService implements LoadChatUseCase {

    @Override
    public void load(Long chatId) {

    }

    @Override
    public void loadAll(Long userId) {

    }
}
