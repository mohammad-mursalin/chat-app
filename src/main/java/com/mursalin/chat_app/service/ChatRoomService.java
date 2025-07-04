package com.mursalin.chat_app.service;

import com.mursalin.chat_app.model.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    ChatRoom privateMessageRequest(ChatRoom chatRoom);

    List<ChatRoom> getAllPrivateMessages(String senderId, String receiverId);
}
