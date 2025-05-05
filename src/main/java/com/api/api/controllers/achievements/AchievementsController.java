package com.api.api.controllers.achievements;


import com.api.api.entities.achievements.Achievement;
import com.api.api.entities.achievements.UserAchievement;
import com.api.api.entities.achievements.UserAchievementsDTO;
import com.api.api.services.achievements.AchievementsService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/achievements")
public class AchievementsController {

    private final AchievementsService userAchievementService;


    @GetMapping("/")
    public List<Achievement> getAllAchievements() {
        return userAchievementService.getAllAchievements();
    }

    @GetMapping("/user/")
    public List<UserAchievementsDTO> getAchievementsByUserId(@RequestHeader("X-User-Id") long id) {
        return userAchievementService.getAchievementsByUserId(id);
    }

    @PostMapping("/user/add")
    public ResponseEntity<List<UserAchievement>> addAchievementsForUser(@RequestHeader("X-User-Id") long id,
                                                                        @RequestBody AchievementData data) {
        List<UserAchievement> userAchievements = userAchievementService.addAchievementsForUser(id, data.ids);
        return ResponseEntity.ok(userAchievements);
    }

    @Setter
    public static class AchievementData {
        private List<Long> ids;
    }
}

