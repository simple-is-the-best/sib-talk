package com.sib.chat.adapters.in;

import com.sib.chat.application.usecase.QueryChatUseCase;
import com.sib.chat.application.usecase.dto.Response;
import com.sib.chat.domain.Chat;
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
public class QueryChatController {

    private final QueryChatUseCase queryUseCase;

    @GetMapping("/chats/{chatId}")
    public ResponseEntity<?> getChat(@PathVariable Long chatId) {
        Response.Chat chat = queryUseCase.query(chatId);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/chats/users/{userId}")
    public ResponseEntity<?> getChatsByUserId(@PathVariable Long userId) {
        List<Response.Chat> chats = queryUseCase.queryAllByUserId(userId);
        return ResponseEntity.ok(chats);
    }
}