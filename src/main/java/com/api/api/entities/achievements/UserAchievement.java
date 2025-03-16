package com.api.api.entities.achievements;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user_achievements")
public class UserAchievement  {

    @EmbeddedId
    private UserAchievementId id;


    @CreationTimestamp
    @Column(name = "achieved", updatable = false)
    private LocalDateTime createdAt;
}
