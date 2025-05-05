package com.api.api.controllers.streak;

import java.time.LocalDate;


import com.api.api.entities.streak.Streak.StreakStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

