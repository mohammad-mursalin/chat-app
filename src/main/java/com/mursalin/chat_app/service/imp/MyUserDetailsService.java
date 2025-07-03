package com.mursalin.chat_app.service.imp;

import com.mursalin.chat_app.model.User;
import com.mursalin.chat_app.model.UserPrinciples;
import com.mursalin.chat_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = repo.findUserByUserEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with this email"));

        return new UserPrinciples(user);

    }
}
