package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateIssueModal extends Modal {

    @FindBy(id = "project-field")
    private WebElement projectInput;

    @FindBy(id = "issuetype-field")
    private WebElement issueTypeInput;

    @FindBy(id = "summary")
    private WebElement summaryInput;

    public CreateIssueModal(WebDriver driver) {
        super(driver);
    }

    /**
     * Creates a new issue if the 'Create' button is already clicked,
     * and the 'Create Issue' modal is opened.
     *
     * @param project Name of the Project as displayed in the dropdown.
     *                Apply empty string ("") if default value shall be used.
     * @param issueType Name of the Issue Type as displayed in the dropdown.
     *                  Apply empty string ("") if default value shall be used.
     * @param summary Text to be typed into Summary input field.
     */
    public void fillAndSaveIssue(String project, String issueType, String summary) {
        if (!project.equals("")) selectProject(project);
        if (!issueType.equals("")) selectIssueType(issueType);
        fillSummaryInput(summary);
        clickOnCreateButton();
    }

    private void selectInput(WebElement inputField, String input) {
        inputField.click();
        inputField.sendKeys(input);
        inputField.sendKeys(Keys.ENTER);
    }

    private void selectProject(String project) {
        selectInput(projectInput, project);
    }

    private void selectIssueType(String issueType) {
        selectInput(issueTypeInput, issueType);
    }

    private void fillSummaryInput(String summary) {
        // TODO: ask on review
        this.summaryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
        fillInputField(summaryInput, summary);
    }

    private void clickOnCreateButton() {
        clickOnButton(submitButton);
    }

    private void clickOnCancelButton() {
        clickOnButton(cancelButton);
    }
}
