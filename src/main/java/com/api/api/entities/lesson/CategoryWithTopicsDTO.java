package com.api.api.entities.lesson;

import lombok.Data;

import java.util.List;

@Data
public class CategoryWithTopicsDTO {
    private Long categoryId;
    private String categoryName;
    private List<TopicDTO> topics;

    @Data
    public static class TopicDTO {
        private Long id;
        private String topicName;
    }
}
