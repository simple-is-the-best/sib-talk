package com.sib.chat.application.usecase;

import com.sib.chat.application.usecase.dto.Request;
import com.sib.chat.application.usecase.dto.Response;

public interface CreateChatUseCase {

    Response.Create create(Request.Create req);
}