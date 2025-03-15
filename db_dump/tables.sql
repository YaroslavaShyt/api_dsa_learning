CREATE TABLE IF NOT EXISTS ACHIEVEMENTS(
	ID INT PRIMARY KEY, 
    NAME varchar(50),
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

CREATE TABLE IF NOT EXISTS Trainings (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    date DATE NOT NULL,                
    timeSpent INT NOT NULL,           
    CONSTRAINT chk_timeSpent CHECK (timeSpent >= 0)
);

CREATE TABLE IF NOT EXISTS UserTrainings (
    user_id BIGINT,
    training_id INT,
    PRIMARY KEY (user_id, training_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (training_id) REFERENCES trainings(id)
);


CREATE TABLE IF NOT EXISTS LearningCategory (
	id INT AUTO_INCREMENT PRIMARY KEY, 
    NAME varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS LessonPlanStep (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    number INT NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS LessonPlan (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    step1_id INT NOT NULL,
    step2_id INT NOT NULL,
    step3_id INT NOT NULL,
    step4_id INT NOT NULL,
    FOREIGN KEY (step1_id) REFERENCES LessonPlanStep(id),
    FOREIGN KEY (step2_id) REFERENCES LessonPlanStep(id),
    FOREIGN KEY (step3_id) REFERENCES LessonPlanStep(id),
    FOREIGN KEY (step4_id) REFERENCES LessonPlanStep(id)
);

CREATE TABLE IF NOT EXISTS TheoryStep (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    theory_text TEXT NOT NULL, 
    theory_image VARCHAR(255),  
    plan_id INT NOT NULL,  
    plan_step_id INT NOT NULL,
    FOREIGN KEY (plan_id) REFERENCES LessonPlan(id),
    FOREIGN KEY (plan_step_id) REFERENCES LessonPlanStep(id)
);

CREATE TABLE IF NOT EXISTS Theory (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    step1_id INT NOT NULL,
    step2_id INT NOT NULL,
    step3_id INT NOT NULL,
    step4_id INT NOT NULL,
    FOREIGN KEY (step1_id) REFERENCES TheoryStep(id),
    FOREIGN KEY (step2_id) REFERENCES TheoryStep(id),
    FOREIGN KEY (step3_id) REFERENCES TheoryStep(id),
    FOREIGN KEY (step4_id) REFERENCES TheoryStep(id)
);

CREATE TABLE IF NOT EXISTS Answers (
	id INT AUTO_INCREMENT PRIMARY KEY,
    answer varchar(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS AnswerVariants(
	id INT AUTO_INCREMENT PRIMARY KEY,
    correct_answer_id INT NOT NULL,
    first_answer_id INT NOT NULL,
    second_answer_id INT NOT NULL,
    third_answer_id INT NOT NULL,
    fourth_answer_id INT NOT NULL,
	FOREIGN KEY (correct_answer_id) REFERENCES Answers(id),
    FOREIGN KEY (first_answer_id) REFERENCES Answers(id),
    FOREIGN KEY (second_answer_id) REFERENCES Answers(id),
    FOREIGN KEY (third_answer_id) REFERENCES Answers(id),
	FOREIGN KEY (fourth_answer_id) REFERENCES Answers(id)
);

CREATE TABLE IF NOT EXISTS GameTask(
	id INT AUTO_INCREMENT PRIMARY KEY, 
	questionNumber INT NOT NULL,
	question TEXT NOT NULL, 
    answerVariants INT NOT NULL,
    FOREIGN KEY (answerVariants) REFERENCES AnswerVariants(id)
);

CREATE TABLE IF NOT EXISTS Game(
	id INT AUTO_INCREMENT PRIMARY KEY, 
	name varchar(128) NOT NULL,
	time_limit int NOT NULL
);

CREATE TABLE IF NOT EXISTS GameTaskToGame(
	game_id INT NOT NULL,
	game_task_id INT NOT NULL,
    PRIMARY KEY(game_id, game_task_id),
    FOREIGN KEY (game_id) REFERENCES Game(id),
    FOREIGN KEY (game_task_id) REFERENCES GameTask(id)
);

CREATE TABLE IF NOT EXISTS Lesson (
    id INT AUTO_INCREMENT PRIMARY KEY,
    game_id INT NOT NULL,
    theory_id INT NOT NULL,
    lesson_plan_id INT NOT NULL,  
    FOREIGN KEY (game_id) REFERENCES Game(id),
    FOREIGN KEY (theory_id) REFERENCES Theory(id),
    FOREIGN KEY (lesson_plan_id) REFERENCES LessonPlan(id)  
);

