package com.sib.vo.message;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class ChatMessage {
    Long id;
    Type type;
    Long channelId;
    Long senderId;
    LocalDateTime sendAt;
    Object content;
}
