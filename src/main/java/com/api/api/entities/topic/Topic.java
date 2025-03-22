package com.api.api.entities.topic;

import com.api.api.entities.learning_category.LearningCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "topic", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name", nullable = false)
    private String topicName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "learning_category_id", nullable = false)
    private LearningCategory category;
}

