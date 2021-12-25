package io.yadnyesh.springbootunittestrahulshetty.repository;

import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository
        extends JpaRepository<Library, String>, LibraryRepositoryCustom {

}
