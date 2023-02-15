package com.sib.message.adapters.in;

import com.sib.message.application.usecase.CreateTopicUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sib.utils.ObjectValidator.isNil;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class CommandChannelController {

    private final CreateTopicUseCase createTopicUseCase;

    @PutMapping("/channel/{channelId}")
    public ResponseEntity<Object> createChannelTopic(@PathVariable long channelId) {
        if(isNil(channelId)) {
            return ResponseEntity.badRequest().body(false);
        }
        createTopicUseCase.createTopic(channelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }
}