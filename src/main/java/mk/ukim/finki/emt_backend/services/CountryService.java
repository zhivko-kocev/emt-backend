package mk.ukim.finki.emt_backend.services;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.CountryDto;
import mk.ukim.finki.emt_backend.models.Country;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(CountryDto country);

    Optional<Country> update(Long id, CountryDto country);

    void deleteById(Long id);
}
