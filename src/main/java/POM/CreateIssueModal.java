package POM;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CreateIssueModal extends Modal {

    @FindBy(id = "project-field")
    private WebElement projectInput;

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

    public void selectProject(String project) {
        selectInput(projectInput, project);
    }

    public void clickOnIssueTypesDropdown() {
        this.issueTypeInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        clickOn(issueTypeInput);
    }

    public List<String> getAvailableIssueTypes() {
        clearInputField(issueTypeInput);
        clickOn(issueTypeInput);
        WebElement dropdown = wait.until(visibilityOfElementLocated(
                id("issuetype-suggestions")
        ));
        List<WebElement> dropdownElements = dropdown.findElements(cssSelector("a"));
        return Lists.transform(dropdownElements, WebElement::getText);
    }
}
