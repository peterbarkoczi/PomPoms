import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BrowseProjectsTest extends BaseTest {

    @BeforeEach
    void navigateToDashboard() {
        navigateTo(baseUrl + dashboardPage.getUrl());
        loginPage.login(username, password);
    }

    @ParameterizedTest
    @CsvSource({ "Main Testing Project, MTP"})
    void testViewAllProjects(String projectName, String projectKey) {
        dashboardPage.navigateToAllProjectsPage();
        browseProjectsPage.searchProject(projectName);
        Assertions.assertTrue(browseProjectsPage.isProjectFound(projectKey));
    }
}
