package com.mursalin.chat_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/register")
    public String showRegisterPage() {
        return "Register"; // refers to Register.html in templates folder
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login";
    }
    @GetMapping("/homepage")
    public String showHomepage() {
        return "ChatPage";
    }

    @GetMapping("/private-chat")
    public String showPrivateChatPage() {
        return "PrivateChat";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "ForgotPassword";
    }

    @GetMapping("/chat-app/auth/user/reset-password")
    public String showResetPasswordPage() {
        return "ResetPassword";
    }
}

