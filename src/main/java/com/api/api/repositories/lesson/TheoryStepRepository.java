package com.api.api.repositories.lesson;

import com.api.api.entities.lesson.theory.TheoryStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheoryStepRepository extends JpaRepository<TheoryStep, Long> {
}