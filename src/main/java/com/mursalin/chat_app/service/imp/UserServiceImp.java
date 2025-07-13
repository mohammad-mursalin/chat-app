package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.dto.GroupListResponse;
import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.repository.UserRepository;
import com.mursalin.chat_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final MongoService mongoService;


    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<GroupListResponse>> getUserGroups(String currentUserId) {
        return new ResponseEntity<>(mongoService.getUserGroups(currentUserId), HttpStatus.OK);
    }
}
