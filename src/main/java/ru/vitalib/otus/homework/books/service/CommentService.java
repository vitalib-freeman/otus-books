package ru.vitalib.otus.homework.books.service;

import java.util.List;
import ru.vitalib.otus.homework.books.dto.CommentDto;

public interface CommentService {
  List<CommentDto> getCommentsForBook(Long bookId);
}
