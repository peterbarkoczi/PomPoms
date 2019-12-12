import POM.BrowseProjectsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BrowseProjectsTest extends BaseTest {
    BrowseProjectsPage browseProjectsPage;

    BrowseProjectsPage browseProjectsPage;

    @BeforeEach
    void navigateToDashboard() {
        browseProjectsPage = new BrowseProjectsPage(driver);
        navigateTo(baseUrl + dashboardPage.getUrl());
        loginPage.login(username, password);
    }

    @ParameterizedTest
    @CsvSource({
            "Main Testing Project, MTP",
            "TOUCAN projekt, TOUCAN",
            "JETI Project, JETI",
            "COALA Project, COALA"})
    void testViewAllProjects(String projectName, String projectKey) {
        dashboardPage.navigateToAllProjectsPage();
        browseProjectsPage.searchProject(projectName);
        Assertions.assertTrue(browseProjectsPage.isProjectFound(projectKey));
    }
}
