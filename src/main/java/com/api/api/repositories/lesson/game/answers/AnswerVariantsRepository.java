package com.api.api.repositories.lesson.game.answers;


import com.api.api.entities.lesson.answers.AnswerVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerVariantsRepository extends JpaRepository<AnswerVariants, Long> {
}
