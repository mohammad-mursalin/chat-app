package com.mursalin.chat_app.service;

import com.mursalin.chat_app.model.User;

import java.util.List;

public interface UserService {
    User registerNewUser(User newUser);

    User login(User user);

    List<User> getAllUsers();
}
