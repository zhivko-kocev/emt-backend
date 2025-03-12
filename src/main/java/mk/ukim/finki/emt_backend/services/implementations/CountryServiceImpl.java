package mk.ukim.finki.emt_backend.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.CountryDto;
import mk.ukim.finki.emt_backend.models.Country;
import mk.ukim.finki.emt_backend.repositories.CountryRepository;
import mk.ukim.finki.emt_backend.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countries;

    public CountryServiceImpl(CountryRepository countries) {
        this.countries = countries;
    }

    @Override
    public void deleteById(Long id) {
        countries.deleteById(id);
    }

    @Override
    public List<Country> findAll() {
        return countries.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countries.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto country) {

        if (country.name() == null
                || country.name().isEmpty()
                || country.continent() == null
                || country.continent().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(countries.save(new Country(country.name(), country.continent())));
    }

    @Override
    public Optional<Country> update(Long id, CountryDto country) {

        Optional<Country> found = countries.findById(id);

        if (!found.isPresent()) {
            return Optional.empty();
        }

        Country c = found.get();

        if (country.name() != null && !country.continent().isEmpty()) {
            c.setName(country.name());
        }

        if (country.continent() != null && !country.continent().isEmpty()) {
            c.setContinent(country.continent());
        }

        return Optional.of(countries.save(c));
    }

}
