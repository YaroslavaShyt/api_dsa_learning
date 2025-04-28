package com.api.api.controllers.teaching;

import lombok.Data;

@Data
public class TopicRequestDTO {
    private String topicName;
    private Long categoryId;
}