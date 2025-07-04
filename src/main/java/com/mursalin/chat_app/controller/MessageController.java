package com.mursalin.chat_app.controller;

import com.mursalin.chat_app.model.ChatMessage;
import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-app")
public class MessageController {

    private final ChatRoomService chatRoomService;

    @MessageMapping("/send-message")
    @SendTo("/chatroom/all")
    public ChatMessage handleMessageRequest(ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/send-private-message")
    public ChatRoom handlePrivateMessageRequest( @Payload ChatRoom chatRoom) {
        return chatRoomService.privateMessageRequest(chatRoom);
    }

    @GetMapping("/messages")
    public List<ChatRoom> getAllPrivateMessages(@RequestParam String senderId, @RequestParam String receiverId) {
        return chatRoomService.getAllPrivateMessages(senderId, receiverId);
    }
}
