CREATE TABLE IF NOT EXISTS ACHIEVEMENTS
(
    ID          INT PRIMARY KEY,
    NAME        varchar(50),
    DESCRIPTION varchar(50)
);

-- CREATE TABLE IF NOT EXISTS UserAchievements (
--     user_id BIGINT,
--     achievement_id BIGINT,
--     achieved DATETIME,
--     PRIMARY KEY (user_id, achievement_id),
--     FOREIGN KEY (user_id) REFERENCES users(id),
--     FOREIGN KEY (achievement_id) REFERENCES achievements(id)
-- );

CREATE TABLE IF NOT EXISTS Trainings
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    date      DATE NOT NULL,
    timeSpent INT  NOT NULL,
    CONSTRAINT chk_timeSpent CHECK (timeSpent >= 0)
);

CREATE TABLE IF NOT EXISTS UserTrainings
(
    user_id     BIGINT,
    training_id BIGINT,
    PRIMARY KEY (user_id, training_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (training_id) REFERENCES trainings (id)
);


CREATE TABLE IF NOT EXISTS LearningCategory
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    NAME varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS lesson_plan_step
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    number INT  NOT NULL,
    name   TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS lesson_plan
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    step1_id BIGINT,
    step2_id BIGINT,
    step3_id BIGINT,
    step4_id BIGINT,
    FOREIGN KEY (step1_id) REFERENCES lesson_plan_step (id),
    FOREIGN KEY (step2_id) REFERENCES lesson_plan_step (id),
    FOREIGN KEY (step3_id) REFERENCES lesson_plan_step (id),
    FOREIGN KEY (step4_id) REFERENCES lesson_plan_step (id)
);

CREATE TABLE IF NOT EXISTS theory_step
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    theory_text  TEXT   NOT NULL,
    theory_image VARCHAR(255),
    plan_id      BIGINT NOT NULL,
    plan_step_id BIGINT NOT NULL,
    FOREIGN KEY (plan_id) REFERENCES lesson_plan (id),
    FOREIGN KEY (plan_step_id) REFERENCES lesson_plan_step (id)
);

CREATE TABLE IF NOT EXISTS theory
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    step1_id BIGINT NOT NULL,
    step2_id BIGINT NOT NULL,
    step3_id BIGINT NOT NULL,
    step4_id BIGINT NOT NULL,
    FOREIGN KEY (step1_id) REFERENCES theory_step (id),
    FOREIGN KEY (step2_id) REFERENCES theory_step (id),
    FOREIGN KEY (step3_id) REFERENCES theory_step (id),
    FOREIGN KEY (step4_id) REFERENCES theory_step (id)
);

CREATE TABLE IF NOT EXISTS answers
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    answer varchar(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS answer_variants
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    correct_answer_id BIGINT NOT NULL,
    first_answer_id   BIGINT NOT NULL,
    second_answer_id  BIGINT NOT NULL,
    third_answer_id   BIGINT NOT NULL,
    fourth_answer_id  BIGINT NOT NULL,
    FOREIGN KEY (correct_answer_id) REFERENCES Answers (id),
    FOREIGN KEY (first_answer_id) REFERENCES Answers (id),
    FOREIGN KEY (second_answer_id) REFERENCES Answers (id),
    FOREIGN KEY (third_answer_id) REFERENCES Answers (id),
    FOREIGN KEY (fourth_answer_id) REFERENCES Answers (id)
);

CREATE TABLE IF NOT EXISTS game_task
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    questionNumber INT    NOT NULL,
    question       TEXT   NOT NULL,
    answers        BIGINT NOT NULL,
    FOREIGN KEY (answers) REFERENCES answer_variants (id)
);

CREATE TABLE IF NOT EXISTS game
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       varchar(128) NOT NULL,
    time_limit int          NOT NULL
);

CREATE TABLE IF NOT EXISTS game_task_to_game
(
    game_id      BIGINT NOT NULL,
    game_task_id BIGINT NOT NULL,
    PRIMARY KEY (game_id, game_task_id),
    FOREIGN KEY (game_id) REFERENCES Game (id),
    FOREIGN KEY (game_task_id) REFERENCES game_task (id)
);

CREATE TABLE IF NOT EXISTS topic
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    topic_name           VARCHAR(255),
    learning_category_id int NOT NULL,
    FOREIGN KEY (learning_category_id) REFERENCES LearningCategory (id)
);

CREATE TABLE IF NOT EXISTS lesson
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id        BIGINT NOT NULL,
    topic_id       BIGINT NOT NULL,
    theory_id      BIGINT NOT NULL,
    lesson_plan_id BIGINT NOT NULL,
    FOREIGN KEY (game_id) REFERENCES Game (id),
    FOREIGN KEY (topic_id) REFERENCES Topic (id),
    FOREIGN KEY (theory_id) REFERENCES Theory (id),
    FOREIGN KEY (lesson_plan_id) REFERENCES lesson_plan (id)
);




