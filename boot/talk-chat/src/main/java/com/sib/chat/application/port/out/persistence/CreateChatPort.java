package com.sib.chat.application.port.out.persistence;

import com.sib.chat.domain.Chat;

public interface CreateChatPort {

    Chat create(Chat chat);
}
