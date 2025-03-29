package com.api.api.repositories.user;

import com.api.api.entities.user.UserTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserTrainingRepository extends JpaRepository<UserTraining, Long> {

    @Query("SELECT ut.trainingId FROM UserTraining ut WHERE ut.userId = :userId")
    List<Long> findTrainingIdsByUserId(@Param("userId") Long userId);
}

