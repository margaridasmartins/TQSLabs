package lab3.tests;

import lab3.pages.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ToptalTest {
   WebDriver driver;

   @Before
   public void setup(){
       //use FF Driver
       driver = new FirefoxDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   }

   @Test
   public void applyAsDeveloper() {
       //Create object of HomePage Class
       HomePage home = new HomePage(driver);
       home.clickOnDeveloperApplyButton();

       //Create object of DeveloperApplyPage
       DeveloperApplyPage applyPage =new DeveloperApplyPage(driver);

       //Check if page is opened
       Assert.assertTrue(applyPage.isPageOpened());

       //Fill up data
       applyPage.setTalent_type();
       applyPage.setDeveloper_email("dejan@toptal.com");
       applyPage.setDeveloper_password("password123");
       applyPage.setDeveloper_password_confirmation("password123");


       //Click on join
       //applyPage.clickOnJoin(); 
   }

    @After
    public void close(){
          driver.close();
       }
   }