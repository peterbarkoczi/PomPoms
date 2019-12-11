package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class Modal extends Page {
    private boolean isCanceled;

    @FindBy(css = ".jira-dialog input[type='submit']")
    WebElement submitButton;

    @FindBy(css = ".jira-dialog a.cancel")
    WebElement cancelButton;

    @FindBy(id = "issuetype-field")
    WebElement issueTypeInput;

    @FindBy(id = "summary")
    WebElement summaryInput;

    public Modal(WebDriver driver) {
        super(driver);
        this.isCanceled = false;
    }

    public void submit() {
        clickOn(submitButton);
    }

    public void cancel() {
        clickOn(cancelButton);
        this.isCanceled = true;
    }

    public boolean isCanceled() {
        return this.isCanceled;
    }

    protected void selectInput(WebElement inputField, String input) {
        inputField.click();
        inputField.sendKeys(input);
        inputField.sendKeys(Keys.ENTER);
    }

    protected void selectIssueType(String issueType) {
        this.issueTypeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        selectInput(issueTypeInput, issueType);
    }

    protected void fillSummaryInput(String summary) {
        // TODO: ask on review
        this.summaryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
        fillInputField(summaryInput, summary);
    }

    protected void clickOnCreateButton() {
        clickOn(submitButton);
    }

    private void clickOnCancelButton() {
        clickOn(cancelButton);
    }
}
