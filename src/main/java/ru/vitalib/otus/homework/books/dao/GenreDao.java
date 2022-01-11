package ru.vitalib.otus.homework.books.dao;

import java.util.List;
import ru.vitalib.otus.homework.books.domain.Genre;

public interface GenreDao {
  Genre save(Genre author);

  Genre findById(long id);

  void delete(long id);

  long count();

  List<Genre> findAll();

  Genre findByName(String genreName);
}
