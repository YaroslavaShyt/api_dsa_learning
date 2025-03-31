package com.api.api.controllers.statistics;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class MonthlyStatisticsDTO {
    private int month;
    private String category;
    private Long totalTime;

    public MonthlyStatisticsDTO(int month,
                                String category, Long totalTime) {
        this.month = month;
        this.category = category;
        this.totalTime = totalTime;
    }

//    public MonthlyStatisticsDTO(//Date month,
//                                String category, int totalTime) {
//     //   this.month = month != null ? month.getMonth() + 1 : -1; // Using .getMonth() to extract the month from Date
//        this.category = category;
//        this.totalTime = totalTime;
//    }
}

