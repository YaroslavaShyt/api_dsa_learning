package com.api.api.entities.lesson;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class LessonSummaryDTO {

    private Long lessonId;
    private Long gameId;
    private String topicName;
    private Long categoryId;
    private Long topicId;
    private String lessonTitle;
    private Map<String, String> lessonPlanSteps;

    public LessonSummaryDTO(Long lessonId, Long gameId, String topicName, String lessonTitle,  Map<String, String> lessonPlanSteps, Long topicId, Long categoryId) {
        this.lessonId = lessonId;
        this.gameId = gameId;
        this.topicName = topicName;
        this.lessonTitle = lessonTitle;
        this.lessonPlanSteps = lessonPlanSteps;
        this.topicId = topicId;
        this.categoryId = categoryId;
    }
}

