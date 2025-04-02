package com.api.api.entities.user;

import com.api.api.entities.lesson.Lesson;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "user_trainings")
@Getter
@Setter
@NoArgsConstructor
@IdClass(UserTrainingId.class)
public class UserTraining {

    @Id
    private Long userId;

    @Id
    @Column(name = "training_id")
    private Long trainingId;

    @Column(name = "time", nullable = false)
    private int time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Lesson training;

    @CreationTimestamp
    @Column(name = "date", nullable = false, updatable = false)
    private LocalDate date;

    public UserTraining(Long userId, Long trainingId, int time, LocalDate date) {
        this.userId = userId;
        this.trainingId = trainingId;
        this.time = time;
        this.date = date;
    }
}

