package com.sib.chat.application.usecase;

import org.springframework.http.HttpHeaders;

public interface ConnectChatUseCase {

    HttpHeaders upgradeSocketHeaders(Long channelId);
    void disConnect();
}
