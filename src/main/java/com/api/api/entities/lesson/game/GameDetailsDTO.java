package com.api.api.entities.lesson.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class GameDetailsDTO {
    private Long gameId;
    private String gameName;
    private int timeLimit;
    private List<GameTaskDTO> gameTasks;

    public GameDetailsDTO(Long gameId, String gameName, int timeLimit, List<GameTaskDTO> gameTasks) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.timeLimit = timeLimit;
        this.gameTasks = gameTasks;
    }


}
