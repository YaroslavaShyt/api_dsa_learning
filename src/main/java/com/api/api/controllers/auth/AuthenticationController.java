package com.api.api.controllers.auth;

import com.api.api.services.auth.JwtService;
import com.api.api.entities.User;
import com.api.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public Object registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }

        User newUser = new User(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                passwordEncoder.encode(user.getPassword())
        );
        userRepository.save(newUser);

        String generatedToken = jwtService.generateToken(newUser.getUsername());

        return new Object() {
            public String token = generatedToken;
            public Long userId = newUser.getId();
        };
    }

    @PostMapping("/signin")
    public Object authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User authenticatedUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String generatedToken = jwtService.generateToken(userDetails.getUsername());

        return new Object() {
            public String token = generatedToken;
            public Long userId = authenticatedUser.getId();
        };
    }

}
