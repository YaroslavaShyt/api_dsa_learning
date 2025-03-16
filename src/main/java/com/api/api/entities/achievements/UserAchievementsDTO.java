package com.api.api.entities.achievements;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAchievementsDTO {
    private Long userId;
    private Long achievementId;
    private String achievementName;
    private String achievementDescription;
    private String createdAt;
}

