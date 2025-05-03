package com.api.api.controllers.statistics;

import java.time.LocalDate;
import java.util.Date;

public class UserLearnedLessonsDTO {
    UserLearnedLessonsDTO(String lessonName, LocalDate date, int timeSpent) {
        this.lessonName = lessonName;
        this.date = date;
        this.timeSpent = timeSpent;
    }

    public String lessonName;

    public int timeSpent;

    public LocalDate date;
}
