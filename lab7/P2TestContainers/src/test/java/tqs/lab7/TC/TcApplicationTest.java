package tqs.lab7.TC;

import tqs.lab7.TC.entity.Book;
import tqs.lab7.TC.repository.BookRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TcApplicationTest {

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

	@Order(1)
	@Test
	public void savingBooks() {

		Book book = new Book("The Return of the King","Tolkien",LocalDate.now());
		bookRepository.save(book);
		Book book1 = new Book("The fellowship of the ring","Tolkien",LocalDate.now());
		bookRepository.save(book1);
		Book book2 = new Book("A Game of Thrones","George RR Martin",LocalDate.now());
		bookRepository.save(book2);

		System.out.println("3 books saved");

	}

	@Order(2)
	@Test
	public void getAllBooks(){
		System.out.println("Getting all books");
		bookRepository.findAll().stream().forEach(System.out::println);
	}

	@Order(3)
	@Test
	public void getBooksByAuthor(){
		System.out.println("Getting books by author Tolkien");
		bookRepository.findByAuthor("Tolkien").stream().forEach(System.out::println);
	}


}
