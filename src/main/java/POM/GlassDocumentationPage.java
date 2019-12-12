package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GlassDocumentationPage extends Page {

    List<WebElement> versionsName = new ArrayList<>();

    @FindBy(className = "aui-sidebar-settings-button")
    private WebElement projectSettingsButton;

    @FindBy(css = "#aui-uid-2")
    private WebElement versionsTab;

    public GlassDocumentationPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnVersionsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(versionsTab));
        clickOn(versionsTab);
    }

    public boolean validateCreatedVersion(String versionName) {
        versionsName = driver.findElements(By.cssSelector("#versions-table td.versions-table__name"));
        return versionsName.get(versionsName.size() - 1).getText().equals(versionName);
    }

}
