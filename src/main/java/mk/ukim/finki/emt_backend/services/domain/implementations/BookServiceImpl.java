package mk.ukim.finki.emt_backend.services.domain.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.models.domain.Book;
import mk.ukim.finki.emt_backend.models.enumerations.Category;
import mk.ukim.finki.emt_backend.models.views.BooksPerAuthor;
import mk.ukim.finki.emt_backend.repositories.BookRepository;
import mk.ukim.finki.emt_backend.repositories.BooksPerAuthorRepository;
import mk.ukim.finki.emt_backend.services.domain.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository books;
    private BooksPerAuthorRepository bpa;

    public BookServiceImpl(BookRepository books, BooksPerAuthorRepository bpa) {
        this.books = books;
        this.bpa = bpa;
    }

    @Override
    public void deleteById(Long id) {
        books.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return books.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return books.findById(id);
    }

    @Override
    public Optional<Book> save(Book book) {

        if (book.getAuthor() == null
                || book.getAvailableCopies() == null
                || book.getCategory() == null
                || book.getName() == null || book.getName().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(books.save(book));
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        Optional<Book> found = books.findById(id);

        if (!found.isPresent()) {
            return Optional.empty();
        }

        Book b = found.get();

        if (book.getAuthor() != null) {
            b.setAuthor(book.getAuthor());
        }

        if (book.getAvailableCopies() != null) {
            b.setAvailableCopies(book.getAvailableCopies());
        }

        if (book.getCategory() != null) {
            b.setCategory(book.getCategory());
        }

        if (book.getName() != null && !book.getName().isEmpty()) {
            b.setName(book.getName());
        }
        return Optional.of(this.books.save(b));
    }

    @Override
    public List<Book> filterByCategory(Category category) {

        return books.filterByCategory(category);
    }

    @Override
    public void rentBook(Long id, int quantity) {
        Optional<Book> found = books.findById(id);

        if (!found.isPresent()) {
            return;
        }

        Book b = found.get();

        b.rentBook(quantity);

        books.save(b);
    }

    @Override
    public void refreshMaterializedView() {
        bpa.refreshMaterializedView();
    }

    public List<BooksPerAuthor> findAllApc() {
        return bpa.findAll();
    }

}
