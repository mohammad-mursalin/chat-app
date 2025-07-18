package com.mursalin.chat_app.controller;

import com.mursalin.chat_app.dto.GroupListResponse;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.model.UserPrinciples;
import com.mursalin.chat_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-app")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = ((UserPrinciples)userDetails).getUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/groups/{currentUserId}")
    public ResponseEntity<List<GroupListResponse>> getUserGroups(@PathVariable String currentUserId) {
        return userService.getUserGroups(currentUserId);
    }

}
