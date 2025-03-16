package com.api.api.entities.lesson.game;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class GameTaskToGameId implements Serializable {

    private Integer game;
    private Integer gameTask;

    public GameTaskToGameId() {}

    public GameTaskToGameId(Integer game, Integer gameTask) {
        this.game = game;
        this.gameTask = gameTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameTaskToGameId that = (GameTaskToGameId) o;
        return Objects.equals(game, that.game) && Objects.equals(gameTask, that.gameTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, gameTask);
    }
}

