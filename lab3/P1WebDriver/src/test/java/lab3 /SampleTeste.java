package lab3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class SampleTest {

    WebDriver browser;

    @BeforeEach
    public void SetUp(){

        //System.setProperty("webdriver.chrome.driver", "/where/you/put/chromedriver");  
        browser = new FirefoxDriver();
    }

    @AfterEach
    public void tearDown(){
        browser.close(); 
    }

    @Test
    public void site_header_is_on_home_page() {

        browser.get("https://www.saucelabs.com"); 
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));        
        assertTrue((href.isDisplayed()));  

    }
}