package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.dto.GroupListResponse;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.model.PasswordResetToken;
import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.repository.PasswordResetTokenRepository;
import com.mursalin.chat_app.repository.UserRepository;
import com.mursalin.chat_app.service.UserService;
import com.mursalin.chat_app.utils.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final MongoService mongoService;
    private final PasswordResetTokenRepository resetTokenRepository;
    private final MailSenderUtil mailSender;

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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<GroupListResponse> getUserGroups(String currentUserId) {
        return mongoService.getUserGroups(currentUserId);
    }

    @Override
    public ResponseEntity<String> handleForgotPassword(String email) {
        if(userRepository.existsByUserEmail(email)) {
            String token = generateToken();
            PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                    .token(token)
                    .email(email)
                    .createdAt(Instant.now())
                    .expiresAt(Instant.now().plus(Duration.ofMinutes(5)))
                    .build();
            resetTokenRepository.save(passwordResetToken);
            mailSender.sendSimpleMail(email, token);
            return new ResponseEntity<>("Check your email for verification token", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No account found with this email", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> resetPassword(PasswordResetRequest resetRequest) {
        Optional<PasswordResetToken> resetTokenOptional = resetTokenRepository.findByEmailandToken(resetRequest.getEmail(), resetRequest.getToken());

        if(resetTokenOptional.isPresent()) {
            PasswordResetToken resetToken = resetTokenOptional.get();
            if(resetToken.getExpiresAt().isAfter(Instant.now())) {
                boolean passwordUpdated = mongoService.updatePasswordByEmail(resetRequest.getEmail(), encoder.encode(resetRequest.getPassword()));
                if(passwordUpdated) {
                    resetTokenRepository.delete(resetToken);
                    return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Password update attempt failed", HttpStatus.NOT_MODIFIED);
                }
            } else {
                resetTokenRepository.delete(resetToken);
                return new ResponseEntity<>("token expired", HttpStatus.REQUEST_TIMEOUT);
            }
        } else {
            return new ResponseEntity<>("invalid token or email", HttpStatus.BAD_REQUEST);
        }
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }


}
