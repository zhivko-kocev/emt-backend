package mk.ukim.finki.emt_backend.dtos.createImpls;

import mk.ukim.finki.emt_backend.dtos.CreateDto;
import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.models.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role) implements CreateDto<User> {

    @Override
    public User to() {
        if (!password.equals(repeatPassword)) {
            throw new UnsupportedOperationException("Unimplemented method 'toWithData'");
        }
        return new User(username, password, name, surname, role);
    }

    @Override
    public User toWithData(Object additionalData) {
        throw new UnsupportedOperationException("Unimplemented method 'toWithData'");
    }

}
