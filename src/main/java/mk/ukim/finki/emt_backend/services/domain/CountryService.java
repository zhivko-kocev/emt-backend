package mk.ukim.finki.emt_backend.services.domain;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.models.domain.Country;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(Country country);

    Optional<Country> update(Long id, Country country);

    void deleteById(Long id);
}
