package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BrowseProjectsPage extends Page {

    @FindBy(id = "project-filter-text")
    private WebElement searchProjectInput;

    public BrowseProjectsPage(WebDriver driver) {
        super(driver);
        this.url = "secure/BrowseProjects.jspa";
    }

    public void searchProject(String projectName) {
        int numberOfResults = wait.until(presenceOfAllElementsLocatedBy(By.cssSelector(".projects-list > tr"))).size();
        fillInputField(searchProjectInput, projectName);
        wait.until(ExpectedConditions.or(
                numberOfElementsToBeMoreThan(By.cssSelector(".projects-list > tr"), numberOfResults),
                numberOfElementsToBeLessThan(By.cssSelector(".projects-list > tr"), numberOfResults)
        ));
    }

    public boolean isProjectFound(String projectKey) {
        List<String> keys = new ArrayList<>();
        for (WebElement project : driver.findElements(By.cssSelector(".projects-list > tr"))) {
            keys.add(project.findElement(By.className("cell-type-key")).getText());
        }
        return keys.contains(projectKey);
    }

}
