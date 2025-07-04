package com.mursalin.chat_app.service;

import com.mursalin.chat_app.dto.ChatMessageRequest;
import com.mursalin.chat_app.model.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    void privateMessageRequest(ChatMessageRequest chatRoom);

    ChatRoom getAllPrivateMessages(List<String> membersId);
}
