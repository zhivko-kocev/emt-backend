package mk.ukim.finki.emt_backend.models.views;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "authors_per_country")
@Subselect("SELECT * FROM public.authors_per_country")
@Immutable
public class AuthorsPerCountry {

    @Id
    private Long countryId;

    private Long numAuthors;

}
