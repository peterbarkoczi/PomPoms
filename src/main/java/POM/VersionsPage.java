package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class VersionsPage extends Page {

    List<WebElement> versions = new ArrayList<>();

    @FindBy(css = ".releases-add__name > input")
    private WebElement versionNameInput;

    @FindBy(css = ".releases-add__confirm > button")
    private WebElement addButton;

    public VersionsPage(WebDriver driver) {
        super(driver);
    }

    private void fillVersionNameInputField(String inputText) {
        versionNameInput.click();
        fillInputField(versionNameInput, inputText);
    }

    private void clickOnAddButton() {
        clickOn(addButton);
    }

    public void createNewVersion(String inputText) {
        fillVersionNameInputField(inputText);
        clickOnAddButton();
    }

    public void deleteVersion(String versionName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("versions-table")));
        versions = driver.findElements(By.cssSelector("#versions-table tr.item-state-ready"));
        WebElement latestVersion = versions.get(0);
        if (latestVersion.findElement(By.className("versions-table__name")).getText().equals(versionName)) {
            latestVersion.findElement((By.className("aui-button"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".project-config-operations-delete[tabindex='-1']\n"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click();
        }

    }

}
