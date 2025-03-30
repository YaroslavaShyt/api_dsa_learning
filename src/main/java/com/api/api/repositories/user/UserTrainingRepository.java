package com.api.api.repositories.user;

import com.api.api.controllers.statistics.MonthlyStatisticsDTO;
import com.api.api.entities.user.UserTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserTrainingRepository extends JpaRepository<UserTraining, Long> {

    @Query("SELECT ut.trainingId FROM UserTraining ut WHERE ut.userId = :userId")
    List<Long> findTrainingIdsByUserId(@Param("userId") Long userId);


    @Query("SELECT new com.api.api.controllers.statistics.MonthlyStatisticsDTO(" +
            "FUNCTION('MONTH', ut.date), " +
            "lc.topicName, " +
            "SUM(ut.time)) " +
            "FROM UserTraining ut " +
            "JOIN ut.training t " +
            "JOIN t.topic lc " +
            "WHERE ut.date BETWEEN :startDate AND :endDate " +
            "GROUP BY EXTRACT(MONTH FROM ut.date), lc.topicName " +
            "ORDER BY EXTRACT(MONTH FROM ut.date) ASC")
    List<MonthlyStatisticsDTO> findStatisticsBetweenDates(LocalDate startDate, LocalDate endDate);

}

