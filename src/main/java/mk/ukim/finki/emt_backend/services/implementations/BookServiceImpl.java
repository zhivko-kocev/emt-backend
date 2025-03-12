package mk.ukim.finki.emt_backend.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.BookDto;
import mk.ukim.finki.emt_backend.models.Book;
import mk.ukim.finki.emt_backend.models.Category;
import mk.ukim.finki.emt_backend.repositories.BookRepository;
import mk.ukim.finki.emt_backend.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository books;

    public BookServiceImpl(BookRepository books) {
        this.books = books;
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
    public Optional<Book> save(BookDto book) {

        if (book.author() == null
                || book.availableCopies() == null
                || book.category() == null
                || book.name() == null || book.name().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(books.save(new Book(book.name(), book.category(), book.author(), book.availableCopies())));
    }

    @Override
    public Optional<Book> update(Long id, BookDto book) {
        Optional<Book> found = books.findById(id);

        if (!found.isPresent()) {
            return Optional.empty();
        }

        Book b = found.get();

        if (book.author() != null) {
            b.setAuthor(book.author());
        }

        if (book.availableCopies() != null) {
            b.setAvailableCopies(book.availableCopies());
        }

        if (book.category() != null) {
            b.setCategory(book.category());
        }

        if (book.name() != null && !book.name().isEmpty()) {
            b.setName(book.name());
        }
        return Optional.of(this.books.save(b));
    }

    @Override
    public List<Book> filterByCategory(Category category) {

        return books.filterByCategory(category);
    }

    @Override
    public void rentBook(Long id) {
        Optional<Book> found = books.findById(id);

        if (!found.isPresent()) {
            return;
        }

        Book b = found.get();

        b.rentBook();

        books.save(b);
    }

}
