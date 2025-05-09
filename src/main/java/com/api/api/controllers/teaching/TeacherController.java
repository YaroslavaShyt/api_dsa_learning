package com.api.api.controllers.teaching;


import com.api.api.entities.lesson.game.GameTaskDTO;
import com.api.api.entities.topic.Topic;
import com.api.api.services.teaching.TeacherService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/teaching")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody LessonUpdateRequest request) {
        teacherService.updateLesson(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        teacherService.deleteLesson(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/", consumes = "multipart/form-data")
    public ResponseEntity<?> createLesson(@ModelAttribute LessonCreateRequest request) {
        teacherService.createLesson(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/topic")
    public ResponseEntity<Topic> createTopic(@RequestBody TopicRequestDTO topicRequest) {
        Topic createdTopic = teacherService.addTopic(topicRequest);
        return ResponseEntity.ok(createdTopic);
    }

    @PutMapping("/topic/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody TopicRequestDTO topicRequest) {
        Topic updatedTopic = teacherService.updateTopic(id, topicRequest);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/topic/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        teacherService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "gameTasks", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    List<GameTaskDTO> tasks = mapper.readValue(text, new TypeReference<>() {});
                    setValue(tasks);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Invalid gameTasks format: " + e.getMessage(), e);
                }
            }
        });
    }


}

