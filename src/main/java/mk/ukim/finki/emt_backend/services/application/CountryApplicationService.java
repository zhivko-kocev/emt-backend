package mk.ukim.finki.emt_backend.services.application;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.createImpls.CreateCountryDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayCountryDto;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    void deleteById(Long id);
}
