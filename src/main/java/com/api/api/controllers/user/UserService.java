package com.api.api.controllers.user;

import com.api.api.entities.user.Admin;
import com.api.api.entities.user.User;
import com.api.api.repositories.user.AdminRepository;
import com.api.api.repositories.user.UserRepository;
import com.api.api.repositories.user.UserTrainingRepository;
import com.api.api.services.achievements.AchievementsService;
import com.api.api.services.streak.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserTrainingRepository userTrainingRepository;

    @Autowired
    AchievementsService achievementsService;

    @Autowired
    StreakService  streakService;

    public Admin getAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElse(null);

        assert admin != null;

        return admin;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(Long id) {
        User user = userRepository.findById(id).orElse(null);

        assert user != null;
        System.out.println("fans updated last " + user.getFansUpdatedLast() );

        LocalDateTime fansUpdatedLast = user.getFansUpdatedLast();

        if(fansUpdatedLast == null) {
            fansUpdatedLast = LocalDateTime.now();
            user.setFansUpdatedLast(LocalDateTime.now());

        }
            long hoursSinceLastUpdate = Duration.between(fansUpdatedLast, LocalDateTime.now()).toHours();
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
        existingUser.setAnimations(user.isAnimations());
        existingUser.set_onboarded(user.is_onboarded());

        return userRepository.save(existingUser);
    }


    @Transactional
    public void delete(Long id) {
        achievementsService.deleteUserAchievements(id);
        userTrainingRepository.deleteAllByUserId(id);
        streakService.deleteUserStreak(id);
        userRepository.deleteById(id);
    }
}
