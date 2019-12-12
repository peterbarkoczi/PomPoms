package POM;

import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProjectSettingsPage extends Page {

    @FindBy(css = "#project-config-webpanel-summary-issuetypes ul")
    private WebElement issueTypesPanel;

    private List<WebElement> issueTypes;

    public ProjectSettingsPage(WebDriver driver) {
        super(driver);
        issueTypes = issueTypesPanel.findElements(By.cssSelector("a"));
    }

    public List<String> getIssueTypes() {
        List<String> issueTypeTitles = new ArrayList<>(Lists.transform(issueTypes, WebElement::getText));
        issueTypeTitles.sort(String::compareToIgnoreCase);
        return issueTypeTitles;
    }
}
