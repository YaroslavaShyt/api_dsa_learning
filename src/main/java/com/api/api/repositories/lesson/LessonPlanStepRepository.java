package com.api.api.repositories.lesson;

import com.api.api.entities.lesson.plan.LessonPlanStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonPlanStepRepository extends JpaRepository<LessonPlanStep, Long> {
}