package com.sib.chat.application.services;

import com.sib.chat.application.usecase.ConnectChatUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConnectService implements ConnectChatUseCase {


    @Override
    public void connect() {

    }

    @Override
    public void disConnect() {

    }
}
