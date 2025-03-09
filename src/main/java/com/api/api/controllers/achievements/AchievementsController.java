package com.api.api.controllers.achievements;


import com.api.api.entities.achievements.Achievement;
import com.api.api.entities.achievements.UserAchievement;
import com.api.api.entities.achievements.UserAchievementsDTO;
import com.api.api.services.achievements.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
public class AchievementsController {

    private final AchievementsService userAchievementService;

    @Autowired
    public AchievementsController(AchievementsService userAchievementService) {
        this.userAchievementService = userAchievementService;
    }

    @GetMapping("/")
    public List<Achievement> getAllAchievements() {
        return userAchievementService.getAllAchievements();
    }

    @GetMapping("/user/{userId}")
    public List<UserAchievementsDTO> getAchievementsByUserId(@PathVariable Long userId) {
        return userAchievementService.getAchievementsByUserId(userId);
    }

    @PostMapping("/user/{userId}/add/{achievementId}")
    public ResponseEntity<UserAchievement> addAchievementForUser(@PathVariable Long userId,
                                                                 @PathVariable Long achievementId) {
        UserAchievement userAchievement = userAchievementService.addAchievementForUser(userId, achievementId);
        return ResponseEntity.ok(userAchievement);
    }
}

