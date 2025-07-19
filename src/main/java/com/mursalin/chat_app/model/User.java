package com.mursalin.chat_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
@Builder
public class User {

    @Id
    private String userId;

    @Indexed(unique = true)
    private String username;
    private String userEmail;
    private String password;
}
