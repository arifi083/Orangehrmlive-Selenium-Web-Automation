package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class PIMPage {
    WebDriver driver;
    @FindBy(className = "oxd-button--secondary")
    public List<WebElement>button;
    @FindBy(css = "[name=firstName]")
    public WebElement txtFirstName;

    @FindBy(css = "[name=lastName]")
    public WebElement txtLastName;

    @FindBy(className = "oxd-input")
    public List<WebElement> txtEmployeeId;

    @FindBy(className = "oxd-switch-input")
    public WebElement btnToggle;

    @FindBy(tagName = "input")
    public List<WebElement>txtUserName;

    @FindBy(tagName = "input")
    public List<WebElement>txtPassword;

    @FindBy(tagName = "input")
    public List<WebElement> txtConfirmPassword;

    @FindBy(css = "[type=submit]")
    public WebElement submit;

    @FindBy(tagName = "input")
    public List<WebElement> txtSearchEmpName;
    @FindBy(tagName = "button")
    public List<WebElement> btnUpdateEmployee;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtUpdateEmployeeId;


    public PIMPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }


    public void createEmployee(String firstName,String lastName,String employeeId,String userName,String password) throws InterruptedException {
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        WebElement empID = txtEmployeeId.get(4);
        Thread.sleep(1000);
        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(employeeId);
        Thread.sleep(1000);
        btnToggle.click();
        txtUserName.get(7).sendKeys(userName);
        txtUserName.get(10).sendKeys(password);
        txtUserName.get(11).sendKeys(password);
        Thread.sleep(1500);
        submit.click();
        Thread.sleep(3000);
    }

    public void createEmployeeWithoutUsername(String firstName,String lastName,String employeeId,String password) throws InterruptedException {
      txtFirstName.sendKeys(firstName);
      txtLastName.sendKeys(lastName);
      WebElement empID = txtEmployeeId.get(4);
      Thread.sleep(1000);
      empID.clear();
      empID.sendKeys(Keys.CONTROL + "a");
      empID.sendKeys(employeeId);
      Thread.sleep(1000);
      btnToggle.click();
      txtUserName.get(10).sendKeys(password);
      txtUserName.get(11).sendKeys(password);
      //Utils.doScroll(driver,200);
      Thread.sleep(1500);
      submit.click();

    }

    public void SearchEmployeeByInvalidName(String EmpName) throws InterruptedException {
        txtSearchEmpName.get(1).sendKeys(EmpName);
        Thread.sleep(1500);
        submit.click();
    }

    public void SearchEmployeeByValidName(String EmpName) throws InterruptedException {
        txtSearchEmpName.get(1).sendKeys(EmpName);
        Thread.sleep(1500);
        submit.click();
    }

    public void updateEmployeeById(String employeeId) throws InterruptedException {
        btnUpdateEmployee.get(6).click();
        Thread.sleep(2000);
        //btnUpdateEmployee.get(6).clear();
        //txtUpdateEmployeeId.get(5).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUpdateEmployeeId.get(5).sendKeys(Keys.chord(Keys.chord(Keys.CONTROL,"a")));
        txtUpdateEmployeeId.get(5).sendKeys(Keys.DELETE);
        Thread.sleep(1500);
        txtUpdateEmployeeId.get(5).sendKeys(employeeId);
        Thread.sleep(1500);
        btnUpdateEmployee.get(1).click();

    }

    public void SearchEmployeeByValidId(String randomEmployeeId) throws InterruptedException {
        txtEmployeeId.get(1).sendKeys(randomEmployeeId);
        Thread.sleep(1500);
        submit.click();
    }












}
