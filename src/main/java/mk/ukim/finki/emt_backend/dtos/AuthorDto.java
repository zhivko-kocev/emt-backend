package mk.ukim.finki.emt_backend.dtos;

import mk.ukim.finki.emt_backend.models.Country;

public record AuthorDto(String name, String surname, Country country) {
}
