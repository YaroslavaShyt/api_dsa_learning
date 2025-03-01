package com.api.api.controllers.streak;

import com.api.api.entities.Streak;
import com.api.api.services.streak.StreakService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/streak")
public class StreakController {

    @Autowired
    private StreakService streakService;

    @GetMapping("/{userId}")
    public List<Streak> getStreak(@PathVariable Long userId) {
        return streakService.getStreakForUser(userId);
    }

    @PostMapping("/{userId}/update")
    public ResponseEntity<String> updateStreak(@PathVariable Long userId, @RequestBody StreakStatusRequest request) {
        streakService.updateStreakForUser(userId, request.status);
        return ResponseEntity.ok("Streak updated successfully");
    }

    @Setter
    public static class StreakStatusRequest {
        private Streak.StreakStatus status;

    }
}
