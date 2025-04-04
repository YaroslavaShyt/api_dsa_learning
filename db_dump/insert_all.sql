INSERT INTO learning_category (name) VALUES ('ALGORITHMS'), ('DATA_STRUCTURES');
INSERT INTO lesson_plan_step (number, name)
VALUES (1, 'Огляд алгоритмів сортування та їх важливість у програмуванні'),
       (2, 'Визначення поняття "сортування" та основні терміни'),
       (3, 'Класифікація алгоритмів сортування'),
       (4, 'Оцінка складності алгоритмів сортування');

INSERT INTO lesson_plan(step1_id, step2_id, step3_id, step4_id)
values (1, 2, 3, 4);


INSERT INTO theory_step (theory_text,
                         theory_image,
                         plan_id,
                         plan_step_id)
VALUES ('Алгоритми сортування — це методи впорядкування елементів у масиві або списку за певним критерієм, зазвичай за зростанням або спаданням.
                                                   Вони є основою для багатьох інших алгоритмів і важливі в програмуванні, оскільки дозволяють ефективно обробляти та аналізувати дані. Сортування оптимізує пошук, покращує швидкість роботи інших алгоритмів (наприклад, пошук бінарним методом) та забезпечує зручність для користувачів у роботі з великими обсягами даних.
                                                   Різні алгоритми сортування мають різну складність і підходять для різних типів задач, тому важливо вибирати правильний алгоритм з',
        'PATH', 1, 1),
       ('Алгоритми сортування — це методи впорядкування елементів у масиві або списку за певним критерієм, зазвичай за зростанням або спаданням.
                                                   Вони є основою для багатьох інших алгоритмів і важливі в програмуванні, оскільки дозволяють ефективно обробляти та аналізувати дані. Сортування оптимізує пошук, покращує швидкість роботи інших алгоритмів (наприклад, пошук бінарним методом) та забезпечує зручність для користувачів у роботі з великими обсягами даних.
                                                   Різні алгоритми сортування мають різну складність і підходять для різних типів задач, тому важливо вибирати правильний алгоритм з',
        'PATH', 1, 2),
       ('Алгоритми сортування — це методи впорядкування елементів у масиві або списку за певним критерієм, зазвичай за зростанням або спаданням.
                                                   Вони є основою для багатьох інших алгоритмів і важливі в програмуванні, оскільки дозволяють ефективно обробляти та аналізувати дані. Сортування оптимізує пошук, покращує швидкість роботи інших алгоритмів (наприклад, пошук бінарним методом) та забезпечує зручність для користувачів у роботі з великими обсягами даних.
                                                   Різні алгоритми сортування мають різну складність і підходять для різних типів задач, тому важливо вибирати правильний алгоритм з',
        'PATH', 1, 3),
       ('Алгоритми сортування — це методи впорядкування елементів у масиві або списку за певним критерієм, зазвичай за зростанням або спаданням.
                                                   Вони є основою для багатьох інших алгоритмів і важливі в програмуванні, оскільки дозволяють ефективно обробляти та аналізувати дані. Сортування оптимізує пошук, покращує швидкість роботи інших алгоритмів (наприклад, пошук бінарним методом) та забезпечує зручність для користувачів у роботі з великими обсягами даних.
                                                   Різні алгоритми сортування мають різну складність і підходять для різних типів задач, тому важливо вибирати правильний алгоритм з',
        'PATH', 1, 4)
;



INSERT INTO THEORY(step1_id, step2_id, step3_id, step4_id)
VALUES (9, 10, 11, 12);

insert into answers (answer) values ('Складністю'), ('Підходами до сортування'), ('Часовою складністю'), ('Пам"яттю');
insert into answer_variants (correct_answer_id,
                             first_answer_id,
                             second_answer_id,
                             third_answer_id,
                             fourth_answer_id) values (1, 1, 2, 3, 4);


insert into game_task (questionNumber,
                       question,
                       answers) values (1, 'Алгоритми сортування можна класифікувати за:', 1);
INSERT INTO GAME (name, time_limit) VALUES('Алгоритми сортування: Вступ', 360);
insert into game_task_to_game (game_id, game_task_id) values (1, 1);

INSERT INTO topic (topic_name, learning_category_id) VALUES  ('Алгоритми сортування', 1);

INSERT INTO Lesson (game_id, topic_id, theory_id, lesson_plan_id) VALUES(1, 1, 2, 1);

