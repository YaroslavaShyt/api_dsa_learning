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
import java.util.*;

@RestController
@RequestMapping("/api/trainings")
public class UserTrainingController {

    @Autowired
    private UserTrainingRepository userTrainingRepository;

    @GetMapping("/user")
    public ResponseEntity<Map<String, List<Long>>> getUserTrainings(@RequestHeader("X-User-Id") Long userId) {
            List<Object[]> results = userTrainingRepository.findTrainingIdsByUserIdAndCategories(userId);

            Map<String, List<Long>> trainingIdsByCategory = new HashMap<>();
            trainingIdsByCategory.put("ALGORITHMS", new ArrayList<>());
            trainingIdsByCategory.put("DATA_STRUCTURES", new ArrayList<>());

            for (Object[] result : results) {
                Long trainingId = (Long) result[0];
                String categoryName = (String) result[1];

                if ("ALGORITHMS".equals(categoryName)) {
                    trainingIdsByCategory.get("ALGORITHMS").add(trainingId);
                } else if ("DATA_STRUCTURES".equals(categoryName)) {
                    trainingIdsByCategory.get("DATA_STRUCTURES").add(trainingId);
                }
            }

        return ResponseEntity.ok(trainingIdsByCategory);
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

