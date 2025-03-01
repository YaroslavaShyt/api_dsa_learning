package com.api.api.services.achievements;


import com.api.api.entities.achievements.Achievement;
import com.api.api.entities.achievements.UserAchievement;
import com.api.api.entities.achievements.UserAchievementId;
import com.api.api.repositories.achievements.AchievementsRepository;
import com.api.api.repositories.achievements.UserAchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementsService {

    private final UserAchievementsRepository userAchievementRepository;
    private final AchievementsRepository achievementRepository;

    @Autowired
    public AchievementsService(UserAchievementsRepository userAchievementRepository,
                                  AchievementsRepository achievementRepository) {
        this.userAchievementRepository = userAchievementRepository;
        this.achievementRepository = achievementRepository;
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public List<UserAchievement> getAchievementsByUserId(Long userId) {
        return userAchievementRepository.findByIdUserId(userId);
    }

    public UserAchievement addAchievementForUser(Long userId, Long achievementId) {
        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new IllegalArgumentException("Achievement not found"));

        UserAchievementId id = new UserAchievementId(userId, achievementId);
        UserAchievement userAchievement = new UserAchievement();
        userAchievement.setId(id);

        return userAchievementRepository.save(userAchievement);
    }
}
