package mk.ukim.finki.emt_backend.services.application.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.dtos.createImpls.CreateBookDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayBookDto;
import mk.ukim.finki.emt_backend.models.domain.Book;
import mk.ukim.finki.emt_backend.models.enumerations.Category;
import mk.ukim.finki.emt_backend.services.application.BookApplicationService;
import mk.ukim.finki.emt_backend.services.domain.AuthorService;
import mk.ukim.finki.emt_backend.services.domain.BookService;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private BookService books;
    private AuthorService authors;

    public BookApplicationServiceImpl(BookService books, AuthorService authors) {
        this.books = books;
        this.authors = authors;
    }

    @Override
    public void deleteById(Long id) {
        books.deleteById(id);
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return books.findAll().stream().map(book -> DisplayDto.from(book, DisplayBookDto::new)).toList();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        Optional<Book> found = books.findById(id);

        if (found.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(found.get(), DisplayBookDto::new));
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto book) {

        Optional<Book> saved = books.save(book.toWithData(authors));

        if (saved.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(saved.get(), DisplayBookDto::new));
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto book) {
        Optional<Book> updated = books.update(id, book.toWithData(authors));

        if (updated.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(updated.get(), DisplayBookDto::new));
    }

    @Override
    public List<DisplayBookDto> filterByCategory(Category category) {

        return books.filterByCategory(category).stream().map(book -> DisplayDto.from(book, DisplayBookDto::new))
                .toList();
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
