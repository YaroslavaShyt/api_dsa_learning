package com.api.api.services.streak;

import com.api.api.entities.Streak;
import com.api.api.entities.User;
import com.api.api.repositories.StreakRepository;
import com.api.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StreakService {

    @Autowired
    private StreakRepository streakRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Streak> getStreakForUser(Long userId) {
        LocalDate today = LocalDate.now();
        return streakRepository.findByUserIdAndDateGreaterThanEqual(userId, today.minusDays(7));
    }

    public void updateStreakForUser(Long userId, Streak.StreakStatus status) {
        LocalDate today = LocalDate.now();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }

        User user = optionalUser.get();

        Streak streak = new Streak();
        streak.setUser(user);
        streak.setDate(today);
        streak.setStatus(status);

        streakRepository.save(streak);
    }


    public void cleanupOldStreaks(Long userId) {
        LocalDate today = LocalDate.now();
        streakRepository.deleteByUserIdAndDateBefore(userId, today.minusDays(7));
    }

}

