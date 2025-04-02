package mk.ukim.finki.emt_backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayWishListDto;
import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.services.application.WishListApplicationService;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "WishList API", description = "Endpoints for managing the wishlist")
public class WishListController {

    private final WishListApplicationService list;

    public WishListController(WishListApplicationService list) {
        this.list = list;
    }

    @Operation(summary = "Get active wishlist", description = "Retrieves the active wishlist for the logged-in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shopping cart retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Shopping cart not found")
    })
    @GetMapping
    public ResponseEntity<DisplayWishListDto> getActiveShoppingCart(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return list.getActiveWishList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add product to wishlist", description = "Adds a product to the wishlist for the logged-in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added to shopping cart successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Product not found") })
    @PostMapping("/add/{id}")
    public ResponseEntity<DisplayWishListDto> addProductToShoppingCart(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            System.out.println(user.getUsername());
            System.out.println(id);
            return list.addProductToWishList(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
