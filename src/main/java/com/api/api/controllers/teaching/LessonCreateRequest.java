package com.api.api.controllers.teaching;


import com.api.api.entities.lesson.game.GameTaskDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class LessonCreateRequest {
    private String lessonTitle;

    private String step1Plan;
    private String step2Plan;
    private String step3Plan;
    private String step4Plan;

    private String theoryStep1;
    private String theoryStep2;
    private String theoryStep3;
    private String theoryStep4;
    private MultipartFile theoryImageStep1;
    private MultipartFile theoryImageStep2;
    private MultipartFile theoryImageStep3;
    private MultipartFile theoryImageStep4;

    private Long topicId;
    private Long gameId;
    private String gameName;
    private int timeLimit;
    private List<GameTaskDTO> gameTasks;
}

