package io.yadnyesh.springbootunittestrahulshetty;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.yadnyesh.springbootunittestrahulshetty.controller.LibraryController;
import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import io.yadnyesh.springbootunittestrahulshetty.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class SpringbootunittestrahulshettyApplicationTests {

	@Autowired
	LibraryController libraryController;
	@MockBean
	LibraryRepository libraryRepository;
	@MockBean
	LibraryService libraryService;
	@Autowired
	private MockMvc mockMvc;

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

	public Library updateLibrary() {
		Library library = new Library();
		library.setAisle(322);
		library.setBook_name("SpringBoot");
		library.setIsbn("sfe");
		library.setId("sfe322");
		library.setAuthor("yadnyeshbharatjuvekar");
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
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
		Assertions.assertEquals(library.getId(), addBookResponse.getId());
	}

	@Test
	public void addBookControllerTest() throws Exception {
		Library library = buildLibrary();

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(library);

		when(libraryService.buildId(library.getIsbn(), library.getAisle())).thenReturn(library.getId());
		when(libraryService.checkIfBookAlreadyExists(library.getId())).thenReturn(false);
		when(libraryRepository.save(any())).thenReturn(library);
		ResponseEntity response = libraryController.addBookToLibrary(library);
		this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString)).andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("sfe322"));

	}

	@Test
	public void getBooksByAuthor() throws Exception {
		List<Library> libraryList = new ArrayList<>();
		libraryList.add(buildLibrary());
		libraryList.add(buildLibrary());
		when(libraryRepository.findAllBooksByAuthor(any())).thenReturn(libraryList);
		this.mockMvc.perform(get("/books").param("authorname", "yadnyesh"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()",is(2)))
				.andExpect(jsonPath("$.[0].id").value("sfe322"));

	}

	@Test
	public void updateBookByIdTest() throws Exception{
		Library library = buildLibrary();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(updateLibrary());
		when(libraryService.getBookByBookId(any())).thenReturn(buildLibrary());
		this.mockMvc.perform(put("/books/"+library.getId())
								.contentType(MediaType.APPLICATION_JSON)
						        .content(jsonString))
					.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{\"id\":\"sfe322\",\"book_name\":\"SpringBoot\",\"isbn\":\"sfe\",\"aisle\":322,\"author\":\"yadnyeshbharatjuvekar\"}"));
	}

	@Test
	public void deleteBookById() throws Exception{
		when(libraryService.getBookByBookId(any())).thenReturn(buildLibrary());
		doNothing().when(libraryRepository).deleteById(buildLibrary().getId());
		this.mockMvc.perform(delete("/books").contentType(MediaType.APPLICATION_JSON).content("{\n" +
				"  \"id\": \"sfe322\"\n" +
				"}")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("Successfully delete the book having id: sfe322"));

	}

}
