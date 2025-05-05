package com.api.api.repositories.lesson.game;


import com.api.api.entities.lesson.game.GameTaskToGame;
import com.api.api.entities.lesson.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameTaskToGameRepository extends JpaRepository<GameTaskToGame, Long> {
    List<GameTaskToGame> findByGame(Game game);
}
