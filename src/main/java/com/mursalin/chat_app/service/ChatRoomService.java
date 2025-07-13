package com.mursalin.chat_app.service;

import com.mursalin.chat_app.dto.ChatMessageRequest;
import com.mursalin.chat_app.model.ChatRoom;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatRoomService {
    void privateMessageRequest(ChatMessageRequest chatRoom);

    ResponseEntity<ChatRoom> getAllPrivateMessages(List<String> membersId, String groupName);
}
