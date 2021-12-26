package io.yadnyesh.springbootunittestrahulshetty;

import io.yadnyesh.springbootunittestrahulshetty.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootunittestrahulshettyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void validateBuildIdLogic() {

		LibraryService libraryService = new LibraryService();
		String bookId = libraryService.buildId("ZMAN",22);
		Assertions.assertEquals(bookId,"OldZMAN22");
		Assertions.assertEquals("yadnyesh","yadnyesh");

	}

}
