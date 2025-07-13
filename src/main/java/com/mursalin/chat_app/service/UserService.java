package com.mursalin.chat_app.service;

import com.mursalin.chat_app.dto.GroupListResponse;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> registerNewUser(User newUser);

    ResponseEntity<String> login(User user);

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<List<GroupListResponse>> getUserGroups(String currentUserId);

    ResponseEntity<String> handleForgotPassword(String email);

    ResponseEntity<String> resetPassword(PasswordResetRequest resetRequest);
}
