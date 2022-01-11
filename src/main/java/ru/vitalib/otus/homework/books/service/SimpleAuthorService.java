package ru.vitalib.otus.homework.books.service;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vitalib.otus.homework.books.domain.Author;
import ru.vitalib.otus.homework.books.exception.AuthorNotFoundException;
import ru.vitalib.otus.homework.books.repositories.AuthorRepository;

@Service
@AllArgsConstructor
public class SimpleAuthorService implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Author getAuthorByName(String authorName) {
    return Optional.ofNullable(authorRepository.findByName(authorName)).orElseThrow(AuthorNotFoundException::new);
  }
}
