package io.yadnyesh.springbootunittestrahulshetty.repository;

import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LibraryRepositoryImpl implements LibraryRepositoryCustom{

    LibraryRepository libraryRepository;

    public LibraryRepositoryImpl(@Lazy LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }


    @Override
    public List<Library> findAllBooksByAuthor(String authorName) {
        List<Library> books = libraryRepository.findAll();
        List<Library> booksByAuthor = new ArrayList<>();
        for (Library book: books){
            if(book.getAuthor().equalsIgnoreCase(authorName)) {
                log.info(book.getAuthor());
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }
}
