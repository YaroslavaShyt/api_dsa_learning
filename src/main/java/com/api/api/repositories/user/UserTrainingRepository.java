package com.api.api.repositories.user;

import com.api.api.controllers.statistics.MonthlyStatisticsDTO;
import com.api.api.controllers.statistics.UserLearnedLessonsDTO;
import com.api.api.entities.user.UserTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface UserTrainingRepository extends JpaRepository<UserTraining, Long> {

    @Query("SELECT ut.trainingId, category.name " +
            "FROM UserTraining ut " +
            "JOIN ut.training t " +
            "JOIN t.topic topic " +
            "JOIN topic.category category " +
            "WHERE ut.userId = :userId " +
            "AND category.name IN ('ALGORITHMS', 'DATA_STRUCTURES')")
    List<Object[]> findTrainingIdsByUserIdAndCategories(@Param("userId") Long userId);



    @Query("SELECT new com.api.api.controllers.statistics.MonthlyStatisticsDTO(" +
            "EXTRACT(MONTH FROM ut.date), " +
            "lc.category.name, " +
            "SUM(ut.time)) " +
            "FROM UserTraining ut " +
            "JOIN ut.training t " +
            "JOIN t.topic lc " +
            "JOIN lc.category category " +
            "WHERE ut.userId = :userId " +
            "AND ut.date BETWEEN :startDate AND :endDate " +
            "GROUP BY EXTRACT(MONTH FROM ut.date), lc.category.name " +
            "ORDER BY EXTRACT(MONTH FROM ut.date) ASC")
    List<MonthlyStatisticsDTO> findStatisticsBetweenDatesAndUser(@Param("userId") Long userId,
                                                                 @Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_trainings WHERE user_id = :userId", nativeQuery = true)
    void deleteAllByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.api.api.controllers.statistics.UserLearnedLessonsDTO(" +
            "t.title, ut.date, ut.time) " +
            "FROM UserTraining ut " +
            "JOIN ut.training t " +
            "WHERE ut.userId = :userId")
    List<UserLearnedLessonsDTO> findUserLearnedLessonsByUserId(@Param("userId") Long userId);

}

