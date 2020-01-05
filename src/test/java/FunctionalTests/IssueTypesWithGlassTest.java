package FunctionalTests;

import POM.GlassDocumentationPage;
import POM.ProjectSettingsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class IssueTypesWithGlassTest extends BaseTest {
    private GlassDocumentationPage glassPage;
    private ProjectSettingsPage projectPage;

    @BeforeEach
    void login() {
        loginPage.login(username, password);
    }

    @ParameterizedTest(name = "Test issue types with Glass")
    @CsvSource({
            "https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/summary, https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass"
    })
    void testGlassIssueTypes(String projectUrl, String glassUrl) {
        navigateTo(projectUrl);
        this.projectPage = new ProjectSettingsPage(driver);
        List<String> projectIssueTypes = projectPage.getIssueTypes();

        navigateTo(glassUrl);
        this.glassPage = new GlassDocumentationPage(driver);
        List<String> glassIssueTypes = glassPage.getIssueTypes();

        Assertions.assertEquals(projectIssueTypes, glassIssueTypes);
    }
}
