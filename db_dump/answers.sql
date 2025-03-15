insert into answers (answer) values ('Складністю'), ('Підходами до сортування'), ('Часовою складністю'), ("Пам'яттю");
insert into AnswerVariants (correct_answer_id,
    first_answer_id,
    second_answer_id,
    third_answer_id,
    fourth_answer_id) values (1, 1, 2, 3, 4);
    
insert into GameTask (questionNumber,
	question, 
    answerVariants) values (1, 'Алгоритми сортування можна класифікувати за:', 1);