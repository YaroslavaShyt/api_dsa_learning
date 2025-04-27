package com.api.api.repositories.lesson.game;

import com.api.api.entities.lesson.answers.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
}