package mk.ukim.finki.emt_backend.dtos.createImpls;

import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.CreateDto;
import mk.ukim.finki.emt_backend.models.domain.Author;
import mk.ukim.finki.emt_backend.models.domain.Book;
import mk.ukim.finki.emt_backend.models.enumerations.Category;
import mk.ukim.finki.emt_backend.services.domain.AuthorService;

public record CreateBookDto(String name, Category category, Long author, Integer availableCopies)
        implements CreateDto<Book> {

    @Override
    public Book to() {

        throw new UnsupportedOperationException("Unimplemented method 'to'");
    }

    @Override
    public Book toWithData(Object additionalData) {
        AuthorService authors = (AuthorService) additionalData;
        Optional<Author> found = authors.findById(author);

        if (found.isEmpty()) {
            throw new UnsupportedOperationException("Unimplemented method 'to'");
        }

        return new Book(name, category, found.get(), availableCopies);
    }
}
