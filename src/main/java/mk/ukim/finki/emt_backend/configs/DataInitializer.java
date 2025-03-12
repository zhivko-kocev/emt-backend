package mk.ukim.finki.emt_backend.configs;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt_backend.dtos.AuthorDto;
import mk.ukim.finki.emt_backend.dtos.CountryDto;
import mk.ukim.finki.emt_backend.services.AuthorService;
import mk.ukim.finki.emt_backend.services.CountryService;

@Component
public class DataInitializer {
    private final AuthorService authors;
    private final CountryService countries;

    public DataInitializer(AuthorService authors, CountryService countries) {
        this.authors = authors;
        this.countries = countries;
    }

    @PostConstruct
    public void init() {
        if (countries.findAll().isEmpty()) {
            countries.save(new CountryDto("USA", "North America"));
            countries.save(new CountryDto("UK", "Europe"));
            countries.save(new CountryDto("Germany", "Europe"));
        }

        if (authors.findAll().isEmpty()) {
            authors.save(new AuthorDto("Mark", "Twain", countries.findById(1L).get()));
            authors.save(new AuthorDto("William", "Shakespeare", countries.findById(2L).get()));
            authors.save(new AuthorDto("Johann", "Goethe", countries.findById(3L).get()));
        }
    }
}
