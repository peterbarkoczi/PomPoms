package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GlassDocumentationPage extends Page {
    private List<WebElement> metaLabels;
    private WebElement issueTypesMetaLabel;
    private WebElement issueTypesMetaValue;
    private List<WebElement> issueTypes;

    public GlassDocumentationPage(WebDriver driver) {
        super(driver);
        this.metaLabels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.className("glass-meta-label"))
        );
        issueTypesMetaLabel = getLabelFromLabels(metaLabels, "Issue Types");
        issueTypesMetaValue = getValueOfLabel(issueTypesMetaLabel);
        issueTypes = issueTypesMetaValue.findElements(By.cssSelector("span"));
    }

    private WebElement getLabelFromLabels(List<WebElement> labels, String expectedLabel) {
        for (WebElement label : labels) {
            if (label.getText().equals(expectedLabel)) {
                return label;
            }
        } throw new ElementNotSelectableException("No element found with the given label.");
    }

    private WebElement getValueOfLabel(WebElement label) {
        return label.findElement(By.xpath("./..")).findElement(By.className("glass-meta-value"));
    }

    public List<String> getIssueTypes() {
        List<String> issueTypeTitles = new ArrayList<>();
        for (WebElement issueType : issueTypes) {
            issueTypeTitles.add(issueType.getAttribute("title"));
        }
        issueTypeTitles.sort(String::compareToIgnoreCase);
        return issueTypeTitles;
    }

}
