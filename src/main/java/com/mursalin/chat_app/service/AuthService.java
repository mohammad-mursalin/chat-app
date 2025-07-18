package com.mursalin.chat_app.service;

import com.mursalin.chat_app.dto.LoginRequestDto;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.dto.RegistrationRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> handleForgotPassword(String email);

    ResponseEntity<String> resetPassword(PasswordResetRequest resetRequest);

    ResponseEntity<String> registerNewUser(@Valid RegistrationRequestDto newUser);

    ResponseEntity<String> login(@Valid LoginRequestDto user);
}
