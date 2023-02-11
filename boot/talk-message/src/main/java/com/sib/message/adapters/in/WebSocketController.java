package com.sib.message.adapters.in;

import com.sib.message.application.usecase.CreateTopicUseCase;
import com.sib.message.application.usecase.PublishMessageUseCase;
import com.sib.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WebSocketController {

    private final PublishMessageUseCase publishMessageUseCase;
    private final CreateTopicUseCase createTopicUseCase;

    @PutMapping("/channel/topic/{channelId}")
    public boolean createChannelTopic(@PathVariable long channelId) {
        if(isNil(channelId)) {
            return false;
        }
        createTopicUseCase.createTopic(channelId);
        return true;
    }

    @MessageMapping("/channel/{channelId}")
    public void onMessage(@Payload ChatMessage message,
                          @DestinationVariable long channelId) {
        publishMessageUseCase.publish(message, channelId);
    }

    private boolean isNil(Object o) {
        return o == null || o.toString().isEmpty();
    }
}