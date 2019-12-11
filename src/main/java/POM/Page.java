package POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Page {

    WebDriver driver;
    String url;

    @FindBy(id = "log_out")
    protected List<WebElement> logoutButtons;

    @FindBy(css = "body")
    private WebElement body;

    public Page(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 20);
        PageFactory.initElements(factory, this);
    }

    public String getUrl() {
        return this.url;
    }

    void fillInputField(WebElement inputField, String inputText) {
        inputField.sendKeys(inputText);
    }

    void clickOnButton(WebElement button) {
        button.click();
    }

    public void clickOnLogoutButton() {
        clickOnButton(logoutButtons.get(0));
    }

    public void openNewTab(String url) {
        body.sendKeys(Keys.CONTROL + "t");
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "')");
    }

    public void changeTab(int numberOfTab) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(numberOfTab - 1));
    }
}
