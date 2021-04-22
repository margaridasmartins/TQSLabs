package tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
 
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
 
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDate iso8601Date(String year, String month, String day){
		return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
	}
 
	@Given("a book with the title {string}, written by {string}, published in {string}")
	public void addNewBook(final String title, final String author,  final String date) {
        String[] published = date.split("-");
		Book book = new Book(title, author, LocalDate.of(Integer.parseInt(published[0]), Integer.parseInt(published[1]), Integer.parseInt(published[2])));
		library.addBook(book);
	}
 
	@When("the customer searches for books published between {string} and {string}")
	public void setSearchParametersDate( final String f,  final String t) {
		String[] from_st = f.split("-");
		String[] to_st = t.split("-");
		LocalDate from = LocalDate.of(Integer.parseInt(from_st[0]), Integer.parseInt(from_st[1]), Integer.parseInt(from_st[2]));
		LocalDate to = LocalDate.of(Integer.parseInt(to_st[0]), Integer.parseInt(to_st[1]), Integer.parseInt(to_st[2]));
		result = library.findBookbyDate(from, to);
	}

    @When("the customer searches for books written by {string}")
	public void setSearchParametersAuthor( final String author) {
		result = library.findBookbyAuthor(author);
	}
    
    @When("the customer searches for books with title {string}")
	public void setSearchParametersTitle( final String title) {
		result = library.findBookbyTitle(title);
	}
 
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertEquals(result.size(), booksFound);
	}
 
	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertEquals(result.get(position - 1).getTitle(), title);
	}
}