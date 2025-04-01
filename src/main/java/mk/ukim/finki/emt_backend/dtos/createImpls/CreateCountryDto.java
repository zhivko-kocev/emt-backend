package mk.ukim.finki.emt_backend.dtos.createImpls;

import mk.ukim.finki.emt_backend.dtos.CreateDto;
import mk.ukim.finki.emt_backend.models.domain.Country;

public record CreateCountryDto(String name, String continent) implements CreateDto<Country> {

    @Override
    public Country to() {
        return new Country(name, continent);
    }

    @Override
    public Country toWithData(Object additionalData) {
        throw new UnsupportedOperationException("Unimplemented method 'toWithData'");
    }

}