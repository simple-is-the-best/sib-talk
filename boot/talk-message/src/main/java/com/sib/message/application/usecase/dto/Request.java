package com.sib.message.application.usecase.dto;

import com.sib.utils.generator.IdGenerator;
import com.sib.vo.ChatMessage;
import com.sib.vo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public interface Request {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Message {
        private String type;
        private Long senderId;
        private String sendAt;
        private String content;

        public ChatMessage convert(Long channelId) {
            return new ChatMessage(
                    IdGenerator.nextId(),
                    Type.valueOf(this.type),
                    channelId,
                    this.senderId,
                    this.sendAt,
                    this.content
            );
        }
    }
}
