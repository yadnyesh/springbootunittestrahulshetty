package io.yadnyesh.springbootunittestrahulshetty;

import io.yadnyesh.springbootunittestrahulshetty.model.Library;
import io.yadnyesh.springbootunittestrahulshetty.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class SpringbootunittestrahulshettyApplication {

	@Autowired
	LibraryRepository libraryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootunittestrahulshettyApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Optional<Library> library = libraryRepository.findById("fdsefr343");
//		System.out.println(library.get().getAuthor());
//		//libraryRepository.save(new Library("fedup311", "my Book", "abcr455",44 , "Author 1" ));
//	}
}
