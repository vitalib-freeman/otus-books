package ru.vitalib.otus.homework.books.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.vitalib.otus.homework.books.domain.Author;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
  private final NamedParameterJdbcOperations jdbc;

  @Override
  public void save(Author author) {
    jdbc.update(
        "insert into author (id, name) values(:id, :name)",
        Map.of("id", author.getId(), "name", author.getName())
    );
  }

  @Override
  public Author findById(long id) {
    return jdbc.queryForObject(
        "select id, name from author where id =:id",
        Map.of("id", id),
        (rs, rowNum) -> new Author(rs.getLong("id"), rs.getString("name"))
    );
  }

  @Override
  public void delete(long id) {
    jdbc.update("delete from author where id = :id", Map.of("id", id));

  }

  @Override
  public void update(long id, Author author) {
    jdbc.update(
        "update author set name = :name where id = :id",
        Map.of("name", author.getName(), "id", author.getId())
    );
  }

  @Override
  @SuppressWarnings("all")
  public int count() {
    return jdbc.queryForObject("select count(1) from author", Map.of(), Integer.class);
  }

  @Override
  public List<Author> findAll() {
    return jdbc.query(
        "select id, name from author",
        (rs, rowNum) -> new Author(rs.getLong("id"), rs.getString("name"))
    );
  }
}
