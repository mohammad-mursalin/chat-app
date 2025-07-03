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
        return "Homepage";
    }
}

