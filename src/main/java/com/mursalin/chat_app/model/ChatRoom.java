package com.mursalin.chat_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
@Builder
public class ChatRoom {

    @Id
    private String chatRoomId;
    private String senderId;
    private String receiverId;
    private String message;
    private LocalTime sendAt;
}
