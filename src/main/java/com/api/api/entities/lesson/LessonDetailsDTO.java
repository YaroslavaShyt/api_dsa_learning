package com.api.api.entities.lesson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDetailsDTO {

    private String lessonTitle;
    private String step1Plan;
    private String step2Plan;
    private String step3Plan;
    private String step4Plan;
    private String theoryStep1;
    private String theoryStep2;
    private String theoryStep3;
    private String theoryStep4;



    public LessonDetailsDTO(String lessonTitle, String step1Plan, String step2Plan, String step3Plan, String step4Plan,
                            String theoryStep1, String theoryStep2, String theoryStep3, String theoryStep4) {
        this.lessonTitle = lessonTitle;
        this.step1Plan = step1Plan;
        this.step2Plan = step2Plan;
        this.step3Plan = step3Plan;
        this.step4Plan = step4Plan;
        this.theoryStep1 = theoryStep1;
        this.theoryStep2 = theoryStep2;
        this.theoryStep3 = theoryStep3;
        this.theoryStep4 = theoryStep4;
    }


}

