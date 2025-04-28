package com.api.api.controllers.teaching;


import com.api.api.entities.topic.Topic;
import com.api.api.services.teaching.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseEntity<?> createLesson(@RequestBody LessonCreateRequest request) {
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
}

