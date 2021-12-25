package io.yadnyesh.springbootunittestrahulshetty.controller;

import io.yadnyesh.springbootunittestrahulshetty.AddBookResponse;
import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryController {

    LibraryRepository libraryRepository;

    public LibraryController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @PostMapping("/books")
    public ResponseEntity<AddBookResponse> addBookToLibrary(@RequestBody Library library) {
        String id = library.getIsbn()+library.getAisle();
        library.setId(id);
        libraryRepository.save(library);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("unique", id);
        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setMsg("The book is successfully added to library collection");
        addBookResponse.setId(id);
        return new ResponseEntity<>(addBookResponse, httpHeaders, HttpStatus.CREATED);
    }
}
