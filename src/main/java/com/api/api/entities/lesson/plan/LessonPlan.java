package com.api.api.entities.lesson.plan;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "LessonPlan", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class LessonPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step1_id", nullable = false)
    private LessonPlanStep step1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step2_id", nullable = false)
    private LessonPlanStep step2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step3_id", nullable = false)
    private LessonPlanStep step3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step4_id", nullable = false)
    private LessonPlanStep step4;
}
