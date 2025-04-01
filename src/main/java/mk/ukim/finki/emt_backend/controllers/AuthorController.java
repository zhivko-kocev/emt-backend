package mk.ukim.finki.emt_backend.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mk.ukim.finki.emt_backend.dtos.createImpls.CreateAuthorDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayAuthorDto;
import mk.ukim.finki.emt_backend.services.application.AuthorApplicationService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorApplicationService authors;

    public AuthorController(AuthorApplicationService authors) {
        this.authors = authors;
    }

    @GetMapping
    @Operation(summary = "Get all authors", description = "Fetches a list of all authors in the system.")
    @ApiResponse(responseCode = "200", description = "List of authors fetched successfully")
    public List<DisplayAuthorDto> findAll() {

        return authors.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an author by ID", description = "Fetches an author by their ID from the system.")
    @ApiResponse(responseCode = "200", description = "Author found")
    @ApiResponse(responseCode = "404", description = "Author not found")
    public ResponseEntity<DisplayAuthorDto> findById(
            @Parameter(description = "ID of the author to be fetched") @PathVariable Long id) {
        return authors.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new author", description = "Adds a new author to the system.")
    @ApiResponse(responseCode = "200", description = "Author added successfully")
    @ApiResponse(responseCode = "404", description = "Unable to add author")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto author) {
        return authors.save(author)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an author", description = "Updates the details of an existing author in the system.")
    @ApiResponse(responseCode = "200", description = "Author updated successfully")
    @ApiResponse(responseCode = "404", description = "Author not found")
    public ResponseEntity<DisplayAuthorDto> update(
            @Parameter(description = "ID of the author to be updated") @PathVariable Long id,
            @RequestBody CreateAuthorDto author) {
        return authors.update(id, author)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author by ID from the system.")
    @ApiResponse(responseCode = "200", description = "Author deleted successfully")
    @ApiResponse(responseCode = "404", description = "Author not found")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (authors.findById(id).isPresent()) {
            authors.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
