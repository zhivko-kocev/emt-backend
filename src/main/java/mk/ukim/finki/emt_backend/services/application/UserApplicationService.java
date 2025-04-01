package mk.ukim.finki.emt_backend.services.application;

import java.util.Optional;

import mk.ukim.finki.emt_backend.dtos.createImpls.CreateUserDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayUserDto;
import mk.ukim.finki.emt_backend.dtos.loginImpls.LoginUserDto;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
