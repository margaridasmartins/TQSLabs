/*
 * (C) Copyright 2020 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia;

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

public class SearchSteps {

    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @And("I choose {string} followed by {string}")
    public void iChoose(String from, String to) {
        webDriver.findElement(By.name("fromPort")).click();
        webDriver.findElement(By.cssSelector("option[value="+from+"]")).click();
        webDriver.findElement(By.name("toPort")).click();
        webDriver.findElement(By.cssSelector("option[value="+to+"]")).click();
    }

    @And("I click {string} button")
    public void iClick(String btn) {
        webDriver.findElement(By.xpath("/html/body/div[3]/form/div/input")).click();
    }

    @Then("I should be shown {string} page")
    public void iShouldBeShownPage(String title) {
        try {
            String header = webDriver.findElement( By.cssSelector("h3")).getText();
            assertThat(header,containsString(title));

        } catch (NoSuchElementException e) {
            throw new AssertionError("Wrong page redirected");
        } finally {
            webDriver.quit();
        }
    }

}
