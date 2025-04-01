package mk.ukim.finki.emt_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt_backend.dtos.createImpls.CreateUserDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayUserDto;
import mk.ukim.finki.emt_backend.dtos.loginImpls.LoginUserDto;
import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.services.application.UserApplicationService;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or passwords do not match") })
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {

        return userApplicationService.register(createUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "404", description = "Invalid username or password") })
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(HttpServletRequest request) {
        DisplayUserDto displayUserDto = userApplicationService.login(
                new LoginUserDto(request.getParameter("username"), request.getParameter("password")))
                .orElseThrow(null);

        request.getSession().setAttribute("user", new User(displayUserDto.username(), displayUserDto.name(),
                displayUserDto.surname(), displayUserDto.role().name()));
        return ResponseEntity.ok(displayUserDto);

    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
