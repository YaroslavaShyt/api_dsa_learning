package com.api.api.entities.lesson.plan;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "lesson_plan", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class LessonPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "step1_id", nullable = false)
    private LessonPlanStep step1_id;

    @ManyToOne
    @JoinColumn(name = "step2_id", nullable = false)
    private LessonPlanStep step2_id;

    @ManyToOne
    @JoinColumn(name = "step3_id", nullable = false)
    private LessonPlanStep step3_id;

    @ManyToOne
    @JoinColumn(name = "step4_id", nullable = false)
    private LessonPlanStep step4_id;
}
