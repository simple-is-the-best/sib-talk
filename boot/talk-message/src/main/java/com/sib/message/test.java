package com.sib.message;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.WebSocketMessage;

@RequiredArgsConstructor
public class test {

    private final WebSocketMessage message;

    private final SimpMessagingTemplate template;

}
