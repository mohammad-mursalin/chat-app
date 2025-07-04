package com.mursalin.chat_app.controller;

import com.mursalin.chat_app.dto.ChatMessageRequest;
import com.mursalin.chat_app.model.ChatMessage;
import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.model.Conversation;
import com.mursalin.chat_app.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

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
    public void handlePrivateMessageRequest(@Payload ChatMessageRequest chatMessageRequest) {
        chatRoomService.privateMessageRequest(chatMessageRequest);
    }

    @PostMapping("/chatroom")
    public ChatRoom getAllPrivateMessages(@RequestBody List<String> membersId) {
        return chatRoomService.getAllPrivateMessages(membersId);
    }
}
