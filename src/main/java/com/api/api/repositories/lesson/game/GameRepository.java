package com.api.api.repositories.lesson.game;

import com.api.api.entities.lesson.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}

