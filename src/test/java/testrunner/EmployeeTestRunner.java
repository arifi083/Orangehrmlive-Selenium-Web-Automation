package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeInfoPage;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class EmployeeTestRunner extends Setup {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    PIMPage pimPage;
    EmployeeInfoPage employeeInfoPage;

    @Test(priority = 1, description = "Login With Second User")
    public void doLoginWithSecondUsers() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        JSONObject userObject = Utils.loadJSONFileContainingArray("./src/test/resources/Employee.json", 10);
        String userName = userObject.get("userName").toString();
        String password = userObject.get("password").toString();
        loginPage.doLogin(userName,password);
        Thread.sleep(1500);

        //Assertion
        WebElement headerText = driver.findElement(By.tagName("h6"));
        String actual_headerText = headerText.getText();
        String expected_headerText = "Dashboard";
        Assert.assertEquals(actual_headerText,expected_headerText);

    }
    @Test(priority = 2, description = "Insert second user's Gender, Blood Type, Address and Email ")
    public void updateUserInformation() throws InterruptedException {
        employeeInfoPage = new EmployeeInfoPage(driver);
        employeeInfoPage.userMenu.get(2).click();


        Utils.doScroll(driver,500);
        employeeInfoPage.selectCountry();
        Thread.sleep(1000);
        driver.navigate().refresh();
        employeeInfoPage.selectContract();
        Thread.sleep(1000);

        //assertion
        WebElement headerText = driver.findElement(By.tagName("h6"));
        String actual_headerText = headerText.getText();
        String expected_headerText = "PIM";
        Assert.assertEquals(actual_headerText,expected_headerText);
        Thread.sleep(1000);
    }

    @Test(priority = 3,description = "Second User Logout Successfully")
    public void logOut() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();

    }

}
