import POM.DashboardPage;
import POM.LoginPage;
import Utils.Util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseTest {

    WebDriver driver;
    String baseUrl;
    String username;
    String password;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeAll
    void setupTestEnvironment() {
        this.baseUrl = "https://jira.codecool.codecanvas.hu/";
        this.username = System.getenv("JIRA_USERNAME");
        this.password = System.getenv("JIRA_PASSWORD");
    }

    @BeforeEach
    void setDriver() {
        System.setProperty("webdriver.chrome.driver", Util.getDriverPath("chromedriver"));
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void quitDriver() {
        driver.quit();
    }

    void navigateTo(String url) {
        driver.get(url);
    }
}
