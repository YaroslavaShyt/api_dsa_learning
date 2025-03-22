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


    public List<LessonSummaryDTO> getLessonSummaries() {
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonSummaryDTO> lessonSummaries = new ArrayList<>();

        for (Lesson lesson : lessons) {
            String topicName = lesson.getTopic() != null ? lesson.getTopic().getTopicName() : "";
            String lessonTitle = lesson.getTitle();
            Map<String, String> lessonPlanSteps = lesson.getLessonPlan() != null ? lesson.getLessonPlan().getStepsMap() : new HashMap<>();

            LessonSummaryDTO summaryDTO = new LessonSummaryDTO(
                    lesson.getId(),
                    topicName,
                    lessonTitle,
                    lessonPlanSteps
            );
            lessonSummaries.add(summaryDTO);
        }

        return lessonSummaries;
    }
}
