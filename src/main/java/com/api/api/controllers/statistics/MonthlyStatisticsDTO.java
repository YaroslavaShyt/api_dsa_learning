package com.api.api.controllers.statistics;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class MonthlyStatisticsDTO {
    private int month;
    private String category;
    private Long totalTime;

    public MonthlyStatisticsDTO(int month, String category, Long totalTime) {
        this.month = month;
        this.category = category;
        this.totalTime = totalTime;
    }

}

