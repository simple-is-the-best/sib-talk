package com.sib.chat.application.usecase;

import com.sib.chat.application.usecase.dto.Response;

import java.util.List;

public interface QueryChatUseCase {

    Response.Chat query(Long chatId);
    List<Response.Chat> queryAllByUserId(Long userId);
}
