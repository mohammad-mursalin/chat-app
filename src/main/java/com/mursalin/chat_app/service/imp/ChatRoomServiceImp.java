package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.repository.ChatRoomRepository;
import com.mursalin.chat_app.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    @Override
    public ChatRoom privateMessageRequest(ChatRoom chatRoom) {
        chatRoom.setSendAt(LocalTime.now());
        return chatRoomRepository.save(chatRoom);
    }
}
