package mk.ukim.finki.emt_backend.services.application.implementations;

import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.dtos.DisplayDto;
import mk.ukim.finki.emt_backend.dtos.createImpls.CreateUserDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayUserDto;
import mk.ukim.finki.emt_backend.dtos.loginImpls.LoginUserDto;
import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.services.application.UserApplicationService;
import mk.ukim.finki.emt_backend.services.domain.UserService;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role());
        return Optional.of(DisplayDto.from(user, DisplayUserDto::new));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()), DisplayUserDto::new));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayDto.from(userService.findByUsername(username), DisplayUserDto::new));
    }
}
