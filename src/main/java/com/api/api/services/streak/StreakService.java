package com.api.api.services.streak;

import com.api.api.controllers.streak.StreakDTO;
import com.api.api.entities.streak.Streak;
import com.api.api.entities.user.User;
import com.api.api.repositories.streak.StreakRepository;
import com.api.api.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class StreakService {

    private final StreakRepository streakRepository;

    private final UserRepository userRepository;

    public List<StreakDTO> getStreakForUser(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate weekAgo = today.minusDays(6);

        List<Streak> existingStreaks = streakRepository.findByUserIdAndDateGreaterThanEqual(userId, weekAgo);

        Set<LocalDate> existingDates = existingStreaks.stream()
                .map(Streak::getDate)
                .collect(Collectors.toSet());

        List<StreakDTO> streakDTOs = existingStreaks.stream()
                .map(s -> new StreakDTO(s.getDate(), s.getStatus()))
                .toList();

        return IntStream.rangeClosed(0, 6)
                .mapToObj(weekAgo::plusDays)
                .map(date -> existingDates.contains(date)
                        ? streakDTOs.stream().filter(s -> s.getDate().equals(date)).findFirst().orElse(null)
                        : new StreakDTO(date, Streak.StreakStatus.NOT_LEARNED))
                .collect(Collectors.toList());
    }

    public void updateStreakForUser(Long userId, Streak.StreakStatus status, LocalDate date) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }

        User user = optionalUser.get();

        Optional<Streak> existingStreak = streakRepository.findByUserIdAndDate(userId, targetDate);

        Streak streak;
        if (existingStreak.isPresent()) {
            streak = existingStreak.get();
        } else {
            streak = new Streak();
            streak.setUser(user);
            streak.setDate(targetDate);
        }
        streak.setStatus(status);
        streakRepository.save(streak);
    }

    public void deleteUserStreak(Long userId) {
        streakRepository.deleteAllByUserId(userId);
    }
}

