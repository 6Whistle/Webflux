package com.whistle6.webfluxdemo.chat.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private String id;
    private String roomId;
    private String msg;
    private String sender;
    private LocalDateTime createdAt;
}
