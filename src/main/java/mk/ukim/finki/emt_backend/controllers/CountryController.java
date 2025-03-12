package mk.ukim.finki.emt_backend.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mk.ukim.finki.emt_backend.dtos.CountryDto;
import mk.ukim.finki.emt_backend.models.Country;
import mk.ukim.finki.emt_backend.services.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countries;

    public CountryController(CountryService countries) {
        this.countries = countries;
    }

    @GetMapping
    @Operation(summary = "Get all countries", description = "Fetches a list of all countries available in the database.")
    @ApiResponse(responseCode = "200", description = "List of countries fetched successfully")
    public List<Country> findAll() {
        return countries.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a country by ID", description = "Fetches a country by its ID from the database.")
    @ApiResponse(responseCode = "200", description = "Country found")
    @ApiResponse(responseCode = "404", description = "Country not found")
    public ResponseEntity<Country> findById(
            @Parameter(description = "ID of the country to be fetched") @PathVariable Long id) {
        return countries.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new country", description = "Adds a new country to the database.")
    @ApiResponse(responseCode = "200", description = "Country added successfully")
    @ApiResponse(responseCode = "404", description = "Unable to add country")
    public ResponseEntity<Country> save(@RequestBody CountryDto country) {
        return countries.save(country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update a country", description = "Updates details of an existing country in the database.")
    @ApiResponse(responseCode = "200", description = "Country updated successfully")
    @ApiResponse(responseCode = "404", description = "Country not found")
    public ResponseEntity<Country> update(
            @Parameter(description = "ID of the country to be updated") @PathVariable Long id,
            @RequestBody CountryDto country) {
        return countries.update(id, country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a country", description = "Deletes a country by ID from the database.")
    @ApiResponse(responseCode = "200", description = "Country deleted successfully")
    @ApiResponse(responseCode = "404", description = "Country not found")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (countries.findById(id).isPresent()) {
            countries.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
