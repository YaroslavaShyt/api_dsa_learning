package com.api.api.repositories.lesson.game;

import com.api.api.entities.lesson.game.GameTask;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GameTaskRepository extends JpaRepository<GameTask, Long> {
}