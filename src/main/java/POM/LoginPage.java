package POM;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(id = "login-form-username")
    private WebElement usernameInput;

    @FindBy(id = "login-form-password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(className = "login-link")
    private WebElement loginLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        url = "secure/Dashboard.jspa";
    }

    public void login(String username, String password) {
        fillCredentials(username, password);
        clickOnLoginButton();
        DashboardPage dashboard = new DashboardPage(driver);
        try {
            dashboard.isTestUserLoggedIn(username);
        } catch (TimeoutException te) {
            te.printStackTrace();
        }
    }

    private void clickOnLoginButton() {
        clickOn(loginButton);
    }

    private void fillCredentials(String username, String password) {
        fillInputField(usernameInput, username);
        passwordInput.click();
        fillInputField(passwordInput, password);
    }
}
