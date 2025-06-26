package mk.ukim.finki.emt_backend.services.domain.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.models.domain.Author;
import mk.ukim.finki.emt_backend.models.views.AuthorsPerCountry;
import mk.ukim.finki.emt_backend.repositories.AuthorRepository;
import mk.ukim.finki.emt_backend.repositories.AuthorsPerCountryRepository;
import mk.ukim.finki.emt_backend.services.domain.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authors;
    private AuthorsPerCountryRepository authpcountries;

    public AuthorServiceImpl(AuthorRepository authors, AuthorsPerCountryRepository authpcountries) {
        this.authpcountries = authpcountries;
        this.authors = authors;
    }

    @Override
    public void deleteById(Long id) {
        authors.deleteById(id);
    }

    @Override
    public List<Author> findAll() {
        return authors.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authors.findById(id);
    }

    @Override
    public Optional<Author> save(Author author) {

        if (author.getCountry() == null
                || author.getName() == null
                || author.getName().isEmpty()
                || author.getSurname() == null
                || author.getSurname().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(authors.save(author));
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        Optional<Author> found = authors.findById(id);

        if (!found.isPresent()) {
            return Optional.empty();
        }

        Author a = found.get();

        if (author.getCountry() != null) {
            a.setCountry(author.getCountry());
        }

        if (author.getName() != null && !author.getName().isEmpty()) {
            a.setName(author.getName());
        }

        if (author.getSurname() != null && !author.getSurname().isEmpty()) {
            a.setSurname(author.getSurname());
        }
        return Optional.of(authors.save(a));
    }

    @Override
    public void refreshMaterializedView() {
        authpcountries.refreshMaterializedView();
    }

    public List<AuthorsPerCountry> findAllApc() {
        return authpcountries.findAll();
    }

}
