package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    @FindBy(className = "oxd-userdropdown-tab")
    WebElement profileTab;

    @FindBy(partialLinkText = "Logout")
    WebElement logOut;

    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement>menus;

    public DashboardPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }


    public void doLogout() throws InterruptedException {
        profileTab.click();
        logOut.click();
        Thread.sleep(1000);
    }

}
