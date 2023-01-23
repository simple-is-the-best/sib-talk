package com.sib.chat.application.usecase.dto;

import com.sib.chat.domain.Method;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface Request {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Create {
        private Long userId;
        private Method method;
        private List<Long> receiverIds;
    }
}