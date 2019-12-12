package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectSettingsPage extends Page {

    @FindBy(id = "administer_project_versions")
    private WebElement versionsButton;

    public ProjectSettingsPage(WebDriver driver) {
        super(driver);
    }

}
