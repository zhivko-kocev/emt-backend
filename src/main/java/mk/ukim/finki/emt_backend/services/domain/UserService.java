package mk.ukim.finki.emt_backend.services.domain;

import org.springframework.security.core.userdetails.UserDetailsService;

import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.models.enumerations.Role;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);
}
