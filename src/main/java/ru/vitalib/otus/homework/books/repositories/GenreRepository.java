package ru.vitalib.otus.homework.books.repositories;

import java.util.List;
import ru.vitalib.otus.homework.books.domain.Genre;

public interface GenreRepository {
  Genre save(Genre author);

  Genre findById(long id);

  void delete(long id);

  long count();

  List<Genre> findAll();

  Genre findByName(String genreName);
}
