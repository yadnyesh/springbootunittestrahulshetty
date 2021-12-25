package io.yadnyesh.springbootunittestrahulshetty.service;

import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public String buildId(String isbn, int aisle) {
        return isbn+aisle;
    }

    public boolean checkIfBookAlreadyExists(String bookId) {
        return libraryRepository.findById(bookId).isPresent();
    }
}
