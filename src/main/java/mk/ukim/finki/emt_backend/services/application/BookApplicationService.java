package mk.ukim.finki.emt_backend.services.application;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.createImpls.CreateBookDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayBookDto;
import mk.ukim.finki.emt_backend.models.enumerations.Category;

public interface BookApplicationService {
    List<DisplayBookDto> findAll();

    List<DisplayBookDto> filterByCategory(Category category);

    Optional<DisplayBookDto> findById(Long id);

    Optional<DisplayBookDto> save(CreateBookDto book);

    Optional<DisplayBookDto> update(Long id, CreateBookDto book);

    void deleteById(Long id);

    void rentBook(Long id, int quantity);
}
