package com.api.api.services.teaching;

import com.api.api.controllers.teaching.LessonCreateRequest;
import com.api.api.controllers.teaching.LessonUpdateRequest;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonPlanStepRepository lessonPlanStepRepository;

    @Autowired
    private LessonPlanRepository lessonPlanRepository;

    @Autowired
    private TheoryStepRepository theoryStepRepository;

    @Autowired
    private TheoryRepository theoryRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameTaskRepository gameTaskRepository;

    @Autowired
    private GameTaskToGameRepository gameTaskToGameRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerVariantsRepository answerVariantsRepository;
    @Autowired
    private AnswersRepository answersRepository;
    @Autowired
    private GameTaskAnswersTypeRepository gameTaskAnswersTypeRepository;

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

        Theory theory = buildTheoryFromCreateRequest(request, lessonPlan);
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

    private Theory buildTheoryFromCreateRequest(LessonCreateRequest request, LessonPlan plan) {
        return buildTheoryFromRequest(request.getTheoryStep1(), request.getTheoryStep2(), request.getTheoryStep3(), request.getTheoryStep4(), plan);
    }

    private Theory buildTheoryFromUpdateRequest(LessonUpdateRequest request, LessonPlan plan) {
        return buildTheoryFromRequest(request.getTheoryStep1(), request.getTheoryStep2(), request.getTheoryStep3(), request.getTheoryStep4(), plan);
    }

    private Theory buildTheoryFromRequest(String theoryStep1, String theoryStep2, String theoryStep3, String theoryStep4, LessonPlan plan) {
        TheoryStep t1 = new TheoryStep(theoryStep1, "img1.png", plan, plan.getStep1_id());
        TheoryStep t2 = new TheoryStep(theoryStep2, "img2.png", plan, plan.getStep2_id());
        TheoryStep t3 = new TheoryStep(theoryStep3, "img3.png", plan, plan.getStep3_id());
        TheoryStep t4 = new TheoryStep(theoryStep4, "img4.png", plan, plan.getStep4_id());

        theoryStepRepository.saveAll(List.of(t1, t2, t3, t4));

        Theory theory = new Theory();
        theory.setStep1(t1);
        theory.setStep2(t2);
        theory.setStep3(t3);
        theory.setStep4(t4);
        theoryRepository.save(theory);

        return theory;
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
//        deleteTheoryFromLesson(lesson.getTheory());
//        deleteLessonPlanFromLesson(lesson.getLessonPlan());

        lessonRepository.delete(lesson);

    }


    private void deleteGameTasksFromGame(Game game) {
        if (game != null) {
            gameTaskToGameRepository.deleteAll(gameTaskToGameRepository.findByGame(game));
            gameRepository.delete(game);
        }
    }

    private void deleteTheoryFromLesson(Theory theory) {
        if (theory != null) {
            theoryRepository.delete(theory);
        }
    }

    private void deleteLessonPlanFromLesson(LessonPlan lessonPlan) {
        if (lessonPlan != null) {
            lessonPlanRepository.delete(lessonPlan);
        }
    }
}
