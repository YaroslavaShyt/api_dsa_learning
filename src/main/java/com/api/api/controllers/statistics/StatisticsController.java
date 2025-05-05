package com.api.api.controllers.statistics;

import com.api.api.services.statistics.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/")
    public Map<Integer, Map<String, Integer>> getStatistics(@RequestHeader("X-User-Id") Long userId) {
        return statisticsService.getLastThreeMonthsStatistics(userId);
    }

    @GetMapping("/teaching/{id}")
    public List<UserLearnedLessonsDTO> getUserLessonsStatistics(@PathVariable Long id) {
        return statisticsService.getUserLearnedLessons(id);
    }
}
