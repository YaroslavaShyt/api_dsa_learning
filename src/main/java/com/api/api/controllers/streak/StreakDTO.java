package com.api.api.controllers.streak;

import com.api.api.entities.streak.Streak;
import jakarta.persistence.*;

import java.time.LocalDate;


import com.api.api.entities.streak.Streak.StreakStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StreakDTO {
    private LocalDate date;
    private StreakStatus status;

    public StreakDTO(LocalDate date, StreakStatus status) {
        this.date = date;
        this.status = status;
    }
}

