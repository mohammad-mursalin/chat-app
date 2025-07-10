package com.mursalin.chat_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "password_reset_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PasswordResetToken {

    @Id
    private String id;

    private String email;

    private String token;

    private Instant createdAt;

    private Instant expiresAt;
}
