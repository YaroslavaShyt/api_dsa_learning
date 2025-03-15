package com.api.api.entities.lesson.theory;

import com.api.api.entities.lesson.plan.LessonPlan;
import com.api.api.entities.lesson.plan.LessonPlanStep;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "TheoryStep", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class TheoryStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String theory_text;

    @Column(nullable = false)
    private String theory_image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private LessonPlan plan_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private LessonPlanStep plan_step_id;
}
