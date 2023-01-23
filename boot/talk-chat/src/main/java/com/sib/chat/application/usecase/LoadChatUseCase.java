package com.sib.chat.application.usecase;

public interface LoadChatUseCase {

    void load(Long userId, Long chatId);
    void loadAll(Long userId);
}
