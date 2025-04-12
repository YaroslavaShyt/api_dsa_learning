package com.api.api.repositories.achievements;


import com.api.api.entities.achievements.UserAchievement;
import com.api.api.entities.achievements.UserAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAchievementsRepository extends JpaRepository<UserAchievement, UserAchievementId> {

    List<UserAchievement> findByIdUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_achievements WHERE user_id = :userId", nativeQuery = true)
    void deleteAllByUserId(@Param("userId") Long userId);
}

