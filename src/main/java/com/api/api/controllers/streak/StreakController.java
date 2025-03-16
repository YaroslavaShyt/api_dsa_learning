package com.api.api.controllers.streak;

import com.api.api.entities.streak.Streak;
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

    @GetMapping("/")
    public List<Streak> getStreak(@RequestHeader("X-User-Id") long id) {
        return streakService.getStreakForUser(id);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateStreak(@RequestHeader("X-User-Id") long id, @RequestBody StreakStatusRequest request) {
        streakService.updateStreakForUser(id, request.status);
        return ResponseEntity.ok("Streak updated successfully");
    }

    @Setter
    public static class StreakStatusRequest {
        private Streak.StreakStatus status;

    }
}
