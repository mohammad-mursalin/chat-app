package com.mursalin.chat_app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService myUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/chat-app/user/registration",
                                        "/chat-app/user/login",
                                        "/register", "/login", "/forgot-password", "/chat-app/forgot-password", "/chat-app/auth/user/reset-password", "/chat-app/password-reset").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login") // shows login.html
                        .loginProcessingUrl("/perform_login") // where the form submits
                        .defaultSuccessUrl("/homepage", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll())

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myUserDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
