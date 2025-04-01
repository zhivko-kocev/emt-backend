package mk.ukim.finki.emt_backend.dtos.displayImpls;

import mk.ukim.finki.emt_backend.models.domain.User;
import mk.ukim.finki.emt_backend.models.enumerations.Role;

public record DisplayUserDto(String username, String name, String surname,
                Role role) {

        public DisplayUserDto(User user) {
                this(user.getUsername(), user.getName(), user.getSurname(), user.getRole());
        }

}