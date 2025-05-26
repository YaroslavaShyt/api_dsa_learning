package com.api.api.entities.lesson.game;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@IdClass(GameTaskToGameId.class)
@Table(name = "game_task_to_game", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class GameTaskToGame {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "game_task_id", nullable = false)
    private GameTask gameTask;

    @Column(name = "task_level")
    private String task_level;

    public GameTaskToGame(Game game, GameTask gameTask) {
        this.game = game;
        this.gameTask = gameTask;
    }

    public GameTaskToGame() {

    }
}
