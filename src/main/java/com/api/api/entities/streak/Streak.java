package com.api.api.entities.streak;

import com.api.api.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "streaks", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Streak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StreakStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    public enum StreakStatus {
        LEARNED, FROZEN, NOT_LEARNED
    }

    public Streak(Long userId, LocalDate date, StreakStatus status) {
        this.user = new User();
        this.user.setId(userId);
        this.date = date;
        this.status = status;
    }

}
