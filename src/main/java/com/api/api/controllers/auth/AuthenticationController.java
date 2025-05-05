package com.api.api.controllers.auth;

import com.api.api.entities.user.Admin;
import com.api.api.repositories.user.AdminRepository;
import com.api.api.services.auth.JwtService;
import com.api.api.entities.user.User;
import com.api.api.repositories.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AdminRepository adminRepository;

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

        return new AuthResult(generatedToken, newUser.getId());
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

        return new AuthResult(generatedToken, authenticatedUser.getId());
    }

    @PostMapping("/signup/admin")
    public Object registerAdmin(@RequestBody Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            return "Error: Username is already taken!";
        }

        Admin newAdmin = new Admin(
                admin.getFirstName(),
                admin.getUsername(),
                passwordEncoder.encode(admin.getPassword())
        );
        adminRepository.save(newAdmin);

        String generatedToken = jwtService.generateToken(newAdmin.getUsername());

        return new AuthResult(generatedToken, newAdmin.getId());
    }

    @PostMapping("/signin/admin")
    public Object authenticateUser(@RequestBody Admin admin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        admin.getUsername(),
                        admin.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Admin authenticatedUser = adminRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String generatedToken = jwtService.generateToken(userDetails.getUsername());

        return new AuthResult(generatedToken, authenticatedUser.getId());
    }

    @Getter
    @Setter
    public static class AuthResult {
        private String token;
        private Long userId;

        public AuthResult(String generatedToken, Long id) {
            this.token = generatedToken;
            this.userId = id;
        }
    }

}
