package ru.vitalib.otus.homework.books.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.vitalib.otus.homework.books.domain.Genre;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class GenreDaoJdbc implements GenreDao {
  private final NamedParameterJdbcOperations jdbc;

  @Override
  public void save(Genre genre) {
    jdbc.update(
        "insert into genre (id, name) values(:id, :name)",
        Map.of("id", genre.getId(), "name", genre.getName())
    );
  }

  @Override
  public Genre findById(long id) {
    return jdbc.queryForObject(
        "select id, name from genre where id =:id",
        Map.of("id", id),
        (rs, rowNum) -> new Genre(rs.getLong("id"), rs.getString("name"))
    );
  }

  @Override
  public void delete(long id) {
    jdbc.update("delete from genre where id = :id", Map.of("id", id));

  }

  @Override
  public void update(long id, Genre genre) {
    jdbc.update(
        "update genre set name = :name where id = :id",
        Map.of("name", genre.getName(), "id", genre.getId())
    );
  }

  @Override
  @SuppressWarnings("all")
  public int count() {
    return jdbc.queryForObject("select count(1) from genre", Map.of(), Integer.class);
  }

  @Override
  public List<Genre> findAll() {
    return jdbc.query(
        "select id, name from genre",
        (rs, rowNum) -> new Genre(rs.getLong("id"), rs.getString("name"))
    );
  }

  @Override
  public Genre findByName(String genreName) {
    return null;
  }

}
