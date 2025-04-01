package mk.ukim.finki.emt_backend.dtos.displayImpls;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.models.domain.Author;

public record DisplayAuthorDto(Long id, String name, String surname, DisplayCountryDto country) {

    public DisplayAuthorDto(Author author) {
        this(author.getId(), author.getName(), author.getSurname(),
                DisplayDto.from(author.getCountry(), DisplayCountryDto::new));
    }
}
