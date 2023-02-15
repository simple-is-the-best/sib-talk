package com.sib.message.adapters.in;

import com.sib.message.application.usecase.QueryMessageUseCase;
import com.sib.message.application.usecase.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class QueryMessageController {

    private final QueryMessageUseCase queryMessageUseCase;

    @GetMapping("/channel/{channelId}")
    public ResponseEntity<?> getMessages(@PathVariable Long channelId) {
        List<Response.Message> messages = queryMessageUseCase.queryAllByChannelId(channelId);
        return ResponseEntity.ok(messages);
    }
}
