package com.mursalin.chat_app.repository;

import com.mursalin.chat_app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUserEmailIgnoreCase(String userEmail);

    Optional<User> findUserByUserEmailIgnoreCase(String userEmail);
}
