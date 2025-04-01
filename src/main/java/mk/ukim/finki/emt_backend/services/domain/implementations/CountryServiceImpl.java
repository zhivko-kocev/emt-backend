package mk.ukim.finki.emt_backend.services.domain.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.models.domain.Country;
import mk.ukim.finki.emt_backend.repositories.CountryRepository;
import mk.ukim.finki.emt_backend.services.domain.CountryService;

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
    public Optional<Country> save(Country country) {

        if (country.getName() == null
                || country.getName().isEmpty()
                || country.getContinent() == null
                || country.getContinent().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(countries.save(country));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {

        Optional<Country> found = countries.findById(id);

        if (!found.isPresent()) {
            return Optional.empty();
        }

        Country c = found.get();

        if (country.getName() != null && !country.getContinent().isEmpty()) {
            c.setName(country.getName());
        }

        if (country.getContinent() != null && !country.getContinent().isEmpty()) {
            c.setContinent(country.getContinent());
        }

        return Optional.of(countries.save(c));
    }

}
