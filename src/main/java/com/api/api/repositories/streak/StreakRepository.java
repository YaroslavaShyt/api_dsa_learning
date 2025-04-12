package com.api.api.repositories.streak;

import com.api.api.entities.streak.Streak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StreakRepository extends JpaRepository<Streak, Integer> {
    List<Streak> findByUserIdAndDateGreaterThanEqual(Long userId, LocalDate localDate);

    void deleteByUserIdAndDateBefore(Long userId, LocalDate localDate);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM streaks WHERE user_id = :userId", nativeQuery = true)
    void deleteAllByUserId(@Param("userId") Long userId);

    Optional<Streak> findByUserIdAndDate(Long userId, LocalDate targetDate);
}
