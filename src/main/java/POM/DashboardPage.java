package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DashboardPage extends Page {

    @FindBy(id = "header-details-user-fullname")
    private WebElement avatar;

    @FindBy(id = "browse_link")
    private WebElement projectsButton;

    @FindBy(id = "project_view_all_link_lnk")
    private WebElement viewAllProjectsButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.url = "secure/Dashboard.jspa";
    }

    public String getUsernameFromAvatar() {
        return avatar.getAttribute("data-username").toLowerCase();
    }

    public boolean isLogoutButtonPresent() {
        return logoutButtons.size() > 0;
    }

    public void clickOnAvatar() {
        clickOn(avatar);
    }

    public void navigateToAllProjectsPage() {
        clickOnButton(projectsButton);
        clickOnButton(viewAllProjectsButton);
    }

    public WebElement getAvatar() {
        return avatar;
    }
}
