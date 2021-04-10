package lab3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DeveloperApplyPage {
    private WebDriver driver;

    @FindBy(tagName = "h1")
    WebElement heading;

    @FindBy(xpath = "/html/body/div[1]/div/div/header/form/section/div/div[1]/fieldset/div[4]")
    WebElement talent_type;

    @FindBy(id="talent_create_applicant_email")
    WebElement developer_email;

    @FindBy(id = "talent_create_applicant_password")
    WebElement developer_password;

    @FindBy(id = "talent_create_applicant_password_confirmation")
    WebElement developer_password_confirmation;

    @FindBy(id ="save_new_talent_create_applicant")
    WebElement join_toptal_button;


    //Constructor
    public DeveloperApplyPage(WebDriver driver){
       this.driver=driver;

       //Initialise Elements
       PageFactory.initElements(driver, this);
    }

    public void setTalent_type(){
        talent_type.click();
        driver.findElement(By.xpath("//*[@id=\"new_talent_create_applicant\"]/section/div/div[1]/fieldset/div[4]/div[2]/div[1]")).click();
     }

    public void setDeveloper_email(String email){
       developer_email.clear();
       developer_email.sendKeys(email);
    }

    public void setDeveloper_password(String password){
       developer_password.clear();
       developer_password.sendKeys(password);
    }    

    public void  setDeveloper_password_confirmation(String password_confirmation){
       developer_password_confirmation.clear();
       developer_password_confirmation.sendKeys(password_confirmation);
    }



    public void clickOnJoin(){
        join_toptal_button.click();
    }
    public boolean isPageOpened(){
        //Assertion
        System.out.println(heading.getText().toString());
        return heading.getText().toString().contains("Apply to Join\nthe World's Top Talent Network");
    }
}