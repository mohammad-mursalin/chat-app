package com.mursalin.chat_app.repository;

import com.mursalin.chat_app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUserEmail(String userEmail);

    Optional<User> findUserByUserEmail(String userEmail);
}
