package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends Page {

    @FindBy(id = "header-details-user-fullname")
    private WebElement avatar;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getUsernameFromAvatar() {
        return avatar.getAttribute("data-username").toLowerCase();
    }

    public boolean isLogoutButtonPresent() {
        return logoutButtons.size() > 0;
    }

}
