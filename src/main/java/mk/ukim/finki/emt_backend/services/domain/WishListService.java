package mk.ukim.finki.emt_backend.services.domain;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.models.domain.Book;
import mk.ukim.finki.emt_backend.models.domain.WishList;

public interface WishListService {

    List<Book> listAllBooksInWishList(Long wishListId);

    Optional<WishList> getActiveWishList(String username);

    Optional<WishList> addProductToWishList(String username, Long bookId);
}
