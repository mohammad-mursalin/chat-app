package com.mursalin.chat_app.dto;

import com.mursalin.chat_app.model.Conversation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessageRequest {
    private String chatRoomId;
    private Conversation messageData;
}

