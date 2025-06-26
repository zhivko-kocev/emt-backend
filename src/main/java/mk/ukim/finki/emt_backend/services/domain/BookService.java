package mk.ukim.finki.emt_backend.services.domain;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.models.domain.Book;
import mk.ukim.finki.emt_backend.models.enumerations.Category;
import mk.ukim.finki.emt_backend.models.views.BooksPerAuthor;

public interface BookService {
    List<Book> findAll();

    List<Book> filterByCategory(Category category);

    Optional<Book> findById(Long id);

    Optional<Book> save(Book book);

    Optional<Book> update(Long id, Book book);

    void deleteById(Long id);

    void rentBook(Long id, int quantity);

    void refreshMaterializedView();

    List<BooksPerAuthor> findAllApc();

}
