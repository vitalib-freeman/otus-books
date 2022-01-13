package ru.vitalib.otus.homework.books.dao;

import ru.vitalib.otus.homework.books.domain.Comment;

public interface CommentDao {
  Comment save(Comment comment);
  void delete(Comment comment);
  Comment findById(Long id);
}
