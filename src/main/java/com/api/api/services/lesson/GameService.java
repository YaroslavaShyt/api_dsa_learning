package com.api.api.services.lesson;

import com.api.api.entities.lesson.answers.AnswerVariants;
import com.api.api.entities.lesson.game.*;
import com.api.api.repositories.lesson.game.GameRepository;
import com.api.api.repositories.lesson.game.GameTaskRepository;
import com.api.api.repositories.lesson.game.GameTaskToGameRepository;
import com.api.api.repositories.lesson.game.answers.AnswerVariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameTaskRepository gameTaskRepository;

    @Autowired
    private GameTaskToGameRepository gameTaskToGameRepository;

    @Autowired
    private AnswerVariantsRepository answerVariantsRepository;

    public Optional<GameDetailsDTO> getGameDetailsById(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();

            List<GameTaskToGame> gameTaskToGames = gameTaskToGameRepository.findByGame(game);
            List<GameTaskDTO> gameTaskDTOs = new ArrayList<>();

            for (GameTaskToGame taskToGame : gameTaskToGames) {
                GameTask gameTask = taskToGame.getGameTask();
                List<String> answerOptions = new ArrayList<>();

                AnswerVariants answerVariants = gameTask.getAnswers();
                answerOptions.add(answerVariants.getFirstAnswer().getAnswer());
                answerOptions.add(answerVariants.getSecondAnswer().getAnswer());
                answerOptions.add(answerVariants.getThirdAnswer().getAnswer());
                answerOptions.add(answerVariants.getFourthAnswer().getAnswer());

                String correctAnswer = answerVariants.getCorrectAnswer().getAnswer();

                GameTaskDTO gameTaskDTO = new GameTaskDTO(
                        gameTask.getId(),
                        gameTask.getQuestionNumber(),
                        gameTask.getQuestion(),
                        answerOptions,
                        correctAnswer,
                        gameTask.getAnswersType().getId()
                );
                gameTaskDTOs.add(gameTaskDTO);
            }

            GameDetailsDTO gameDetailsDTO = new GameDetailsDTO(
                    game.getId(),
                    game.getName(),
                    game.getTimeLimit(),
                    gameTaskDTOs
            );

            return Optional.of(gameDetailsDTO);
        }

        return Optional.empty();
    }
}
