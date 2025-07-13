package com.mursalin.chat_app.service;

import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.model.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> handleForgotPassword(String email);

    ResponseEntity<String> resetPassword(PasswordResetRequest resetRequest);

    ResponseEntity<String> registerNewUser(User newUser);

    ResponseEntity<String> login(User user);
}
