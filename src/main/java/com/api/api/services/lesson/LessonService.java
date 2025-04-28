package com.api.api.services.lesson;

import com.api.api.entities.learning_category.LearningCategory;
import com.api.api.entities.lesson.CategoryWithTopicsDTO;
import com.api.api.entities.lesson.Lesson;
import com.api.api.entities.lesson.LessonDetailsDTO;
import com.api.api.entities.lesson.LessonSummaryDTO;
import com.api.api.entities.lesson.plan.LessonPlan;
import com.api.api.entities.lesson.theory.Theory;
import com.api.api.entities.topic.Topic;
import com.api.api.repositories.lesson.LearningCategoryRepository;
import com.api.api.repositories.lesson.LessonRepository;
import com.api.api.repositories.lesson.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<CategoryWithTopicsDTO> getTopicsGroupedByCategory() {
        List<Topic> topics = topicRepository.findAll();

        Map<Long, List<Topic>> grouped = topics.stream()
                .collect(Collectors.groupingBy(t -> t.getCategory().getId()));

        return grouped.entrySet().stream().map(entry -> {
            Topic sample = entry.getValue().get(0);

            CategoryWithTopicsDTO dto = new CategoryWithTopicsDTO();
            dto.setCategoryId(sample.getCategory().getId());
            dto.setCategoryName(sample.getCategory().getName());

            List<CategoryWithTopicsDTO.TopicDTO> topicDTOs = entry.getValue().stream().map(topic -> {
                CategoryWithTopicsDTO.TopicDTO t = new CategoryWithTopicsDTO.TopicDTO();
                t.setId(topic.getId());
                t.setTopicName(topic.getTopicName());
                return t;
            }).collect(Collectors.toList());

            dto.setTopics(topicDTOs);
            return dto;
        }).collect(Collectors.toList());
    }

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

            LessonSummaryDTO summaryDTO = getLessonSummaryDTO(lesson, topicName);

            categorizedLessons.get(categoryName).get(topicName).add(summaryDTO);
        }

        return categorizedLessons;
    }

    private static LessonSummaryDTO getLessonSummaryDTO(Lesson lesson, String topicName) {
        Map<String, String> lessonPlanSteps = lesson.getLessonPlan() != null ? lesson.getLessonPlan().getStepsMap() : new HashMap<>();
        return new LessonSummaryDTO(
                lesson.getId(),
                lesson.getGame().getId(),
                topicName,
                lesson.getTitle(),
                lessonPlanSteps,
                lesson.getTopic().getId(),
                lesson.getTopic().getCategory().getId()
        );
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
