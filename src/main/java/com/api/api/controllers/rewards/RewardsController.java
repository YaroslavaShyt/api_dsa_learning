package com.api.api.controllers.rewards;


import com.api.api.services.rewards.RewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardsController {

    private final RewardsService rewardService;

    @PutMapping("/update")
    public ResponseEntity<String> updateRewards(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam int bytes,
            @RequestParam int hash,
            @RequestParam int fans) {

        rewardService.updateAllRewards(userId, bytes, fans, hash);

        return ResponseEntity.ok("Нагороди оновлено успішно.");

    }
}
