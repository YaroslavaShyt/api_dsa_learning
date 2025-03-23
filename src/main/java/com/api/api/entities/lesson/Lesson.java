package com.api.api.entities.lesson;

import com.api.api.entities.lesson.game.Game;
import com.api.api.entities.lesson.plan.LessonPlan;
import com.api.api.entities.lesson.theory.Theory;
import com.api.api.entities.topic.Topic;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "lesson", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theory_id", nullable = false)
    private Theory theory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_plan_id", nullable = false)
    private LessonPlan lessonPlan;

    @Column(name = "title", nullable = false)
    private String title;

}

