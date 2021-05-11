package tqs.lab7.TC;

import tqs.lab7.TC.entity.Book;
import tqs.lab7.TC.repository.BookRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class TcApplicationTests {

	@Container
  	public static PostgreSQLContainer container = new PostgreSQLContainer().withUsername("admin").withPassword("admin").withDatabaseName("books");

	@Autowired
	private BookRepository bookRepository;


	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	void contextLoads() {

		Book book = new Book("The Return of the King","Tolkien",LocalDate.now());

		bookRepository.save(book);

		System.out.println("Context loads!");
	}

}
