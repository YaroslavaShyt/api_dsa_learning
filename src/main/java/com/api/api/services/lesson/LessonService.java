package com.api.api.services.lesson;

import com.api.api.entities.lesson.Lesson;
import com.api.api.entities.lesson.LessonSummaryDTO;
import com.api.api.repositories.lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }


    public Map<String, List<LessonSummaryDTO>> getLessonsByCategory() {
        List<Lesson> lessons = lessonRepository.findAll();
        Map<String, List<LessonSummaryDTO>> categorizedLessons = new HashMap<>();

        for (Lesson lesson : lessons) {
            String categoryName = lesson.getTopic().getCategory().getName();

            categorizedLessons.putIfAbsent(categoryName, new ArrayList<>());

            Map<String, String> lessonPlanSteps = lesson.getLessonPlan() != null ? lesson.getLessonPlan().getStepsMap() : new HashMap<>();
            LessonSummaryDTO summaryDTO = new LessonSummaryDTO(
                    lesson.getId(),
                    lesson.getTopic().getTopicName(),
                    lesson.getTitle(),
                    lessonPlanSteps
            );

            categorizedLessons.get(categoryName).add(summaryDTO);
        }

        return categorizedLessons;
    }
}
