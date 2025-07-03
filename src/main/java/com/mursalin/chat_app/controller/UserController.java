package com.mursalin.chat_app.controller;

import com.mursalin.chat_app.model.Status;
import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.service.UserService;
import lombok.RequiredArgsConstructor;
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

}
