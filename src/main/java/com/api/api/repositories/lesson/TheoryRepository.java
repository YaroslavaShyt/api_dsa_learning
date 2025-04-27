package com.api.api.repositories.lesson;

import com.api.api.entities.lesson.theory.Theory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheoryRepository extends JpaRepository<Theory, Long> {
}