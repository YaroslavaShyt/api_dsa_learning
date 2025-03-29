package com.api.api.entities.user;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTrainingId implements Serializable {
    private Long userId;
    private Long trainingId;
}
