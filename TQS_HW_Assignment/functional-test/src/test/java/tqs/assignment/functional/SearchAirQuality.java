
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchAirQuality {

    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @And("I choose {string} followed by {string}")
    public void iChoose(String distrito, String concelho) {
        webDriver.findElement(By.name("distrito")).click();
        webDriver.findElement(By.cssSelector("option[ng-reflect-ng-value="+distrito+"]")).click();
        webDriver.findElement(By.name("concelho")).click();
        webDriver.findElement(By.cssSelector("option[ng-reflect-ng-value="+concelho+"]")).click();
    }

    @And("I click {string} button")
    public void iClick(String btn) {
        webDriver.findElement(By.name("Pesquisar")).click();
    }

    @Then("I should be shown {string} and {string} information")
    public void iShouldBeShownInfo(String current, String daily) {
        try {
            String header_current= webDriver.findElement( By.id("current_data")).getText();
            String header_daily = webDriver.findElement( By.id("daily_data")).getText();
            assertThat(header_current,containsString(current));
            assertThat(header_daily,containsString(daily));
                                                                                                                                                                                                                                                                        
        } catch (NoSuchElementException e) {
            throw new AssertionError("No information Shown");
        } finally {
            webDriver.quit();
        }
    }

}
