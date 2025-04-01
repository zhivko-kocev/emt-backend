package mk.ukim.finki.emt_backend.dtos;

import java.util.function.Function;

public interface DisplayDto {
    static <T, Q> T from(Q entity, Function<Q, T> mapper) {
        return mapper.apply(entity);
    }
}
