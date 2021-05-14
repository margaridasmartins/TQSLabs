package tqs.assignment.functional;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HistoryAirQuality{

    private WebDriver webDriver;

    private String init_header;

    @Given("I see {string} - {string}  current data")
    public void i_see_current_data(String string, String string2) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("http://127.0.0.1:4200");

        webDriver.findElement(By.name("distrito")).click();
        webDriver.findElement(By.cssSelector("option[ng-reflect-ng-value="+string+"]")).click();
        webDriver.findElement(By.name("concelho")).click();
        webDriver.findElement(By.cssSelector("option[ng-reflect-ng-value="+string2+"]")).click();

        webDriver.findElement(By.name("Pesquisar")).click();
        init_header = webDriver.findElement( By.id("daily_data")).getText();
    }
    
    @When("I click {string}  page button")
    public void i_click_page_button(String string) {
        webDriver.findElement(By.id(string)).click();
    }

    @Then("I should be shown different {string} information")
    public void iShouldBeShownDifferentInfo(String daily) {
        try {
            String header_daily = webDriver.findElement( By.id("daily_data")).getText();
            System.out.println(header_daily);
            assertThat(header_daily,containsString(daily));
            assertThat(header_daily, not(init_header));
                                                                                                                                                                                                                                                                        
        } catch (NoSuchElementException e) {
            throw new AssertionError("No information Shown");
        } finally {
            webDriver.quit();
        }
    }

}
