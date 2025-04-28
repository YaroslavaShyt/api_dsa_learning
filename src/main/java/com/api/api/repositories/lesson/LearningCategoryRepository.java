package com.api.api.repositories.lesson;

import com.api.api.entities.learning_category.LearningCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningCategoryRepository extends JpaRepository<LearningCategory, Long> {
}