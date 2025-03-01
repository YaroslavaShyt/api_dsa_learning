package com.api.api.repositories.achievements;


import com.api.api.entities.achievements.UserAchievement;
import com.api.api.entities.achievements.UserAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAchievementsRepository extends JpaRepository<UserAchievement, UserAchievementId> {

    List<UserAchievement> findByIdUserId(Long userId);
}

