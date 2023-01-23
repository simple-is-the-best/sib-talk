package com.sib.chat.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class Message {
    Long id;
    Type type;
    Long channelId;
    Long senderId;
    LocalDateTime sendAt;
    Object content;
}
