package com.api.api.entities.lesson.answers;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "answer_variants", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class AnswerVariants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "correct_answer_id", nullable = false)
    private Answers correctAnswer;

    @ManyToOne
    @JoinColumn(name = "first_answer_id", nullable = false)
    private Answers firstAnswer;

    @ManyToOne
    @JoinColumn(name = "second_answer_id", nullable = false)
    private Answers secondAnswer;

    @ManyToOne
    @JoinColumn(name = "third_answer_id", nullable = false)
    private Answers thirdAnswer;

    @ManyToOne
    @JoinColumn(name = "fourth_answer_id", nullable = false)
    private Answers fourthAnswer;

}
