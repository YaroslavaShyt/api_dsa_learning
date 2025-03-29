package com.api.api.controllers.user;

import com.api.api.entities.user.UserTraining;
import com.api.api.repositories.user.UserTrainingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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
                                                   @RequestParam("training-id") Long trainingId) {
        UserTraining userTraining = new UserTraining(userId, trainingId);
        userTrainingRepository.save(userTraining);
        return ResponseEntity.ok("Training completed successfully!");
    }
}

