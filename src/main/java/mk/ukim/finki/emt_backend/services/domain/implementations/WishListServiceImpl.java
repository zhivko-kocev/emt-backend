package mk.ukim.finki.emt_backend.services.domain.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.models.domain.Book;
import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.models.domain.WishList;
import mk.ukim.finki.emt_backend.models.enumerations.WishListStatus;
import mk.ukim.finki.emt_backend.repositories.WishListRepository;
import mk.ukim.finki.emt_backend.services.domain.BookService;
import mk.ukim.finki.emt_backend.services.domain.UserService;
import mk.ukim.finki.emt_backend.services.domain.WishListService;

@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository list;
    private final UserService users;
    private final BookService books;

    public WishListServiceImpl(
            WishListRepository list,
            UserService users,
            BookService books) {
        this.list = list;
        this.users = users;
        this.books = books;
    }

    @Override
    public List<Book> listAllBooksInWishList(Long cartId) {
        if (list.findById(cartId).isEmpty()) {
            // throw smth
        }

        return list.findById(cartId).get().getBooks().keySet().stream().map(key -> books.findById(key).get()).toList();
    }

    @Override
    public Optional<WishList> getActiveWishList(String username) {
        User user = users.findByUsername(username);

        return Optional.of(list.findByUserAndStatus(
                user,
                WishListStatus.CREATED).orElseGet(() -> list.save(new WishList(user))));
    }

    @Override
    public Optional<WishList> addProductToWishList(String username, Long productId) {

        WishList wishlist = getActiveWishList(username).get();

        Optional<Book> book = books.findById(productId);// throw exec later

        if (book.isEmpty()) {
            // throw smth later
        }
        if (!wishlist.getBooks().containsKey(book.get().getId())) {
            wishlist.getBooks().put(productId, 1);

            return Optional.of(list.save(wishlist));
        }

        wishlist.getBooks().put(book.get().getId(), wishlist.getBooks().get(book.get().getId()) + 1);

        return Optional.of(list.save(wishlist));

    }
}