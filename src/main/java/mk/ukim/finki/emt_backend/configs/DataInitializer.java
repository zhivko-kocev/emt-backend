package mk.ukim.finki.emt_backend.configs;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt_backend.models.domain.Author;
import mk.ukim.finki.emt_backend.models.domain.Country;
import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.models.enumerations.Role;
import mk.ukim.finki.emt_backend.repositories.UserRepository;
import mk.ukim.finki.emt_backend.services.domain.AuthorService;
import mk.ukim.finki.emt_backend.services.domain.CountryService;

@Component
public class DataInitializer {
    private final AuthorService authors;
    private final CountryService countries;
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AuthorService authors, CountryService countries, UserRepository users,
            PasswordEncoder passwordEncoder) {
        this.authors = authors;
        this.countries = countries;
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (countries.findAll().isEmpty()) {
            countries.save(new Country("USA", "North America"));
            countries.save(new Country("UK", "Europe"));
            countries.save(new Country("Germany", "Europe"));
            countries.save(new Country("France", "Europe"));
            countries.save(new Country("Russia", "Europe"));
            countries.save(new Country("Italy", "Europe"));
            countries.save(new Country("Spain", "Europe"));
            countries.save(new Country("Japan", "Asia"));
            countries.save(new Country("China", "Asia"));
            countries.save(new Country("India", "Asia"));
        }

        if (authors.findAll().isEmpty()) {
            authors.save(new Author("Mark", "Twain", countries.findById(1L).get()));
            authors.save(new Author("William", "Shakespeare", countries.findById(2L).get()));
            authors.save(new Author("Johann", "Goethe", countries.findById(3L).get()));
            authors.save(new Author("Victor", "Hugo", countries.findById(4L).get()));
            authors.save(new Author("Leo", "Tolstoy", countries.findById(5L).get()));
            authors.save(new Author("Dante", "Alighieri", countries.findById(6L).get()));
            authors.save(new Author("Miguel", "de Cervantes", countries.findById(7L).get()));
            authors.save(new Author("Haruki", "Murakami", countries.findById(8L).get()));
            authors.save(new Author("Lu", "Xun", countries.findById(9L).get()));
            authors.save(new Author("Rabindranath", "Tagore", countries.findById(10L).get()));
        }

        users.save(new User(
                "at",
                passwordEncoder.encode("at"),
                "Ana",
                "Todorovska",
                Role.ROLE_LIBRARIAN));

        users.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER));

    }
}
