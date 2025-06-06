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
    private Long gameAnswersTypeId;
    private String taskLevel;

    public GameTaskDTO(Long taskId, int questionNumber, String question, List<String> answerOptions, String correctAnswer, Long gameAnswersTypeId, String taskLevel) {
        this.taskId = taskId;
        this.questionNumber = questionNumber;
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
        this.gameAnswersTypeId = gameAnswersTypeId;
        this.taskLevel = taskLevel;
    }

    public GameTaskDTO() {}
}
