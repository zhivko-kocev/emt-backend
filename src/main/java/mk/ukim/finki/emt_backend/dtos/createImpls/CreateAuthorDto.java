package mk.ukim.finki.emt_backend.dtos.createImpls;

import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.CreateDto;
import mk.ukim.finki.emt_backend.models.domain.Author;
import mk.ukim.finki.emt_backend.models.domain.Country;
import mk.ukim.finki.emt_backend.services.domain.CountryService;

public record CreateAuthorDto(String name, String surname, Long country) implements CreateDto<Author> {

    @Override
    public Author to() {
        throw new UnsupportedOperationException("Unimplemented method 'to'");
    }

    @Override
    public Author toWithData(Object additionalData) {
        CountryService countries = (CountryService) additionalData;
        Optional<Country> found = countries.findById(country);

        if (found.isEmpty()) {
            throw new UnsupportedOperationException("Unimplemented method 'to'");
        }

        return new Author(name, surname, countries.findById(country).get());
    }
}
