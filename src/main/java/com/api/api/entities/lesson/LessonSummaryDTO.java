package com.api.api.entities.lesson;


import com.api.api.entities.lesson.plan.LessonPlan;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class LessonSummaryDTO {

    private Long lessonId;
    private Long gameId;
    private String topicName;
    private String lessonTitle;
    private Map<String, String> lessonPlanSteps;

    public LessonSummaryDTO(Long lessonId, Long gameId, String topicName, String lessonTitle,  Map<String, String> lessonPlanSteps) {
        this.lessonId = lessonId;
        this.gameId = gameId;
        this.topicName = topicName;
        this.lessonTitle = lessonTitle;
        this.lessonPlanSteps = lessonPlanSteps;
    }
}

