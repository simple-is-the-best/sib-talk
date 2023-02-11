package com.sib.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class ChatMessage {
    private Long id;
    private Type type;
    private Long channelId;
    private Long senderId;
    private String sendAt;
    private String content;
}
