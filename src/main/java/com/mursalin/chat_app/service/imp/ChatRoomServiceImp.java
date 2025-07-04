package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.dto.ChatMessageRequest;
import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.model.Conversation;
import com.mursalin.chat_app.repository.ChatRoomRepository;
import com.mursalin.chat_app.service.ChatRoomService;
import com.mursalin.chat_app.utils.ChatKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MongoTemplate mongoTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void privateMessageRequest(ChatMessageRequest chatMessageRequest) {
        String chatRoomId = chatMessageRequest.getChatRoomId();

        Query query = new Query(Criteria.where("_id").is(chatRoomId));

        Update update = new Update()
                .push("conversations", chatMessageRequest.getMessageData());

        mongoTemplate.updateFirst(query, update, ChatRoom.class);
        messagingTemplate.convertAndSend("/chatroom/" + chatRoomId, chatMessageRequest.getMessageData());
    }

    @Override
    public ChatRoom getAllPrivateMessages(List<String> membersId) {
        String chatKey = ChatKeyGenerator.generate(membersId);
        ChatRoom existingChatRoom = chatRoomRepository.findByChatKey(chatKey);
        if (existingChatRoom != null) {
            existingChatRoom.getConversations().sort(Comparator.comparing(Conversation::getSendAt));
            return existingChatRoom;
        }

        ChatRoom newRoom = new ChatRoom();
        newRoom.setMembersId(membersId);
        newRoom.setChatKey(chatKey);
        newRoom.setConversations(new ArrayList<>());
        return chatRoomRepository.save(newRoom);

    }
}
