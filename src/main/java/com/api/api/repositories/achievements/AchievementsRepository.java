package com.api.api.repositories.achievements;


import com.api.api.entities.achievements.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementsRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findAll();
}
