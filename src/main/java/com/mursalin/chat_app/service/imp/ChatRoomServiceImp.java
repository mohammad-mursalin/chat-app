package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.repository.ChatRoomRepository;
import com.mursalin.chat_app.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MongoService mongoService;

    @Override
    public ChatRoom privateMessageRequest(ChatRoom chatRoom) {

        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoom> getAllPrivateMessages(String senderId, String receiverId) {
        return mongoService.getChatsBetweenUsers(senderId, receiverId);
    }
}
