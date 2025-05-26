package com.api.api.entities.lesson.game;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class GroupedGameDetailsDTO {
    private Long gameId;
    private String gameName;
    private int timeLimit;
    private Map<String, List<GameTaskDTO>> tasksByLevel;
}
