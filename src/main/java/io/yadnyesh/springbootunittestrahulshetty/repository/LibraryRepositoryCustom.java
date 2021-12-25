package io.yadnyesh.springbootunittestrahulshetty.repository;

import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepositoryCustom {
    List<Library> findAllBooksByAuthor(String authorName);
}
