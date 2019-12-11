package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuePage extends Page {

    @FindBy(id = "summary-val")
    WebElement summary;

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    public String getSummary() {
        return summary.getText();
    }
}
