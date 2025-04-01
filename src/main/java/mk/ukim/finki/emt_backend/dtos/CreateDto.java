package mk.ukim.finki.emt_backend.dtos;

public interface CreateDto<T> {
    T to();

    T toWithData(Object additionalData);
}
