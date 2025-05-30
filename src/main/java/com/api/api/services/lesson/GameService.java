package com.api.api.services.lesson;

import com.api.api.entities.lesson.answers.AnswerVariants;
import com.api.api.entities.lesson.game.*;
import com.api.api.repositories.lesson.game.GameRepository;
import com.api.api.repositories.lesson.game.GameTaskToGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameTaskToGameRepository gameTaskToGameRepository;

    public Optional<GroupedGameDetailsDTO> getGroupedGameDetailsById(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();

            List<GameTaskToGame> gameTaskToGames = gameTaskToGameRepository.findByGame(game);
            List<GameTaskDTO> gameTaskDTOs = new ArrayList<>();

            for (GameTaskToGame taskToGame : gameTaskToGames) {
                GameTaskDTO gameTaskDTO = getGameTaskDTO(taskToGame);
                gameTaskDTOs.add(gameTaskDTO);
            }

            Map<String, List<GameTaskDTO>> groupedTasks = gameTaskDTOs.stream()
                    .collect(Collectors.groupingBy(GameTaskDTO::getTaskLevel));

            GroupedGameDetailsDTO groupedDetails = new GroupedGameDetailsDTO(
                    game.getId(),
                    game.getName(),
                    game.getTimeLimit(),
                    groupedTasks
            );

            return Optional.of(groupedDetails);
        }

        return Optional.empty();
    }

    public Optional<GameDetailsDTO> getGameDetailsById(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();

            List<GameTaskToGame> gameTaskToGames = gameTaskToGameRepository.findByGame(game);
            List<GameTaskDTO> gameTaskDTOs = new ArrayList<>();

            for (GameTaskToGame taskToGame : gameTaskToGames) {
                GameTaskDTO gameTaskDTO = getGameTaskDTO(taskToGame);
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

    private static GameTaskDTO getGameTaskDTO(GameTaskToGame taskToGame) {
        GameTask gameTask = taskToGame.getGameTask();
        List<String> answerOptions = new ArrayList<>();

        AnswerVariants answerVariants = gameTask.getAnswers();
        answerOptions.add(answerVariants.getFirstAnswer().getAnswer());
        answerOptions.add(answerVariants.getSecondAnswer().getAnswer());
        answerOptions.add(answerVariants.getThirdAnswer().getAnswer());
        answerOptions.add(answerVariants.getFourthAnswer().getAnswer());

        String correctAnswer = answerVariants.getCorrectAnswer().getAnswer();

        return new GameTaskDTO(
                gameTask.getId(),
                gameTask.getQuestionNumber(),
                gameTask.getQuestion(),
                answerOptions,
                correctAnswer,
                gameTask.getAnswersType().getId(),
                taskToGame.getTask_level()
        );
    }
}
