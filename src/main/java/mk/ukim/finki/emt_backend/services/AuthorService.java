package mk.ukim.finki.emt_backend.services;

import java.util.*;

import mk.ukim.finki.emt_backend.dtos.AuthorDto;
import mk.ukim.finki.emt_backend.models.Author;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(AuthorDto author);

    Optional<Author> update(Long id, AuthorDto author);

    void deleteById(Long id);

}
