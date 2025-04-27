package com.api.api.repositories.lesson;

import com.api.api.entities.lesson.plan.LessonPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonPlanRepository extends JpaRepository<LessonPlan, Long> {
}