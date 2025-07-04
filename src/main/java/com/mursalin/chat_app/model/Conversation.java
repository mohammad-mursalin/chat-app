package com.mursalin.chat_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Conversation {

    private String senderId;
    private String receiverId;
    private String message;
    private LocalDateTime sendAt;
}
