package mk.ukim.finki.emt_backend.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.AuthorDto;
import mk.ukim.finki.emt_backend.models.Author;
import mk.ukim.finki.emt_backend.repositories.AuthorRepository;
import mk.ukim.finki.emt_backend.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authors;

    public AuthorServiceImpl(AuthorRepository authors) {
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
    public Optional<Author> save(AuthorDto author) {

        if (author.country() == null
                || author.name() == null
                || author.name().isEmpty()
                || author.surname() == null
                || author.surname().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(authors.save(new Author(author.name(), author.surname(), author.country())));
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto author) {
        Optional<Author> found = authors.findById(id);

        if (!found.isPresent()) {
            return Optional.empty();
        }

        Author a = found.get();

        if (author.country() != null) {
            a.setCountry(author.country());
        }

        if (author.name() != null && !author.name().isEmpty()) {
            a.setName(author.name());
        }

        if (author.surname() != null && !author.surname().isEmpty()) {
            a.setSurname(author.surname());
        }
        return Optional.of(authors.save(a));
    }

}
