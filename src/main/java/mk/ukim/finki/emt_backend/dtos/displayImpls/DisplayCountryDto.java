package mk.ukim.finki.emt_backend.dtos.displayImpls;

import mk.ukim.finki.emt_backend.models.domain.Country;

public record DisplayCountryDto(Long id, String name, String continent) {
    public DisplayCountryDto(Country country) {
        this(country.getId(), country.getName(), country.getContinent());
    }
}
