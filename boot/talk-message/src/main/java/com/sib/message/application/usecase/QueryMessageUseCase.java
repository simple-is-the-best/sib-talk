package com.sib.message.application.usecase;

import com.sib.message.application.usecase.dto.Response;

import java.util.List;

public interface QueryMessageUseCase {

    List<Response.Message> queryAllByChannelId(Long channelId);
}
