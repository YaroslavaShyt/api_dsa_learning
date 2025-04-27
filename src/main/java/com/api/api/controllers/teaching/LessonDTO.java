package com.api.api.controllers.teaching;


import com.api.api.entities.lesson.game.Game;
import com.api.api.entities.lesson.plan.LessonPlan;
import com.api.api.entities.lesson.theory.Theory;
import com.api.api.entities.topic.Topic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDTO {
    private Long id;
    private String title;
    private Topic topic;
    private Game game;
    private Theory theory;
    private LessonPlan lessonPlan;
}

