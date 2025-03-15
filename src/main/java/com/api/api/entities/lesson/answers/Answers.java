package com.api.api.entities.lesson.answers;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Answers", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
