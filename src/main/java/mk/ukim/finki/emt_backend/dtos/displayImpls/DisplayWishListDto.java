package mk.ukim.finki.emt_backend.dtos.displayImpls;

import java.time.LocalDateTime;
import java.util.Map;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.models.domain.WishList;
import mk.ukim.finki.emt_backend.models.enumerations.WishListStatus;

public record DisplayWishListDto(Long id, LocalDateTime dateCreated, DisplayUserDto user, Map<Long, Integer> books,
        WishListStatus status) {

    public DisplayWishListDto(WishList list) {
        this(list.getId(), list.getDateCreated(), DisplayDto.from(list.getUser(), DisplayUserDto::new), list.getBooks(),
                list.getStatus());
    }

}
