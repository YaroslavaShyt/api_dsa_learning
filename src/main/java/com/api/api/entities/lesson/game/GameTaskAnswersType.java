package com.api.api.entities.lesson.game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "game_task_answers_type", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class GameTaskAnswersType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String name;

    public GameTaskAnswersType(String gameAnswersType) {
        this.name = gameAnswersType;
    }

    public GameTaskAnswersType() {

    }
}
