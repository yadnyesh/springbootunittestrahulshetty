package io.yadnyesh.springbootunittestrahulshetty;

import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest
@Slf4j
public class LibraryTestsIT {

    @Test
    public void getAuthorNameBooksIT() throws JSONException {
        String expectedJSON = "[{\"id\":\"dummy343\",\"book_name\":\"Dummy\",\"isbn\":\"dummy3\",\"aisle\":43,\"author\":\"Simmba\"}]";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:8080/books?authorname=simmba", String.class);
        log.info(responseEntity.getStatusCode().toString());
        log.info(responseEntity.getBody());
        JSONAssert.assertEquals(expectedJSON, responseEntity.getBody(),false);
    }

    @Test
    public void addBookToLibraryTest() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<Library>(buildLibrary(), httpHeaders);
        ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:8080/books",request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        log.info(response.getHeaders().get("unique").toString());
    }

    public Library buildLibrary() {
        Library library = new Library();
        library.setAisle(100);
        library.setBook_name("Spring");
        library.setIsbn("book");
        library.setId("book100");
        library.setAuthor("yadnyesh");
        return library;
    }
}
