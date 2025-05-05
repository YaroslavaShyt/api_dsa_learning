package com.api.api.controllers.streak;

import com.api.api.entities.streak.Streak;
import com.api.api.services.streak.StreakService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/streak")
public class StreakController {

    private final StreakService streakService;

    @GetMapping("/")
    public List<StreakDTO> getStreak(@RequestHeader("X-User-Id") long id) {
        return streakService.getStreakForUser(id);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateStreak(@RequestHeader("X-User-Id") long id, @RequestBody StreakStatusRequest request) {
        streakService.updateStreakForUser(id, request.status, request.date);
        return ResponseEntity.ok("Streak updated successfully");
    }

    @Setter
    public static class StreakStatusRequest {
        private Streak.StreakStatus status;
        private LocalDate date;
    }
}
