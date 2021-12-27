package io.yadnyesh.springbootunittestrahulshetty.controller;

import io.yadnyesh.springbootunittestrahulshetty.AddBookResponse;
import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import io.yadnyesh.springbootunittestrahulshetty.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
            log.info("Creating the book by saving input details");
            httpHeaders.add("unique", bookId);
            addBookResponse.setMsg("The book is successfully added to library collection");
            addBookResponse.setId(bookId);
            library.setId(bookId);
            libraryRepository.save(library);
            return new ResponseEntity<>(addBookResponse, httpHeaders, HttpStatus.CREATED);
        } else {
            httpHeaders.add("unique", bookId);
            addBookResponse.setMsg("The book has already added to library collection");
            addBookResponse.setId(bookId);
            return new ResponseEntity<>(addBookResponse, httpHeaders, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/books/{bookId}")
    public Library getBookById(@PathVariable(value = "bookId") String bookId) {
        try {
            log.info("Finding details of book {}", bookId);
            return libraryRepository.findById(bookId).get();
        } catch (Exception exception) {
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/books")
    public List<Library> getBooksByAuthor(@RequestParam(value = "authorname") String authorName) {
        log.info("Fetching details of all books by author: {} ", authorName);
        try {
            return libraryRepository.findAllBooksByAuthor(authorName);
        } catch (Exception exception) {
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Library> updateBookById(@PathVariable(value = "bookId") String bookId,@RequestBody Library library) {
        Library existingBook = libraryService.getBookByBookId(bookId);
        HttpHeaders httpHeaders = new HttpHeaders();
        existingBook.setAisle(library.getAisle());
        existingBook.setAuthor(library.getAuthor());
        existingBook.setBook_name(library.getBook_name());
        libraryRepository.save(existingBook);
        httpHeaders.add("updateApi","successfully updated the object.");
        log.info("Updated details of book {}", bookId);
        return new ResponseEntity<>(existingBook, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/books")
    public ResponseEntity<String> deleteBookById(@RequestBody Library library){
        Library existingBook = libraryRepository.findById(library.getId()).get();
        libraryRepository.deleteById(existingBook.getId());
        log.info("Delete the book with id: {}", library.getId());
        return new ResponseEntity<>("Successfully delete the book having id: " + existingBook.getId(), HttpStatus.OK);
    }

//    @GetMapping("/books/{bookId}")
//    public ResponseEntity<AddBookResponse> getBookById(@PathVariable(value = "bookId") String bookId) {
//        Library library = libraryRepository.findById(bookId).orElse(null);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        AddBookResponse addBookResponse = new AddBookResponse();
//        httpHeaders.add("method", "Get By Id");
//        addBookResponse.setId(library.getId());
//        addBookResponse.setMsg(library.getBook_name());
//        return new ResponseEntity<>(addBookResponse, httpHeaders, HttpStatus.OK);
//        //return new ResponseEntity<>(library, httpHeaders, HttpStatus.OK);
//    }
}
