package com.mursalin.chat_app.controller;

import com.mursalin.chat_app.dto.GroupListResponse;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.model.Status;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/registration")
    public User registration(@RequestBody User newUser) {
        return userService.registerNewUser(newUser);
    }

    @PostMapping("/user/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = ((UserPrinciples)userDetails).getUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/groups/{currentUserId}")
    public List<GroupListResponse> getUserGroups(@PathVariable String currentUserId) {
        return userService.getUserGroups(currentUserId);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<String> handleForgotPassword(@RequestBody String email) {
        return userService.handleForgotPassword(email);
    }

    @PutMapping("/password-reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest resetRequest) {
        return userService.resetPassword(resetRequest);
    }

}
