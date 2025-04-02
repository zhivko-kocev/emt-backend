package mk.ukim.finki.emt_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.models.domain.WishList;
import mk.ukim.finki.emt_backend.models.enumerations.WishListStatus;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    Optional<WishList> findByUserAndStatus(User user, WishListStatus status);
}
