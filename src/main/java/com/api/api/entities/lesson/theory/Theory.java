package com.api.api.entities.lesson.theory;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "theory", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Theory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "step1_id", nullable = false)
    private TheoryStep step1;

    @ManyToOne
    @JoinColumn(name = "step2_id", nullable = false)
    private TheoryStep step2;

    @ManyToOne
    @JoinColumn(name = "step3_id", nullable = false)
    private TheoryStep step3;

    @ManyToOne
    @JoinColumn(name = "step4_id", nullable = false)
    private TheoryStep step4;
}
