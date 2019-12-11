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
        wait.until(ExpectedConditions.visibilityOf(summaryInput));
        fillSummaryInputField(inputText);
        clickOnUpdateButton();
    }

    private void fillSummaryInputField(String inputText) {
        fillInputField(summaryInput, inputText);
    }

    private void clickOnUpdateButton() {
        clickOnButton(updateButton);
    }

}
