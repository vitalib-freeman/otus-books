package ru.vitalib.otus.homework.books.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vitalib.otus.homework.books.domain.Genre;
import ru.vitalib.otus.homework.books.exception.GenreNotFoundException;

import java.util.Optional;
import ru.vitalib.otus.homework.books.repositories.GenreRepository;

@Service
@AllArgsConstructor
public class SimpleGenreService implements GenreService {
  public final GenreRepository genreRepository;

  @Override
  public Genre getGenreByName(String genreName) {
    return Optional.ofNullable(genreRepository.findByName(genreName)).orElseThrow(GenreNotFoundException::new);
  }
}
