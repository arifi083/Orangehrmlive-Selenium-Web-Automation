package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(name ="username")
    WebElement txtUserName;

    @FindBy(name="password")
    WebElement txtPassword;

    @FindBy(css="[type=submit]")
    WebElement btnLogin;

    @FindBy(tagName = "p")
    WebElement invalidCreds;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String userName,String password){
        txtUserName.sendKeys(userName);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    public String doLoggingWithInvalidCreds(String userName,String password){
        txtUserName.sendKeys(userName);
        txtPassword.sendKeys(password);
        btnLogin.click();;
        return  invalidCreds.getText();
    }
}
