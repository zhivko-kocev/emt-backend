package mk.ukim.finki.emt_backend.services.application.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.dtos.createImpls.CreateAuthorDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayAuthorDto;
import mk.ukim.finki.emt_backend.models.domain.Author;
import mk.ukim.finki.emt_backend.services.application.AuthorApplicationService;
import mk.ukim.finki.emt_backend.services.domain.AuthorService;
import mk.ukim.finki.emt_backend.services.domain.CountryService;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private AuthorService authors;
    private CountryService countries;

    public AuthorApplicationServiceImpl(AuthorService authors, CountryService countries) {
        this.authors = authors;
        this.countries = countries;
    }

    @Override
    public void deleteById(Long id) {
        authors.deleteById(id);
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return authors.findAll().stream().map(author -> DisplayDto.from(author, DisplayAuthorDto::new)).toList();
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {

        Optional<Author> found = authors.findById(id);

        if (found.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(found.get(), DisplayAuthorDto::new));
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto author) {

        Optional<Author> saved = authors.save(author.toWithData(countries));

        if (saved.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(saved.get(), DisplayAuthorDto::new));
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto author) {

        Optional<Author> updated = authors.update(id, author.toWithData(countries));

        if (updated.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(updated.get(), DisplayAuthorDto::new));
    }

}
