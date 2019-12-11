package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Modal extends Page {

    @FindBy(className = "jira-dialog")
    WebElement dialogPanel;

    @FindBy(css = ".jira-dialog input[type='submit']")
    WebElement submitButton;

    @FindBy(css = ".jira-dialog a.cancel")
    WebElement cancelButton;

    public Modal(WebDriver driver) {
        super(driver);
    }
}
