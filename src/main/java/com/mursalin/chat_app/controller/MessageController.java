package com.mursalin.chat_app.controller;

import com.mursalin.chat_app.model.ChatMessage;
import com.mursalin.chat_app.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-app")
public class MessageController {

    @MessageMapping("/send-message")
    @SendTo("/chatroom/all")
    public ChatMessage handleMessageRequest(ChatMessage chatMessage) {
        return chatMessage;
    }
}
