package mk.ukim.finki.emt_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "countries")
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String continent;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

}
