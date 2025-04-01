package mk.ukim.finki.emt_backend.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mk.ukim.finki.emt_backend.dtos.createImpls.CreateBookDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayBookDto;
import mk.ukim.finki.emt_backend.models.enumerations.Category;
import mk.ukim.finki.emt_backend.services.application.BookApplicationService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookApplicationService books;

    public BookController(BookApplicationService books) {
        this.books = books;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Fetches a list of all books available in the library.")
    @ApiResponse(responseCode = "200", description = "List of books fetched successfully")
    public List<DisplayBookDto> findAll(@RequestParam(required = false) Category category) {

        if (category != null) {
            return books.filterByCategory(category);
        }
        return books.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", description = "Fetches a book by its ID from the library.")
    @ApiResponse(responseCode = "200", description = "Book found")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<DisplayBookDto> findById(
            @Parameter(description = "ID of the book to be fetched") @PathVariable Long id) {
        return books.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Adds a new book to the library database.")
    @ApiResponse(responseCode = "200", description = "Book added successfully")
    @ApiResponse(responseCode = "404", description = "Unable to add book")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto book) {
        System.out.println(book);
        return books.save(book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update a book", description = "Updates details of an existing book in the library.")
    @ApiResponse(responseCode = "200", description = "Book updated successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<DisplayBookDto> update(
            @Parameter(description = "ID of the book to be updated") @PathVariable Long id,
            @RequestBody CreateBookDto book) {
        return books.update(id, book)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book by ID from the library.")
    @ApiResponse(responseCode = "200", description = "Book deleted successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (books.findById(id).isPresent()) {
            books.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
