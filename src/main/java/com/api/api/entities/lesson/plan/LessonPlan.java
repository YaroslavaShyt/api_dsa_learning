package com.api.api.entities.lesson.plan;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "lesson_plan", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class LessonPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "step1_id", nullable = false)
    private LessonPlanStep step1_id;

    @ManyToOne
    @JoinColumn(name = "step2_id", nullable = false)
    private LessonPlanStep step2_id;

    @ManyToOne
    @JoinColumn(name = "step3_id", nullable = false)
    private LessonPlanStep step3_id;

    @ManyToOne
    @JoinColumn(name = "step4_id", nullable = false)
    private LessonPlanStep step4_id;

    public Map<String, String> getStepsMap() {
        Map<String, String> stepsMap = new HashMap<>();
        if (step1_id != null) {
            stepsMap.put("step1", step1_id.getName());
        }
        if (step2_id != null) {
            stepsMap.put("step2", step2_id.getName());
        }
        if (step3_id != null) {
            stepsMap.put("step3", step3_id.getName());
        }
        if (step4_id != null) {
            stepsMap.put("step4", step4_id.getName());
        }
        return stepsMap;
    }
}
