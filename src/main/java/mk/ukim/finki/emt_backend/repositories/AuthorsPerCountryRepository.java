package mk.ukim.finki.emt_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import mk.ukim.finki.emt_backend.models.views.AuthorsPerCountry;

public interface AuthorsPerCountryRepository extends JpaRepository<AuthorsPerCountry, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.authors_per_country", nativeQuery = true)
    void refreshMaterializedView();

}
