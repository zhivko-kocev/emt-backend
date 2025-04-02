package mk.ukim.finki.emt_backend.services.application.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayBookDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayWishListDto;
import mk.ukim.finki.emt_backend.services.application.WishListApplicationService;
import mk.ukim.finki.emt_backend.services.domain.WishListService;

@Service
public class WishListApplicationServiceImpl implements WishListApplicationService {

    private final WishListService list;

    public WishListApplicationServiceImpl(WishListService list) {
        this.list = list;
    }

    @Override
    public List<DisplayBookDto> listAllBooksInWishList(Long wishListId) {
        return list.listAllBooksInWishList(wishListId).stream().map(book -> DisplayDto.from(book, DisplayBookDto::new))
                .toList();
    }

    @Override
    public Optional<DisplayWishListDto> getActiveWishList(String username) {
        return Optional.of(DisplayDto.from(list.getActiveWishList(username).get(), DisplayWishListDto::new));
    }

    @Override
    public Optional<DisplayWishListDto> addProductToWishList(String username, Long productId) {
        return Optional
                .of(DisplayDto.from(list.addProductToWishList(username, productId).get(), DisplayWishListDto::new));
    }
}
