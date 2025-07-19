package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.dto.LoginRequestDto;
import com.mursalin.chat_app.dto.PasswordResetRequest;
import com.mursalin.chat_app.dto.RegistrationRequestDto;
import com.mursalin.chat_app.model.PasswordResetToken;
import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.repository.PasswordResetTokenRepository;
import com.mursalin.chat_app.repository.UserRepository;
import com.mursalin.chat_app.service.AuthService;
import com.mursalin.chat_app.utils.MailSenderUtil;
import jakarta.validation.Valid;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final MongoService mongoService;
    private final PasswordResetTokenRepository resetTokenRepository;
    private final MailSenderUtil mailSender;

    @Override
    public ResponseEntity<String> registerNewUser(RegistrationRequestDto newUser) {
        if(!userRepository.existsByUserEmailIgnoreCase(newUser.getUserEmail())) {
            User user = User.builder()
                    .username(newUser.getUsername())
                    .userEmail(newUser.getUserEmail())
                    .password(encoder.encode(newUser.getPassword()))
                    .build();

            userRepository.save(user);
            return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Already exist user with this email", HttpStatus.CONFLICT);
        }

    }

    @Override
    public ResponseEntity<String> login(LoginRequestDto user) {
        Optional<User> optionalUser = userRepository.findUserByUserEmailIgnoreCase(user.getUserEmail());
        if(optionalUser.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                return new ResponseEntity<>("User login successfull", HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid password, Please use correct one", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("No account found with this email", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> handleForgotPassword(String email) {
        if(userRepository.existsByUserEmailIgnoreCase(email)) {
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
        Optional<PasswordResetToken> resetTokenOptional = resetTokenRepository.findByEmailAndTokenIgnoreCase(resetRequest.getEmail(), resetRequest.getToken());

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
