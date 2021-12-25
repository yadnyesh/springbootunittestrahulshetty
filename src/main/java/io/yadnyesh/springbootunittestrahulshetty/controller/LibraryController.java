package io.yadnyesh.springbootunittestrahulshetty.controller;

import io.yadnyesh.springbootunittestrahulshetty.AddBookResponse;
import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import io.yadnyesh.springbootunittestrahulshetty.service.LibraryService;
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

    LibraryService libraryService;

    public LibraryController(LibraryRepository libraryRepository, LibraryService libraryService) {
        this.libraryRepository = libraryRepository;
        this.libraryService = libraryService;
    }

    @PostMapping("/books")
    public ResponseEntity<AddBookResponse> addBookToLibrary(@RequestBody Library library) {
        String bookId = libraryService.buildId(library.getIsbn(),library.getAisle());
        AddBookResponse addBookResponse = new AddBookResponse();
        HttpHeaders httpHeaders = new HttpHeaders();
        if(!libraryService.checkIfBookAlreadyExists(bookId)){

            httpHeaders.add("unique", bookId);
            addBookResponse.setMsg("The book is successfully added to library collection");
            addBookResponse.setId(bookId);
            return new ResponseEntity<>(addBookResponse, httpHeaders, HttpStatus.CREATED);
        } else {
            httpHeaders.add("unique", bookId);
            addBookResponse.setMsg("The book has already added to library collection");
            addBookResponse.setId(bookId);
            return new ResponseEntity<>(addBookResponse, httpHeaders, HttpStatus.ACCEPTED);
        }



    }
}
