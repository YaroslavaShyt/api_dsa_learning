package com.api.api.entities.achievements;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class UserAchievementId implements Serializable {

    private Long userId;

    private Long achievementId;


    public UserAchievementId() {
    }

    public UserAchievementId(Long userId, Long achievementId) {
        this.userId = userId;
        this.achievementId = achievementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAchievementId that = (UserAchievementId) o;
        return userId.equals(that.userId) && achievementId.equals(that.achievementId);
    }

    @Override
    public int hashCode() {
        return 31 * userId.hashCode() + achievementId.hashCode();
    }
}
