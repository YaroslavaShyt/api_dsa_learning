package com.api.api.controllers.user;

import com.api.api.entities.user.User;
import com.api.api.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(Long id) {
        User user = userRepository.findById(id).orElse(null);

        assert user != null;
        System.out.println("fans updated last " + user.getFansUpdatedLast() );

        long hoursSinceLastUpdate = Duration.between(user.getFansUpdatedLast(), LocalDateTime.now()).toHours();
        System.out.println("hoursSinceLastUpdate = " + hoursSinceLastUpdate);
        if(user.getFans() < 5 && hoursSinceLastUpdate > 4.5){
            int newFans = (int) (user.getFans() + hoursSinceLastUpdate / 4.5);
            System.out.println(newFans);
            if(newFans > 5){
                newFans = 5;
            }
            user.setFans(newFans);
            user.setFansUpdatedLast(LocalDateTime.now());

        }
        return userRepository.saveAndFlush(user);
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
            if(user.getFans() < existingUser.getFans()){
                existingUser.setFansUpdatedLast(LocalDateTime.now());
            }
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

        existingUser.setSound(user.isSound());
        existingUser.setVibration(user.isVibration());

        return userRepository.save(existingUser);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
