package mk.ukim.finki.emt_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.emt_backend.models.Book;
import mk.ukim.finki.emt_backend.models.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.category = :category")
    List<Book> filterByCategory(@Param("category") Category category);
}
