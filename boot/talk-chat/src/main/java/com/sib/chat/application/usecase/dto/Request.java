package com.sib.chat.application.usecase.dto;

import com.sib.chat.domain.Method;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface Request {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Create {
        @NotNull
        private Long userId;
        @NotNull
        private Method method;
        @NotNull
        private List<Long> receiverIds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Connect {
        @NotNull
        private Long userId;
        @NotNull
        private Long channelId;
    }
}