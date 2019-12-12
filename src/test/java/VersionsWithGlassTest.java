import POM.GlassDocumentationPage;
import POM.ProjectSettingsPage;
import POM.VersionsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class VersionsWithGlassTest extends BaseTest {

    GlassDocumentationPage glassDocumentationPage;
    ProjectSettingsPage projectSettingsPage;
    VersionsPage versionsPage;

    @BeforeEach
    void setup() {
        glassDocumentationPage = new GlassDocumentationPage(driver);
        projectSettingsPage = new ProjectSettingsPage(driver);
        versionsPage = new VersionsPage(driver);
        loginPage.login(username, password);
        navigateTo("https://jira.codecool.codecanvas.hu/projects/PP5?selectedItem=com.codecanvas.glass:glass");
    }

    @AfterEach
    void deleteNewVersion() {
        navigateTo("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP5/administer-versions?status=unreleased");
        versionsPage.deleteVersion("new-test-version");
    }

    @ParameterizedTest
    @CsvSource({"new-test-version"})
    void testCompareGlassVersionsWithProjectVersions(String versionName) {
        navigateTo("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP5/administer-versions?status=unreleased");
        versionsPage.createNewVersion(versionName);
        navigateTo("https://jira.codecool.codecanvas.hu/projects/PP5?selectedItem=com.codecanvas.glass:glass");
        glassDocumentationPage.clickOnVersionsTab();
        Assertions.assertTrue(glassDocumentationPage.validateCreatedVersion(versionName));
    }

}
