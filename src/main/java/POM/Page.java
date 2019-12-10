package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class Page {

    @FindBy(id = "log_out")
    List<WebElement> logoutButtons;
    WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 20);
        PageFactory.initElements(factory, this);
    }

    void fillInputField(WebElement inputField, String inputText) {
        inputField.sendKeys(inputText);
    }

    void clickOnButton(WebElement button) {
        button.click();
    }
}
