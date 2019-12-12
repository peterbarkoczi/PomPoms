package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditIssueModal extends Modal {

    @FindBy(id = "summary")
    private WebElement summaryInput;

    @FindBy(id = "edit-issue-submit")
    private WebElement updateButton;

    public EditIssueModal(WebDriver driver) {
        super(driver);
    }

    public void fillAndUpdateSummary(String inputText) {
        fillSummaryInputField(inputText);
        clickOnUpdateButton();
    }

    public void fillAndCancelUpdating(String inputText) {
        fillSummaryInputField(inputText);
        clickOnCancelButton();
    }

    private void fillSummaryInputField(String inputText) {
        wait.until(ExpectedConditions.visibilityOf(summaryInput));
        fillInputField(summaryInput, inputText);
    }

    private void clickOnUpdateButton() {
        clickOnButton(updateButton);
    }

    private void clickOnCancelButton() {
        clickOnButton(cancelButton);
    }
}
