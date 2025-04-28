package com.api.api.controllers.lesson;

import com.api.api.entities.lesson.CategoryWithTopicsDTO;
import com.api.api.entities.lesson.Lesson;
import com.api.api.entities.lesson.LessonDetailsDTO;
import com.api.api.entities.lesson.LessonSummaryDTO;
import com.api.api.entities.lesson.game.Game;
import com.api.api.entities.lesson.game.GameDetailsDTO;
import com.api.api.services.lesson.GameService;
import com.api.api.services.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private GameService gameService;

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
    public ResponseEntity<Map<String, Map<String, List<LessonSummaryDTO>>>> getLessonsByCategory() {
        Map<String, Map<String, List<LessonSummaryDTO>>> categorizedLessons = lessonService.getLessonsSummaries();
        return ResponseEntity.ok(categorizedLessons);
    }

    @GetMapping("/topics")
    public ResponseEntity<List<CategoryWithTopicsDTO>> getTopicsGroupedByCategory() {
        return ResponseEntity.ok(lessonService.getTopicsGroupedByCategory());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<LessonDetailsDTO> getLessonDetails(@PathVariable("id") Long lessonId) {
        Optional<LessonDetailsDTO> lessonDetails = lessonService.getLessonDetailsById(lessonId);

        return lessonDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<GameDetailsDTO>  getLessonGame(@PathVariable("id") Long gameId) {
        Optional<GameDetailsDTO> gameDetails = gameService.getGameDetailsById(gameId);

        return gameDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}

