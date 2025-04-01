package mk.ukim.finki.emt_backend.services.application.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.dtos.createImpls.CreateCountryDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayCountryDto;
import mk.ukim.finki.emt_backend.models.domain.Country;
import mk.ukim.finki.emt_backend.services.application.CountryApplicationService;
import mk.ukim.finki.emt_backend.services.domain.CountryService;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private CountryService countries;

    public CountryApplicationServiceImpl(CountryService countries) {
        this.countries = countries;
    }

    @Override
    public void deleteById(Long id) {
        countries.deleteById(id);
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countries.findAll().stream().map(country -> DisplayDto.from(country, DisplayCountryDto::new)).toList();
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        Optional<Country> found = countries.findById(id);

        if (found.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(DisplayDto.from(found.get(), DisplayCountryDto::new));
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        Optional<Country> saved = countries.save(country.to());

        if (saved.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(saved.get(), DisplayCountryDto::new));
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto country) {

        Optional<Country> updated = countries.update(id, country.to());

        if (updated.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(DisplayDto.from(updated.get(), DisplayCountryDto::new));
    }

}
