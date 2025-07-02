package com.mursalin.chat_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
@AllArgsConstructor
//@Document
public class ChatMessage {

//    @Id
//    private String chatMessageId;
//    private String chatMessage;
//    private LocalTime sentAt;
    private String sender;
    private String content;
}
