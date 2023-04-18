package testrunner;


import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class PIMTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    PIMPage pimPage;
    Utils utils;

    @Test(priority = 1,description = "User cannot login with invalid creds")
    public void doLoginWithInvalidCreds() throws InterruptedException {
        loginPage = new LoginPage(driver);
        String actual_msg = loginPage.doLoggingWithInvalidCreds("admin","1234567");
        String expected_msg ="Invalid credentials";
        Assert.assertTrue(actual_msg.contains(expected_msg));
        Thread.sleep(1500);
    }

    @Test(priority = 2, description = "User can do login with valid Creds")
    public void doLogin() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        String username, password;
        JSONObject userObject = Utils.loadJSONFile("./src/test/resources/Admin.json");

        if (System.getProperty("username") != null && System.getProperty("password") != null) {
            username = System.getProperty("username");
            password = System.getProperty("password");
        } else {
            username = (String) userObject.get("username");
            password = (String) userObject.get("password");
        }

        loginPage.doLogin(username,password);
        WebElement headerText = driver.findElement(By.tagName("h6"));
        String actual_headerText = headerText.getText();
        String expected_headerText = "Dashboard";
        Assert.assertEquals(actual_headerText,expected_headerText);
        Thread.sleep(1500);
        dashboardPage.menus.get(1).click();
        Thread.sleep(1500);

    }

    @Test(priority = 3, description = "Doesn't create employee without Username")
    public void createEmployeeWithoutUsername() throws InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.button.get(1).click();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int empId = Utils.generateRandomNumber(10000,99999);
        String employeeId = String.valueOf(empId);
        String password = "Arif123*";
        Thread.sleep(15000);

        pimPage.createEmployeeWithoutUsername(firstName,lastName,employeeId,password);

        String actual_Required_Text = driver.findElements(By.className("oxd-text")).get(15).getText();
        String expected_Required_Text = "Required";
        Assert.assertEquals(actual_Required_Text,expected_Required_Text);
        driver.navigate().refresh();

    }

    @Test(priority = 4, description = "Create first employee")
    public void createEmployee1() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        utils = new Utils();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int empId = Utils.generateRandomNumber(10000,99999);
        String employeeId = String.valueOf(empId);
        String userName = "Arif"+ Utils.generateRandomNumber(1000,9999);
        String password = "Arif7is$";
        Thread.sleep(1500);
        pimPage.createEmployee(firstName,lastName,employeeId,userName,password);
        Thread.sleep(1500);

       String actual_Header_Text = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
       String expect_Header_Text ="Personal Details";
       Assert.assertEquals(actual_Header_Text,expect_Header_Text);

       utils.saveJsonList(firstName,lastName,employeeId,userName,password);
       driver.findElements(By.className("oxd-topbar-body-nav-tab-item")).get(2).click();
       Thread.sleep(3000);

    }
   @Test(priority = 5, description = "Create second employee")
    public void createEmployee2() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        utils = new Utils();

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int empId = Utils.generateRandomNumber(10000,99999);
        String employeeId = String.valueOf(empId);
        String userName = "Arif"+ Utils.generateRandomNumber(1000,9999);
        String password = "Arif7is$";
        Thread.sleep(3000);
        pimPage.createEmployee(firstName,lastName,employeeId,userName,password);
        Thread.sleep(1500);

        String actual_Header_Text = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
        String expect_Header_Text = "Personal Details";



        if(actual_Header_Text.contains(expect_Header_Text)){
            utils.saveJsonList(firstName,lastName,employeeId,userName,password);
        }
        Thread.sleep(1000);

    }

    @Test(priority = 6, description = "Searching With Invalid Employee's Name")
    public void searchEmployeeByInvalidName() throws InterruptedException {
       pimPage = new PIMPage(driver);
       dashboardPage = new DashboardPage(driver);
       dashboardPage.menus.get(1).click();
       Faker faker = new Faker();
       String employeeName = faker.name().firstName();
       pimPage.SearchEmployeeByInvalidName(employeeName);
       Thread.sleep(1000);

       String actual_Msg = driver.findElements(By.className("oxd-text--span")).get(11).getText();
       String expect_Msg = "No Records Found";
       Assert.assertEquals(actual_Msg,expect_Msg);
    }
    @Test(priority = 7, description = "Searching with Valid Employee's name")
    public void searchEmployeeByName() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        JSONObject userObject = Utils.loadJSONFileContainingArray("./src/test/resources/Employee.json", 8);
        String empFirstName = userObject.get("firstName").toString();
        String empLastName = userObject.get("lastName").toString();
        String employeeName = empFirstName+" "+empLastName;
        System.out.println(employeeName);

        pimPage.txtSearchEmpName.get(1).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        pimPage.SearchEmployeeByValidName(employeeName);
        Thread.sleep(3000);

        String message_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String message_expected = "Record Found";
        Assert.assertTrue(message_actual.contains(message_expected));
        Thread.sleep(1000);


    }
    @Test(priority = 8, description = "Update user Id by Random Id")
    public void updateEmployeeById() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        int empId = Utils.generateRandomNumber(10000,99999);
        String randomEmployeeId = String.valueOf(empId);
        Utils.updateJSONObject("./src/test/resources/Employee.json", "employeeId", randomEmployeeId,8 );
        Utils.doScroll(driver,300);
        pimPage.updateEmployeeById(randomEmployeeId);
        Thread.sleep(1500);

        // Assertion
        String header_actual = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
        String header_expected = "Personal Details";
        Assert.assertTrue(header_actual.contains(header_expected));
    }

    @Test(priority = 9, description = "Search by updated Employee Id")
    public void searchEmployeeById() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        dashboardPage.menus.get(1).click();
        JSONObject userObject = Utils.loadJSONFileContainingArray("./src/test/resources/Employee.json", 8);
        String employeeId = userObject.get("employeeId").toString();
        pimPage.SearchEmployeeByValidId(employeeId);
        Thread.sleep(1500);
        Utils.doScroll(driver,500);

        //Assertion
        String message_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String actual_subStr = message_actual.substring(4);
        String message_expected = "Record Found";
        Assert.assertEquals(actual_subStr,message_expected);
        Thread.sleep(1000);

    }

    @Test(priority = 10,description = "Admin Logout Successfully")
    public void logOut() throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.doLogout();

    }









}
