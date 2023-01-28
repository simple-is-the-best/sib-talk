package com.sib.chat.application.usecase.dto;

import com.sib.chat.domain.Channel;
import com.sib.chat.domain.Message;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public interface Response {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Create {
        @NotNull
        private Long chatId;
        @NotNull
        private Long channelId;
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Chat {
        private Long chatId;
        private Long channelId;
        private Message lastMessage;
        private LocalDateTime createdAt;

        public Response.Chat convert(com.sib.chat.domain.Chat chat) {
            Channel channel = chat.getChannel();
            List<Message> messages = channel.getMessages();
            int lastIndex = messages.size() - 1;
            return new Response.Chat(
                    chat.getId(),
                    channel.getId(),
                    messages.get(lastIndex),
                    chat.getCreatedAt()
            );
        }
    }
}