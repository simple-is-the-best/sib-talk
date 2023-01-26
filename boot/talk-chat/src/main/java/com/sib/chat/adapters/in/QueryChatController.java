package com.sib.chat.adapters.in;

import com.sib.chat.application.usecase.LoadChatUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/apis/v1")
@RestController
public class QueryChatController {

    private final LoadChatUseCase loadUseCase;

    @GetMapping("/chat/{chatId}")
    public void load(@PathVariable Long chatId) {
        loadUseCase.load(chatId);
    }

    @GetMapping("/chats/users/{userId}")
    public void loadAll(@PathVariable Long userId) {
        loadUseCase.loadAll(userId);
    }
}