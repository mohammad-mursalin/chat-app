package com.mursalin.chat_app.service.imp;

import com.mongodb.client.result.UpdateResult;
import com.mursalin.chat_app.dto.GroupListResponse;
import com.mursalin.chat_app.model.ChatRoom;
import com.mursalin.chat_app.model.Conversation;
import com.mursalin.chat_app.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MongoService {

    private final MongoTemplate mongoTemplate;

    public List<GroupListResponse> getUserGroups(String currentUserId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("membersId").in(currentUserId));
        query.addCriteria(Criteria.where("groupName").ne(null)); // group only

        query.fields().include("groupName").include("membersId");

        List<ChatRoom> chatRooms = mongoTemplate.find(query, ChatRoom.class);

        return chatRooms.stream()
                .map(room -> new GroupListResponse(room.getGroupName(), room.getMembersId()))
                .collect(Collectors.toList());
    }

    public boolean updatePasswordByEmail(String email, String newPassword) {
        Query query = new Query(Criteria.where("userEmail").is(email));
        Update update = new Update().set("password", newPassword);
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        return result.getModifiedCount() > 0;
    }

    public boolean addConversationInChatroom(String chatRoomId, Conversation conversation) {

        Query query = new Query(Criteria.where("_id").is(chatRoomId));

        Update update = new Update()
                .push("conversations", conversation);

        UpdateResult result = mongoTemplate.updateFirst(query, update, ChatRoom.class);

        return result.getModifiedCount() > 0;
    }

}
