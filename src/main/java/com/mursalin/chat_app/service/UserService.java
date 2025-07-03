package com.mursalin.chat_app.service;

import com.mursalin.chat_app.model.User;

public interface UserService {
    User registerNewUser(User newUser);

    User login(User user);
}
