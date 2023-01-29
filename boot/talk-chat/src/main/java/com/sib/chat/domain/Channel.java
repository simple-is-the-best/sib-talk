package com.sib.chat.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Document(collation = "channel")
public class Channel {

    @Id
    private Long id;
    private String title;
    private List<Long> receiverIds;
    private List<ChatMessage> chatMessages;
    private LocalDateTime createdAt;
}