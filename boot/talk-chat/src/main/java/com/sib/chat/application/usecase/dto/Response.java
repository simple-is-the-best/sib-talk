package com.sib.chat.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface Response {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Create {
        private Long chatId;
        private Long channelId;
    }
}