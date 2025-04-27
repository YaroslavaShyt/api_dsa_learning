package com.api.api.entities.lesson.answers;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "answers", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String answer;

    public Answers(final String answer) {
        this.answer = answer;
    }
}
