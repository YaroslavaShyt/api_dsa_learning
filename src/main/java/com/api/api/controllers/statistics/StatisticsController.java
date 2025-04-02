package com.api.api.controllers.statistics;

import com.api.api.services.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/")
    public Map<Integer, Map<String, Integer>> getStatistics() {
        return statisticsService.getLastThreeMonthsStatistics();
    }
}
