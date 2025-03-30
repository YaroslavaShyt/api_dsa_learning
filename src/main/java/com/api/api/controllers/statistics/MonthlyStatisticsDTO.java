package com.api.api.controllers.statistics;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyStatisticsDTO {
    private int month;
    private String category;
    private int totalTime;
}

