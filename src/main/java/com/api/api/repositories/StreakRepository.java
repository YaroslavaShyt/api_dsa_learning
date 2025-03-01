package com.api.api.repositories;

import com.api.api.entities.Streak;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StreakRepository extends JpaRepository<Streak, Integer> {
    List<Streak> findByUserIdAndDateGreaterThanEqual(Long userId, LocalDate localDate);

    void deleteByUserIdAndDateBefore(Long userId, LocalDate localDate);
}
