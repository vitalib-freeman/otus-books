package ru.vitalib.otus.homework.books.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vitalib.otus.homework.books.domain.Genre;
import ru.vitalib.otus.homework.books.exception.GenreNotFoundException;

import java.util.Optional;
import ru.vitalib.otus.homework.books.dao.GenreDao;

@Service
@AllArgsConstructor
public class SimpleGenreService implements GenreService {
  public final GenreDao genreDao;

  @Override
  public Genre getGenreByName(String genreName) {
    return genreDao.findByName(genreName).orElseThrow(GenreNotFoundException::new);
  }
}
