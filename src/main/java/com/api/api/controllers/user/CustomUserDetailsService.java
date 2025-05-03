package com.api.api.controllers.user;

import com.api.api.entities.user.Admin;
import com.api.api.entities.user.User;
import com.api.api.repositories.user.AdminRepository;
import com.api.api.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                Collections.emptyList()
        );
    }

    public UserDetails loadAdminByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isEmpty()) {
            throw new UsernameNotFoundException("Admin User Not Found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                admin.get().getUsername(),
                admin.get().getPassword(),
                Collections.emptyList()
        );
    }
}
