package mk.ukim.finki.emt_backend.models.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt_backend.models.enumerations.Category;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Category category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public void rentBook(int quantity) {

        if (availableCopies - quantity < 0) {
            return;
        }

        availableCopies -= quantity;
    }

}
