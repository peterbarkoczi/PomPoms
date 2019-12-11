package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Modal extends Page {
    private boolean isSubmitted;

    @FindBy(className = "jira-dialog")
    WebElement dialogPanel;

    @FindBy(css = ".jira-dialog input[type='submit']")
    WebElement submitButton;

    @FindBy(css = ".jira-dialog a.cancel")
    WebElement cancelButton;

    public Modal(WebDriver driver) {
        super(driver);
        this.isSubmitted = false;
    }

    public void submit() {
        clickOn(submitButton);
        this.isSubmitted = true;
    }

    public void cancel() {
        clickOn(cancelButton);
    }

    public boolean isSubmitted() {
        return this.isSubmitted;
    }
}
