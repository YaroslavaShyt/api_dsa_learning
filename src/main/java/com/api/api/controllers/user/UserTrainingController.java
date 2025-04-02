package com.api.api.controllers.user;

import com.api.api.entities.streak.Streak;
import com.api.api.entities.user.UserTraining;
import com.api.api.repositories.user.UserTrainingRepository;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class UserTrainingController {

    @Autowired
    private UserTrainingRepository userTrainingRepository;

    @GetMapping("/user")
    public ResponseEntity<List<Long>> getUserTrainings(@RequestHeader("X-User-Id") Long userId) {
        List<Long> trainingIds = userTrainingRepository.findTrainingIdsByUserId(userId);
        return ResponseEntity.ok(trainingIds);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeTraining(@RequestHeader("X-User-Id") Long userId,
                                                   @RequestParam("training-id") Long trainingId,
                                                   @RequestBody TrainingStatusRequest trainingStatusRequest
                                                   ) {
        UserTraining userTraining = new UserTraining(userId, trainingId, trainingStatusRequest.time, LocalDate.now());
        userTrainingRepository.save(userTraining);
        return ResponseEntity.ok("Training completed successfully!");
    }

    @Setter
    public static class TrainingStatusRequest {
        private int time;
    }
}

