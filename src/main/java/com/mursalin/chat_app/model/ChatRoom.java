package com.mursalin.chat_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
//@Document
public class ChatRoom {

//    @Id
    private String chatRoomId;
    private User sender;
    private User receiver;
    private ChatMessage chatMessage;
}
