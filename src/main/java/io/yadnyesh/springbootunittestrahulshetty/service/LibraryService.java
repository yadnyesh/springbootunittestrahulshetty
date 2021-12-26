package io.yadnyesh.springbootunittestrahulshetty.service;

import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepository;

//    public LibraryService(LibraryRepository libraryRepository) {
//        this.libraryRepository = libraryRepository;
//    }

    public String buildId(String isbn, int aisle) {
        if(isbn.startsWith("Z"))
        {
            return "Old"+isbn+aisle;
        }
        return isbn+aisle;
    }

    public boolean checkIfBookAlreadyExists(String bookId) {
        return libraryRepository.findById(bookId).isPresent();
    }
}
