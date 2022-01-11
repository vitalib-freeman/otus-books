package ru.vitalib.otus.homework.books.repositories;

import java.util.List;
import ru.vitalib.otus.homework.books.domain.Author;

public interface AuthorRepository {
  Author save(Author author);

  Author findById(long id);

  void delete(long id);

  long count();

  List<Author> findAll();

  Author findByName(String authorName);
}
