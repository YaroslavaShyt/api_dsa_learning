package com.api.api.controllers.lesson;

import com.api.api.entities.lesson.Lesson;
import com.api.api.entities.lesson.LessonSummaryDTO;
import com.api.api.services.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonService.getLessonById(id);
        return lesson.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, List<LessonSummaryDTO>>> getLessonsByCategory() {
        Map<String, List<LessonSummaryDTO>> categorizedLessons = lessonService.getLessonsByCategory();
        return ResponseEntity.ok(categorizedLessons);
    }
}

