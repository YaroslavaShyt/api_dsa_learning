package com.api.api.services.lesson;

import com.api.api.entities.lesson.Lesson;
import com.api.api.entities.lesson.LessonDetailsDTO;
import com.api.api.entities.lesson.LessonSummaryDTO;
import com.api.api.entities.lesson.plan.LessonPlan;
import com.api.api.entities.lesson.theory.Theory;
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


    public Map<String, Map<String, List<LessonSummaryDTO>>> getLessonsSummaries() {
        List<Lesson> lessons = lessonRepository.findAll();
        Map<String, Map<String, List<LessonSummaryDTO>>> categorizedLessons = new HashMap<>();

        for (Lesson lesson : lessons) {
            String categoryName = lesson.getTopic().getCategory().getName();
            String topicName = lesson.getTopic().getTopicName();

            categorizedLessons.putIfAbsent(categoryName, new HashMap<>());

            categorizedLessons.get(categoryName).putIfAbsent(topicName, new ArrayList<>());

            Map<String, String> lessonPlanSteps = lesson.getLessonPlan() != null ? lesson.getLessonPlan().getStepsMap() : new HashMap<>();
            LessonSummaryDTO summaryDTO = new LessonSummaryDTO(
                    lesson.getId(),
                    lesson.getGame().getId(),
                    topicName,
                    lesson.getTitle(),
                    lessonPlanSteps
            );

            categorizedLessons.get(categoryName).get(topicName).add(summaryDTO);
        }

        return categorizedLessons;
    }

    public Optional<LessonDetailsDTO> getLessonDetailsById(Long lessonId) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);

        if (lessonOptional.isPresent()) {
            Lesson lesson = lessonOptional.get();

            LessonPlan lessonPlan = lesson.getLessonPlan();
            Map<String, String> lessonPlanSteps = lessonPlan != null ? lessonPlan.getStepsMap() : new HashMap<>();

            Theory theory = lesson.getTheory();
            String theoryStep1 = (theory != null && theory.getStep1() != null) ? theory.getStep1().getTheory_text() : "N/A";
            String theoryStep2 = (theory != null && theory.getStep2() != null) ? theory.getStep2().getTheory_text() : "N/A";
            String theoryStep3 = (theory != null && theory.getStep3() != null) ? theory.getStep3().getTheory_text() : "N/A";
            String theoryStep4 = (theory != null && theory.getStep4() != null) ? theory.getStep4().getTheory_text() : "N/A";

            LessonDetailsDTO detailsDTO = new LessonDetailsDTO(
                    lesson.getTitle(),
                    lessonPlanSteps.get("step1"),
                    lessonPlanSteps.get("step2"),
                    lessonPlanSteps.get("step3"),
                    lessonPlanSteps.get("step4"),
                    theoryStep1,
                    theoryStep2,
                    theoryStep3,
                    theoryStep4
            );

            return Optional.of(detailsDTO);
        }

        return Optional.empty();
    }
}
