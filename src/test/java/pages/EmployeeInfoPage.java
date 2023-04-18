package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.util.List;

public class EmployeeInfoPage {

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> userMenu;
    @FindBy(className = "oxd-radio-input")
    public List<WebElement> btnRadio;
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdown;
    @FindBy(className = "orangehrm-tabs-item")
    public List<WebElement> contactDetails;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtInput;
    @FindBy(className = "oxd-select-text-input")
    public WebElement dropdownCountry;
    @FindBy(css = "[type=submit]")
    public List<WebElement> submit;


    public EmployeeInfoPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }



    public void selectCountry() throws InterruptedException {
        dropdown.get(0).click();
        dropdown.get(0).sendKeys("b");
        dropdown.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropdown.get(0).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropdown.get(0).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        submit.get(1).click();
    }

    public void selectContract() throws InterruptedException {
        contactDetails.get(1).click();
        Thread.sleep(1000);
        Faker faker = new Faker();
        String streetAddress = faker.address().streetAddress();
        txtInput.get(1).sendKeys(streetAddress);
        txtInput.get(2).sendKeys(streetAddress);
        String city = faker.address().city();
        txtInput.get(3).sendKeys(city);
        String state = faker.address().state();
        txtInput.get(4).sendKeys(state);
        String zipCode = faker.address().zipCode();
        txtInput.get(5).sendKeys(zipCode);
        Thread.sleep(1000);
        dropdownCountry.click();
        dropdownCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropdownCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropdownCountry.sendKeys(Keys.ENTER);
        txtInput.get(7).sendKeys("01772921632");
        String email = "user"+ Utils.generateRandomNumber(100, 999)+"@gmail.com";
        txtInput.get(9).sendKeys(email);
        Thread.sleep(1000);
        submit.get(0).click();

    }


}
