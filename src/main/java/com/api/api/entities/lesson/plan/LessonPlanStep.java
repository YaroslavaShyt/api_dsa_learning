package com.api.api.entities.lesson.plan;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "LessonPlanStep", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class LessonPlanStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String name;
}
