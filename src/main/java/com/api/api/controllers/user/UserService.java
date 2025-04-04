package com.api.api.controllers.user;

import com.api.api.entities.user.User;
import com.api.api.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getBytes() != 0) {
            existingUser.setBytes(user.getBytes());
        }
        if (user.getFans() != 0) {
            existingUser.setFans(user.getFans());
        }
        if (user.getHash() != 0) {
            existingUser.setHash(user.getHash());
        }
        if (user.getAvatars() != null) {
            existingUser.setAvatars(user.getAvatars());
        }
        if (user.getCurrentAvatar() != null) {
            existingUser.setCurrentAvatar(user.getCurrentAvatar());
        }

        return userRepository.save(existingUser);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
