package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class MongoService {

    private final MongoTemplate mongoTemplate;

    public List<ChatRoom> getChatsBetweenUsers(String senderId, String receiverId) {
        Query query = new Query();

        Criteria criteria = new Criteria().orOperator(
                Criteria.where("senderId").is(senderId).and("receiverId").is(receiverId),
                Criteria.where("senderId").is(receiverId).and("receiverId").is(senderId)
        );

        query.addCriteria(criteria);
        query.with(Sort.by(Sort.Direction.ASC, "sendAt"));

        return mongoTemplate.find(query, ChatRoom.class);
    }
}
