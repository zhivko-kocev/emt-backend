package mk.ukim.finki.emt_backend.dtos;

import mk.ukim.finki.emt_backend.models.Author;
import mk.ukim.finki.emt_backend.models.Category;

public record BookDto(String name, Category category, Author author, Integer availableCopies) {

}
