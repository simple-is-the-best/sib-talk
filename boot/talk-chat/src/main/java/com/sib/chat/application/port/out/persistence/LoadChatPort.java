package com.sib.chat.application.port.out.persistence;

import com.sib.chat.domain.Chat;

import java.util.List;

public interface LoadChatPort {

    Chat loadById(Long chatId);
    List<Chat> loadAllByUserId(Long userId);
}
