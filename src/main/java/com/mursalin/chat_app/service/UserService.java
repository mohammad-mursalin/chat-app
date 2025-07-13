package com.mursalin.chat_app.service;

import com.mursalin.chat_app.dto.GroupListResponse;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<List<GroupListResponse>> getUserGroups(String currentUserId);
}
