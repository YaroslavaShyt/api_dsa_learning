package com.api.api.services.achievements;


import com.api.api.entities.achievements.Achievement;
import com.api.api.entities.achievements.UserAchievement;
import com.api.api.entities.achievements.UserAchievementId;
import com.api.api.entities.achievements.UserAchievementsDTO;
import com.api.api.repositories.achievements.AchievementsRepository;
import com.api.api.repositories.achievements.UserAchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserAchievementsDTO> getAchievementsByUserId(Long userId) {
        List<UserAchievement> userAchievements = userAchievementRepository.findByIdUserId(userId);
        return userAchievements.stream()
                .map(userAchievement -> {
                    Achievement achievement = achievementRepository.findById(userAchievement.getId().getAchievementId())
                            .orElseThrow(() -> new IllegalArgumentException("Achievement not found"));

                    return new UserAchievementsDTO(
                            userAchievement.getId().getUserId(),
                            userAchievement.getId().getAchievementId(),
                            achievement.getName(),
                            achievement.getDescription(),
                            userAchievement.getCreatedAt().toString()
                    );
                })
                .collect(Collectors.toList());
    }

    public List<UserAchievement> addAchievementsForUser(Long userId, List<Long> achievementIds) {
        List<Achievement> achievements = achievementRepository.findAllById(achievementIds);
        if (achievements.size() != achievementIds.size()) {
            throw new IllegalArgumentException("Some achievements not found");
        }

        List<UserAchievement> userAchievements = new ArrayList<>();
        for (Achievement achievement : achievements) {
            UserAchievementId id = new UserAchievementId(userId, achievement.getId());
            UserAchievement userAchievement = new UserAchievement();
            userAchievement.setId(id);

            userAchievements.add(userAchievement);
        }

        return userAchievementRepository.saveAll(userAchievements);
    }

    public void deleteUserAchievements(Long userId) {
        userAchievementRepository.deleteAllByUserId(userId);
    }

}
