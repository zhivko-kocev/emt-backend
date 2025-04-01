package mk.ukim.finki.emt_backend.services.domain;

import java.util.*;

import mk.ukim.finki.emt_backend.models.domain.Author;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(Author author);

    Optional<Author> update(Long id, Author author);

    void deleteById(Long id);
}
