package mk.ukim.finki.emt_backend.dtos.displayImpls;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.models.domain.Book;
import mk.ukim.finki.emt_backend.models.enumerations.Category;

public record DisplayBookDto(Long id, String name, Category category, DisplayAuthorDto author,
        Integer availableCopies) {
    public DisplayBookDto(Book book) {
        this(book.getId(), book.getName(), book.getCategory(), DisplayDto.from(book.getAuthor(), DisplayAuthorDto::new),
                book.getAvailableCopies());
    }
}
