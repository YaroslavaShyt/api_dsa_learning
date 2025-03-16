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

    @GetMapping("/user/")
    public List<UserAchievementsDTO> getAchievementsByUserId(@RequestHeader("X-User-Id") long id) {
        return userAchievementService.getAchievementsByUserId(id);
    }

    @PostMapping("/user/add/{achievementId}")
    public ResponseEntity<UserAchievement> addAchievementForUser(@RequestHeader("X-User-Id") long id,
                                                                 @PathVariable Long achievementId) {
        UserAchievement userAchievement = userAchievementService.addAchievementForUser(id, achievementId);
        return ResponseEntity.ok(userAchievement);
    }
}

