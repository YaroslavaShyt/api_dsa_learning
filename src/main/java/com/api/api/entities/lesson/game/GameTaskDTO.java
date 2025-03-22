package com.api.api.entities.lesson.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class GameTaskDTO {
    private Long taskId;
    private int questionNumber;
    private String question;
    private List<String> answerOptions;
    private String correctAnswer;
    private String gameAnswersType;

    public GameTaskDTO(Long taskId, int questionNumber, String question, List<String> answerOptions, String correctAnswer, String gameAnswersType) {
        this.taskId = taskId;
        this.questionNumber = questionNumber;
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
        this.gameAnswersType = gameAnswersType;
    }
}
