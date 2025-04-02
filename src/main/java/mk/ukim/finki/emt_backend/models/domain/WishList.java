package mk.ukim.finki.emt_backend.models.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt_backend.models.enumerations.WishListStatus;

@Data
@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ElementCollection
    @CollectionTable(name = "wishlist_books", joinColumns = @JoinColumn(name = "wishlist_id"))
    @MapKeyColumn(name = "book_id")
    @Column(name = "quantity")
    private Map<Long, Integer> books;

    @Enumerated(EnumType.STRING)
    private WishListStatus status;

    public WishList() {
    }

    public WishList(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.books = new HashMap<>();
        this.status = WishListStatus.CREATED;
    }

}
