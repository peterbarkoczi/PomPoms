import Utils.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    WebDriver driver;

    @BeforeEach
    void setDriver() {
        System.setProperty("webdriver.chrome.driver", Util.getDriverPath("chromedriver"));
        this.driver = new ChromeDriver();
    }

    @AfterEach
    void quitDriver() {
        driver.quit();
    }

    @Test
    void test() {
        driver.get("https://www.google.com");
    }

}
