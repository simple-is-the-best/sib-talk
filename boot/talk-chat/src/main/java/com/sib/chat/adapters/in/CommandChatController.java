package com.sib.chat.adapters.in;

import com.sib.chat.application.usecase.ConnectChatUseCase;
import com.sib.chat.application.usecase.CreateChatUseCase;
import com.sib.chat.application.usecase.DeleteChatUseCase;
import com.sib.chat.application.usecase.dto.Request;
import com.sib.chat.application.usecase.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/apis/v1")
@RestController
public class CommandChatController {

    private final CreateChatUseCase createUseCase;
    private final DeleteChatUseCase deleteUseCase;
    private final ConnectChatUseCase connectUseCase;

    @PutMapping("/chat")
    public void create(@RequestBody Request.Create request) {
        Response.Create created = createUseCase.create(request);
        connectUseCase.connect();
    }

    @DeleteMapping("/chat/{chatId}")
    public void delete(@PathVariable Long chatId) {
        deleteUseCase.delete(chatId);
    }

    @PostMapping("/chat")
    public void connect() {
        connectUseCase.connect();
    }
}