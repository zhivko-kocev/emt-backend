package mk.ukim.finki.emt_backend.services.domain.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.models.enumerations.Role;
import mk.ukim.finki.emt_backend.repositories.UserRepository;
import mk.ukim.finki.emt_backend.services.domain.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole) {
        if (username == null || username.isEmpty() || password == null ||
                password.isEmpty())
            return null;
        if (!password.equals(repeatPassword))
            return null;
        if (userRepository.findByUsername(username).isPresent())
            return null;
        User user = new User(username, passwordEncoder.encode(password), name,
                surname, userRole);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null ||
                password.isEmpty()) {
            return null;
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(null);
    }
}
