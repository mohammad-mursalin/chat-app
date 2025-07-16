package com.mursalin.chat_app.controller;

import com.mursalin.chat_app.dto.ChatMessageRequest;
import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.model.Conversation;
import com.mursalin.chat_app.service.ChatRoomService;
import com.mursalin.chat_app.utils.PresenceEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-app")
public class MessageController {

    private final ChatRoomService chatRoomService;
    private final PresenceEventListener presenceEventListener;

    @MessageMapping("/send-message")
    @SendTo("/chatroom/all")
    public Conversation handleMessageRequest(Conversation conversation) {
        return conversation;
    }

    @MessageMapping("/send-private-message")
    public void handlePrivateMessageRequest(@Payload ChatMessageRequest chatMessageRequest) {
        chatRoomService.privateMessageRequest(chatMessageRequest);
    }

    @PostMapping("/chatroom")
    public ResponseEntity<ChatRoom> getAllPrivateMessages(@RequestBody List<String> membersId, @RequestParam(required = false) String groupName) {
        return chatRoomService.getAllPrivateMessages(membersId, groupName);
    }

    @GetMapping("/online-users")
    public ResponseEntity<Set<String>> getOnlineUsers() {
        return ResponseEntity.ok(presenceEventListener.getOnlineUsers());
    }
}
