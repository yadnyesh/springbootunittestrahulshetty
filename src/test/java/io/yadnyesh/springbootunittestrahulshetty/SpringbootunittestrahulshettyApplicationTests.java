package io.yadnyesh.springbootunittestrahulshetty;

import io.yadnyesh.springbootunittestrahulshetty.controller.LibraryController;
import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import io.yadnyesh.springbootunittestrahulshetty.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@Slf4j
class SpringbootunittestrahulshettyApplicationTests {

	@Autowired
	LibraryController libraryController;

	@Test
	void contextLoads() {
	}

	public Library buildLibrary() {
		Library library = new Library();
		library.setAisle(322);
		library.setBook_name("Spring");
		library.setIsbn("sfe");
		library.setId("sfe322");
		library.setAuthor("yadnyesh");
		return library;
	}

	@Test
	public void validateBuildIdLogic() {
		LibraryService libraryService = new LibraryService();
		String bookId = libraryService.buildId("ZMAN",22);
		Assertions.assertEquals(bookId,"OldZMAN22");
		Assertions.assertEquals("yadnyesh","yadnyesh");
	}

	@Test
	public void addBookTest() {
		Library library = buildLibrary();
		ResponseEntity response = libraryController.addBookToLibrary(library);
		log.info(response.getStatusCode().toString());
		log.info(response.getHeaders().toString());
	}

}
