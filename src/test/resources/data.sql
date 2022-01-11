INSERT INTO AUTHOR(id, `name`) values (1, 'Веллер Михаил');
INSERT INTO GENRE(id, `name`) values (1, 'Детектив');
INSERT INTO BOOK(id, name, genre_id, author_id) values (1, 'Хочу быть дворником', 1, 1);
INSERT INTO COMMENT(id, book_id, text) VALUES (1, 1, 'Норм');