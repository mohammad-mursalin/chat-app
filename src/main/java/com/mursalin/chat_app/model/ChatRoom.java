package com.mursalin.chat_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class ChatRoom {

    @Id
    private String chatRoomId;
    private String chatKey;
    private List<String> membersId = new ArrayList<>();
    private List<Conversation> conversations = new ArrayList<>();

    public void setMemberId(String memberId) {
        this.membersId.add(memberId);
    }

    public void setConversation(Conversation conversation) {
        this.conversations.add(conversation);
    }
}
