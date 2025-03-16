package com.api.api.entities.lesson.game;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "game", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(nullable = false)
    private int timeLimit;

}

