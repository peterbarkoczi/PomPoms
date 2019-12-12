package POM;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class IssuePage extends Page {

    @FindBy(id = "summary-val")
    WebElement summary;

    @FindBy(id = "opsbar-operations_more")
    WebElement moreButton;

    @FindBy(css = "a[href^='/secure/DeleteIssue']")
    WebElement deleteButton;

    @FindBy(css = "a[href^='/secure/CreateSubTaskIssue']")
    WebElement createSubTaskButton;

    @FindBy(id = "view-subtasks")
    WebElement subTaskList;

    @FindBy(id = "edit-issue")
    WebElement editButton;

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    public String getSummary() {
        return summary.getText();
    }

    public void deleteIssue() {
        clickOn(moreButton);
        clickOn(deleteButton);
        wait.until(visibilityOfElementLocated(By.id("delete-issue-submit"))).click();
    }

    public void createSubTask(String summary) {
        clickOn(moreButton);
        clickOn(createSubTaskButton);
        CreateSubTaskModal subTaskModal = new CreateSubTaskModal(driver);
        subTaskModal.fillSummaryInput(summary);
        subTaskModal.submit();
    }

    private List<String> getSubTaskSummaries() {
        List<WebElement> subTasks = subTaskList.findElements(By.cssSelector(".issuerow .stsummary"));
        return Lists.transform(subTasks, WebElement::getText);
    }

    public boolean doesSubTaskListContainSubTaskWithSummary(String summary) {
        return getSubTaskSummaries().contains(summary);
    }

    public void clickOnEditButton() {
        clickOn(editButton);
    }

    public void waitForSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("aui-message-success")));
    }
}
