package mk.ukim.finki.emt_backend.models.views;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "books_per_author")
@Subselect("SELECT * FROM public.books_per_author")
@Immutable
public class BooksPerAuthor {

    @Id
    private Long authorId;

    private Long numBooks;

}