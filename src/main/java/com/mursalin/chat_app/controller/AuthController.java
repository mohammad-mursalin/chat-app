package com.mursalin.chat_app.controller;
import com.mursalin.chat_app.dto.LoginRequestDto;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.dto.RegistrationRequestDto;
import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.service.AuthService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequestDto newUser) {
        return authService.registerNewUser(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto user) {
        return authService.login(user);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> handleForgotPassword(@RequestBody PasswordResetRequest resetRequest) {
        return authService.handleForgotPassword(resetRequest.getEmail());
    }

    @PostMapping("/password-reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetRequest resetRequest) {
        return authService.resetPassword(resetRequest);
    }
}
