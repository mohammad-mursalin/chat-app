package com.mursalin.chat_app.controller;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-app/auth/user")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody User newUser) {
        return authService.registerNewUser(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return authService.login(user);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> handleForgotPassword(@RequestBody PasswordResetRequest resetRequest) {
        return authService.handleForgotPassword(resetRequest.getEmail());
    }

    @PostMapping("/password-reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest resetRequest) {
        return authService.resetPassword(resetRequest);
    }
}
