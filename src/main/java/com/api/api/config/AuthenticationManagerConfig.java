package com.api.api.config;

import com.api.api.controllers.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfig {

    private final CustomUserDetailsService userDetailsService;
    private final AdminAuthenticationProvider adminAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider userAuthProvider = new DaoAuthenticationProvider();
        userAuthProvider.setUserDetailsService(userDetailsService);
        userAuthProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(
                List.of(adminAuthenticationProvider, userAuthProvider)
        );
    }
}

