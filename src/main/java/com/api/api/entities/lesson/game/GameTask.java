package com.api.api.entities.lesson.game;

import com.api.api.entities.lesson.answers.AnswerVariants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "game_task", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class GameTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_number", nullable = false)
    private int questionNumber;

    @Column(nullable = false)
    private String question;

    @ManyToOne
    @JoinColumn(name = "answers", nullable = false)
    private AnswerVariants answers;

    @ManyToOne
    @JoinColumn(name = "task_answers_type", nullable = false)
    private GameTaskAnswersType answersType;
}

