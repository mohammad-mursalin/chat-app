package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.repository.UserRepository;
import com.mursalin.chat_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public User registerNewUser(User newUser) {
        if(!userRepository.existsByUserEmail(newUser.getUserEmail())) {
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        }else {
            throw new RuntimeException("already has an account with this email");
        }

    }

    @Override
    public User login(User user) {
        Optional<User> optionalUser = userRepository.findUserByUserEmail(user.getUserEmail());
        if(optionalUser.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                return optionalUser.get();
            }
            throw new RuntimeException("Invalid userEmail or password");
        }
        throw new RuntimeException("No account found with this email");
    }
}
