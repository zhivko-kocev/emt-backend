package mk.ukim.finki.emt_backend.services.application;

import java.util.List;
import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayBookDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayWishListDto;

public interface WishListApplicationService {
    List<DisplayBookDto> listAllBooksInWishList(Long wishListId);

    Optional<DisplayWishListDto> getActiveWishList(String username);

    Optional<DisplayWishListDto> addProductToWishList(String username, Long bookId);
}
