package com.api.api.services.teaching;

import com.api.api.controllers.teaching.LessonCreateRequest;
import com.api.api.controllers.teaching.LessonUpdateRequest;
import com.api.api.controllers.teaching.TopicRequestDTO;
import com.api.api.entities.learning_category.LearningCategory;
import com.api.api.entities.lesson.Lesson;
import com.api.api.entities.lesson.answers.AnswerVariants;
import com.api.api.entities.lesson.answers.Answers;
import com.api.api.entities.lesson.game.*;
import com.api.api.entities.lesson.plan.LessonPlan;
import com.api.api.entities.lesson.plan.LessonPlanStep;
import com.api.api.entities.lesson.theory.Theory;
import com.api.api.entities.lesson.theory.TheoryStep;
import com.api.api.entities.topic.Topic;
import com.api.api.repositories.lesson.TopicRepository;
import com.api.api.repositories.lesson.*;
import com.api.api.repositories.lesson.game.*;
import com.api.api.repositories.lesson.game.answers.AnswerVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final LessonRepository lessonRepository;
    private final LessonPlanStepRepository lessonPlanStepRepository;
    private final LessonPlanRepository lessonPlanRepository;
    private final TheoryStepRepository theoryStepRepository;
    private final TheoryRepository theoryRepository;
    private final GameRepository gameRepository;
    private final GameTaskRepository gameTaskRepository;
    private final GameTaskToGameRepository gameTaskToGameRepository;
    private final TopicRepository topicRepository;
    private final AnswerVariantsRepository answerVariantsRepository;
    private final AnswersRepository answersRepository;
    private final GameTaskAnswersTypeRepository gameTaskAnswersTypeRepository;
    private final LearningCategoryRepository categoryRepository;


    @Transactional
    public void createLesson(LessonCreateRequest request) {
        Lesson lesson = new Lesson();
        fillLessonFromCreateRequest(lesson, request);
        lessonRepository.save(lesson);
    }

    @Transactional
    public void updateLesson(Long id, LessonUpdateRequest request) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));
        fillLessonFromUpdateRequest(lesson, request);
        lessonRepository.save(lesson);
    }

    private void fillLessonFromCreateRequest(Lesson lesson, LessonCreateRequest request) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        lesson.setTopic(topic);
        lesson.setTitle(request.getLessonTitle());

        LessonPlan lessonPlan = buildLessonPlanFromCreateRequest(request);
        lesson.setLessonPlan(lessonPlan);

        Theory theory = buildTheoryFromRequest(
                request.getTheoryStep1(), request.getTheoryStep2(), request.getTheoryStep3(), request.getTheoryStep4(),
                Optional.ofNullable(request.getTheoryImageStep1()), Optional.ofNullable(request.getTheoryImageStep2()),
                Optional.ofNullable(request.getTheoryImageStep3()), Optional.ofNullable(request.getTheoryImageStep4()),
                lessonPlan
        );
        lesson.setTheory(theory);

        Game game = buildGameFromCreateRequest(request);
        lesson.setGame(game);

        saveGameTasks(game, request.getGameTasks());
    }


    private void saveGameTasks(Game game, List<GameTaskDTO> gameTasks) {
        for (int i = 0; i < gameTasks.size(); i++) {
            GameTaskDTO taskDTO = gameTasks.get(i);

            GameTask gameTask = new GameTask();
            gameTask.setQuestionNumber(i + 1);
            gameTask.setQuestion(taskDTO.getQuestion());

            AnswerVariants answerVariants = createAnswerVariants(taskDTO.getAnswerOptions(), taskDTO.getCorrectAnswer());
            gameTask.setAnswers(answerVariants);

            GameTaskAnswersType answersType = gameTaskAnswersTypeRepository.findById(gameTasks.get(i).getGameAnswersTypeId()).orElseThrow(() -> new EntityNotFoundException("Game task type not found"));

            gameTask.setAnswersType(answersType);

            gameTaskRepository.save(gameTask);

            gameTaskToGameRepository.save(new GameTaskToGame(game, gameTask));
        }
    }


    private AnswerVariants createAnswerVariants(List<String> answerOptions, String correctAnswer) {
        System.out.println(answerOptions);
        AnswerVariants answerVariants = new AnswerVariants();
        answerVariants.setFirstAnswer(answersRepository.save((new Answers(answerOptions.get(0)))));
        answerVariants.setSecondAnswer(answersRepository.save((new Answers(answerOptions.get(1)))));
        answerVariants.setThirdAnswer(answersRepository.save((new Answers(answerOptions.get(2)))));
        answerVariants.setFourthAnswer(answersRepository.save((new Answers(answerOptions.get(3)))));

        answerVariants.setCorrectAnswer(answersRepository.save((new Answers(correctAnswer))));
        answerVariantsRepository.save(answerVariants);
        return answerVariants;
    }


    private void fillLessonFromUpdateRequest(Lesson lesson, LessonUpdateRequest request) {
        lesson.setTitle(request.getLessonTitle());

        LessonPlan lessonPlan = buildLessonPlanFromUpdateRequest(request);
        lesson.setLessonPlan(lessonPlan);

        Theory theory = buildTheoryFromUpdateRequest(request, lessonPlan);
        lesson.setTheory(theory);

        Game game = buildGameFromUpdateRequest(request);
        lesson.setGame(game);

        gameTaskToGameRepository.deleteAll(gameTaskToGameRepository.findByGame(game));

        saveGameTasks(game, request.getGameTasks());
    }


    private LessonPlan buildLessonPlanFromCreateRequest(LessonCreateRequest request) {
        return buildLessonPlanFromRequest(request.getStep1Plan(), request.getStep2Plan(), request.getStep3Plan(), request.getStep4Plan());
    }

    private LessonPlan buildLessonPlanFromUpdateRequest(LessonUpdateRequest request) {
        return buildLessonPlanFromRequest(request.getStep1Plan(), request.getStep2Plan(), request.getStep3Plan(), request.getStep4Plan());
    }

    private LessonPlan buildLessonPlanFromRequest(String step1Plan, String step2Plan, String step3Plan, String step4Plan) {
        LessonPlanStep step1 = new LessonPlanStep(1, step1Plan);
        LessonPlanStep step2 = new LessonPlanStep(2, step2Plan);
        LessonPlanStep step3 = new LessonPlanStep(3, step3Plan);
        LessonPlanStep step4 = new LessonPlanStep(4, step4Plan);

        lessonPlanStepRepository.saveAll(List.of(step1, step2, step3, step4));

        LessonPlan plan = new LessonPlan();
        plan.setStep1_id(step1);
        plan.setStep2_id(step2);
        plan.setStep3_id(step3);
        plan.setStep4_id(step4);
        lessonPlanRepository.save(plan);

        return plan;
    }


    private Theory buildTheoryFromUpdateRequest(LessonUpdateRequest request, LessonPlan plan) {
        Theory oldTheory = theoryRepository.findById(request.getTheoryId()).orElseThrow(() -> new EntityNotFoundException("Theory not found"));

        String imagePath1 = replaceImage(oldTheory.getStep1().getTheory_image(), request.getTheoryImageStep1());
        String imagePath2 = replaceImage(oldTheory.getStep2().getTheory_image(), request.getTheoryImageStep2());
        String imagePath3 = replaceImage(oldTheory.getStep3().getTheory_image(), request.getTheoryImageStep3());
        String imagePath4 = replaceImage(oldTheory.getStep4().getTheory_image(), request.getTheoryImageStep4());

        TheoryStep t1 = new TheoryStep(request.getTheoryStep1(), imagePath1, plan, plan.getStep1_id());
        TheoryStep t2 = new TheoryStep(request.getTheoryStep2(), imagePath2, plan, plan.getStep2_id());
        TheoryStep t3 = new TheoryStep(request.getTheoryStep3(), imagePath3, plan, plan.getStep3_id());
        TheoryStep t4 = new TheoryStep(request.getTheoryStep4(), imagePath4, plan, plan.getStep4_id());

        theoryStepRepository.saveAll(List.of(t1, t2, t3, t4));

        Theory theory = new Theory();
        theory.setStep1(t1);
        theory.setStep2(t2);
        theory.setStep3(t3);
        theory.setStep4(t4);
        theoryRepository.save(theory);

        return theory;
    }

    private String replaceImage(String oldImagePath, MultipartFile newFile) {
        if (newFile != null && !newFile.isEmpty()) {
            deleteTheoryImage(oldImagePath);
            return saveTheoryImage(Optional.of(newFile));
        }
        return oldImagePath;
    }

    private Theory buildTheoryFromRequest(
            String theoryStep1, String theoryStep2, String theoryStep3, String theoryStep4,
            Optional<MultipartFile> img1, Optional<MultipartFile> img2, Optional<MultipartFile> img3,
            Optional<MultipartFile> img4,
            LessonPlan plan
    ) {
        String imagePath1 = saveTheoryImage(img1);
        String imagePath2 = saveTheoryImage(img2);
        String imagePath3 = saveTheoryImage(img3);
        String imagePath4 = saveTheoryImage(img4);

        TheoryStep t1 = new TheoryStep(theoryStep1, imagePath1, plan, plan.getStep1_id());
        TheoryStep t2 = new TheoryStep(theoryStep2, imagePath2, plan, plan.getStep2_id());
        TheoryStep t3 = new TheoryStep(theoryStep3, imagePath3, plan, plan.getStep3_id());
        TheoryStep t4 = new TheoryStep(theoryStep4, imagePath4, plan, plan.getStep4_id());

        theoryStepRepository.saveAll(List.of(t1, t2, t3, t4));

        Theory theory = new Theory();
        theory.setStep1(t1);
        theory.setStep2(t2);
        theory.setStep3(t3);
        theory.setStep4(t4);
        theoryRepository.save(theory);

        return theory;
    }

    private String saveTheoryImage(Optional<MultipartFile> file) {
        if (file.isPresent()) {
            final MultipartFile multipartFile = file.get();
            try {
                String uploadDir = "uploads/theory/";
                Files.createDirectories(Paths.get(uploadDir));

                String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, filename);
                Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                return "/files/theory/" + filename;
            } catch (IOException e) {
                throw new RuntimeException("Не вдалося зберегти файл", e);
            }
        } return "";
    }

    private void deleteTheoryImage(String imagePath) {
        if (imagePath != null) {
            Path path = Paths.get("uploads", imagePath.replace("/files/", ""));
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                System.err.println("Не вдалося видалити файл: " + path);
            }
        }
    }


    private Game buildGameFromCreateRequest(LessonCreateRequest request) {
        return buildGameFromRequest(request.getGameId(), request.getGameName(), request.getTimeLimit());
    }

    private Game buildGameFromUpdateRequest(LessonUpdateRequest request) {
        return buildGameFromRequest(request.getGameId(), request.getGameName(), request.getTimeLimit());
    }

    private Game buildGameFromRequest(Long gameId, String gameName, int timeLimit) {
        Game game = gameId != null
                ? gameRepository.findById(gameId).orElse(new Game())
                : new Game();

        game.setName(gameName);
        game.setTimeLimit(timeLimit);
        gameRepository.save(game);

        return game;
    }

    @Transactional
    public void deleteLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        deleteGameTasksFromGame(lesson.getGame());

        lessonRepository.delete(lesson);

    }


    private void deleteGameTasksFromGame(Game game) {
        if (game != null) {
            gameTaskToGameRepository.deleteAll(gameTaskToGameRepository.findByGame(game));
        }
    }

    public Topic addTopic(TopicRequestDTO requestDTO) {
        Optional<LearningCategory> categoryOpt = categoryRepository.findById(requestDTO.getCategoryId());

        if (categoryOpt.isEmpty()) {
            throw new RuntimeException("Category with ID " + requestDTO.getCategoryId() + " not found.");
        }

        Topic topic = new Topic();
        topic.setTopicName(requestDTO.getTopicName());
        topic.setCategory(categoryOpt.get());

        return topicRepository.save(topic);
    }

    public Topic updateTopic(Long id, TopicRequestDTO requestDTO) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with id: " + id));

        LearningCategory category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + requestDTO.getCategoryId()));

        topic.setTopicName(requestDTO.getTopicName());
        topic.setCategory(category);

        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new RuntimeException("Topic not found with id: " + id);
        }
        topicRepository.deleteById(id);
    }

}
