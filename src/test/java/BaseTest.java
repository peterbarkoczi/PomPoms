import POM.BrowseProjectsPage;
import POM.DashboardPage;
import POM.LoginPage;
import Utils.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseTest {

    WebDriver driver;

    // POM PAGES
    LoginPage loginPage;
    DashboardPage dashboardPage;
    BrowseProjectsPage browseProjectsPage;

    // CREDENTIALS
    String baseUrl;
    String username;
    String password;

    @BeforeAll
    void setupTestEnvironment() {
        this.baseUrl = "https://jira.codecool.codecanvas.hu/";
        this.username = System.getenv("JIRA_USERNAME");
        this.password = System.getenv("JIRA_PASSWORD");
    }

    @BeforeEach
    void setDriver() {
        // TODO: Drivers util class, getChromeDriver method + other browsers
        System.setProperty("webdriver.chrome.driver", Util.getDriverPath("chromedriver"));
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        browseProjectsPage = new BrowseProjectsPage(driver);
        navigateTo(baseUrl + loginPage.getUrl());
    }

    @AfterEach
    void quitDriver() {
        driver.quit();
    }

    void navigateTo(String url) {
        driver.get(url);
    }
}
