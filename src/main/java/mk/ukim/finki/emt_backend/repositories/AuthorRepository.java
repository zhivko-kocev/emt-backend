package mk.ukim.finki.emt_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.emt_backend.models.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
