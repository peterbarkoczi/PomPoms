package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class IssuePage extends Page {

    @FindBy(id = "summary-val")
    WebElement summary;

    @FindBy(id = "opsbar-operations_more")
    WebElement moreButton;

    @FindBy(css = "a[href^='/secure/DeleteIssue']")
    WebElement deleteButton;

    @FindBy(id = "edit-issue")
    WebElement editButton;

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    public String getSummary() {
        return summary.getText();
    }

    public void deleteIssue() {
        clickOnButton(moreButton);
        clickOnButton(deleteButton);
        wait.until(visibilityOfElementLocated(By.id("delete-issue-submit"))).click();
    }

    public void clickOnEditButton() {
        clickOnButton(editButton);
    }

    public void waitForSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("aui-message-success")));
    }
}
