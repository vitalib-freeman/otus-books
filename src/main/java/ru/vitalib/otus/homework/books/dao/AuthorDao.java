package ru.vitalib.otus.homework.books.dao;

import java.util.List;
import ru.vitalib.otus.homework.books.domain.Author;

public interface AuthorDao {
  Author save(Author author);

  Author findById(long id);

  void delete(Author author);

  long count();

  List<Author> findAll();

  Author findByName(String authorName);
}
