package io.yadnyesh.springbootunittestrahulshetty;

import io.yadnyesh.springbootunittestrahulshetty.controller.LibraryController;
import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import io.yadnyesh.springbootunittestrahulshetty.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class SpringbootunittestrahulshettyApplicationTests {

	@Autowired
	LibraryController libraryController;
	@MockBean
	LibraryRepository libraryRepository;
	@MockBean
	LibraryService libraryService;

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
		when(libraryService.buildId(library.getIsbn(), library.getAisle())).thenReturn(library.getId());
		when(libraryService.checkIfBookAlreadyExists(library.getId())).thenReturn(true);

		ResponseEntity response = libraryController.addBookToLibrary(library);
		log.info(response.getStatusCode().toString());
		log.info(response.getHeaders().toString());
		AddBookResponse addBookResponse = (AddBookResponse) response.getBody();
		log.info(addBookResponse.toString());
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
		Assertions.assertEquals(library.getId(), addBookResponse.getId());
	}

}
