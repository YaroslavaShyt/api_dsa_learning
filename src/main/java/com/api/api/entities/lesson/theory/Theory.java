package com.api.api.entities.lesson.theory;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Theory", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Theory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step1_id", nullable = false)
    private TheoryStep step1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step2_id", nullable = false)
    private TheoryStep step2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step3_id", nullable = false)
    private TheoryStep step3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step4_id", nullable = false)
    private TheoryStep step4;
}
