package com.api.api.entities.lesson.theory;

import com.api.api.entities.lesson.plan.LessonPlan;
import com.api.api.entities.lesson.plan.LessonPlanStep;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "theory_step", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class TheoryStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String theory_text;

    @Column(nullable = false)
    private String theory_image;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private LessonPlan plan_id;

    @ManyToOne
    @JoinColumn(name = "plan_step_id", nullable = false)
    private LessonPlanStep plan_step_id;
}
