package mk.ukim.finki.emt_backend.services.application;

import java.util.*;

import mk.ukim.finki.emt_backend.dtos.createImpls.CreateAuthorDto;
import mk.ukim.finki.emt_backend.dtos.displayImpls.DisplayAuthorDto;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> findAll();

    Optional<DisplayAuthorDto> findById(Long id);

    Optional<DisplayAuthorDto> save(CreateAuthorDto author);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto author);

    void deleteById(Long id);

}
