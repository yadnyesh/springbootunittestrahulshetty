package io.yadnyesh.springbootunittestrahulshetty;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

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
}
