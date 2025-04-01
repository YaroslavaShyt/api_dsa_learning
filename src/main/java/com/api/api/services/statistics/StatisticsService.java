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
         String algorithms = "ALGORITHMS";
         String dataStructures = "DATA_STRUCTURES";
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);

        List<MonthlyStatisticsDTO> statistics = userTrainingRepository
                .findStatisticsBetweenDates(threeMonthsAgo, currentDate);

        Map<Integer, Map<String, Integer>> result = new HashMap<>();


        for (MonthlyStatisticsDTO stat : statistics) {
            int month = stat.getMonth();
            String category = stat.getCategory();
            Long totalTime = stat.getTotalTime();


            result.computeIfAbsent(month, k -> new HashMap<>());
            result.get(month).putIfAbsent(algorithms, 0);
            result.get(month).putIfAbsent(dataStructures, 0);

            if (algorithms.equals(category)) {
                result.get(month).put(algorithms, result.get(month).get(algorithms) + totalTime.intValue());
            } else if (dataStructures.equals(category)) {
                result.get(month).put(dataStructures, result.get(month).get(dataStructures) + totalTime.intValue());
            }
        }

        for (int i = 1; i <= 3; i++) {
            int month = currentDate.minusMonths(i).getMonthValue();
            result.putIfAbsent(month, new HashMap<>());
            result.get(month).putIfAbsent(algorithms, 0);
            result.get(month).putIfAbsent(dataStructures, 0);
        }

        return result;
    }

}
