package mk.ukim.finki.emt_backend.services;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.BookDto;
import mk.ukim.finki.emt_backend.models.Book;
import mk.ukim.finki.emt_backend.models.Category;

public interface BookService {
    List<Book> findAll();

    List<Book> filterByCategory(Category category);

    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto book);

    Optional<Book> update(Long id, BookDto book);

    void deleteById(Long id);

    void rentBook(Long id);
}
