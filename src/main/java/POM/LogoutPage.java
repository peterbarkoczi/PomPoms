package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends Page {

    @FindBy(className = "login-link")
    private WebElement loginLink;

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginButtonPresent() {
        return loginLink.isDisplayed();
    }

}
