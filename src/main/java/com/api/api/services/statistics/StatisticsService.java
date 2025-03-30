package com.api.api.services.statistics;

import com.api.api.controllers.statistics.MonthlyStatisticsDTO;
import com.api.api.repositories.user.UserTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private UserTrainingRepository userTrainingRepository;

    public Map<Integer, Map<String, Integer>> getLastThreeMonthsStatistics() {
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);

        // Fetch statistics from the repository
        List<MonthlyStatisticsDTO> statistics = userTrainingRepository
                .findStatisticsBetweenDates(threeMonthsAgo, currentDate);

        // Initialize the statistics map
        Map<Integer, Map<String, Integer>> result = new HashMap<>();

        // Process the result and aggregate by month and category
        for (MonthlyStatisticsDTO stat : statistics) {
            int month = stat.getMonth();
            String category = stat.getCategory();
            int totalTime = stat.getTotalTime();

            // Initialize month if not already done
            result.computeIfAbsent(month, k -> new HashMap<>());
            result.get(month).putIfAbsent("algorithms", 0);
            result.get(month).putIfAbsent("data_structures", 0);

            // Add the time to the appropriate category
            if ("algorithms".equals(category)) {
                result.get(month).put("algorithms", result.get(month).get("algorithms") + totalTime);
            } else if ("data_structures".equals(category)) {
                result.get(month).put("data_structures", result.get(month).get("data_structures") + totalTime);
            }
        }

        // Ensure that all months from 1 to 3 are included with 0 if missing
        for (int i = 1; i <= 3; i++) {
            result.putIfAbsent(i, new HashMap<>());
            result.get(i).putIfAbsent("algorithms", 0);
            result.get(i).putIfAbsent("data_structures", 0);
        }

        return result;
    }
}
