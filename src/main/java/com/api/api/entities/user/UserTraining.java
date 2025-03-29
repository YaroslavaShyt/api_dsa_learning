package com.api.api.entities.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_trainings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserTrainingId.class)
public class UserTraining {

    @Id
    private Long userId;

    @Id
    private Long trainingId;
}

