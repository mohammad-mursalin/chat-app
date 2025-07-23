package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.dto.ChatMessageRequest;
import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.model.Conversation;
import com.mursalin.chat_app.repository.ChatRoomRepository;
import com.mursalin.chat_app.service.ChatRoomService;
import com.mursalin.chat_app.utils.ChatKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MongoService mongoService;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void privateMessageRequest(ChatMessageRequest chatMessageRequest) {
        String chatRoomId = chatMessageRequest.getChatRoomId();
        boolean messageSaved = mongoService.addConversationInChatroom(chatRoomId, chatMessageRequest.getMessageData());
        if(messageSaved) {
            messagingTemplate.convertAndSend("/chatroom/" + chatRoomId, chatMessageRequest.getMessageData());
        } else {
            messagingTemplate.convertAndSend("/chatroom/error/" + chatRoomId + "/" + chatMessageRequest.getMessageData().getSenderId(), "Error occurred while sending message. Please try sending again");
        }
    }

    @Override
    public ResponseEntity<ChatRoom> getAllPrivateMessages(List<String> membersId, String groupName) {
        String chatKey = ChatKeyGenerator.generate(membersId);
        ChatRoom existingChatRoom = chatRoomRepository.findByChatKey(chatKey);
        if (existingChatRoom != null)
            return new ResponseEntity<>(existingChatRoom, HttpStatus.OK);

        ChatRoom newRoom = new ChatRoom();
        newRoom.setMembersId(membersId);
        newRoom.setChatKey(chatKey);
        newRoom.setGroupName(groupName);
        newRoom.setConversations(new ArrayList<>());
        return new ResponseEntity<>(chatRoomRepository.save(newRoom), HttpStatus.OK);

    }
}
