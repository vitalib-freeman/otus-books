package ru.vitalib.otus.homework.books.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vitalib.otus.homework.books.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
  Optional<Genre> findByName(String genreName);
}
