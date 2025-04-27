package com.api.api.controllers.teaching;


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

    @PostMapping("/")
    public ResponseEntity<?> createLesson(@RequestBody LessonCreateRequest request) {
        teacherService.createLesson(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

