package FunctionalTests;

import POM.GlassDocumentationPage;
import POM.VersionsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class VersionsWithGlassTest extends BaseTest {

    private GlassDocumentationPage glassDocumentationPage;
    private VersionsPage versionsPage;

    @BeforeEach
    void setup() {
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
        this.versionsPage = new VersionsPage(driver);
        versionsPage.createNewVersion(versionName);
        navigateTo("https://jira.codecool.codecanvas.hu/projects/PP5?selectedItem=com.codecanvas.glass:glass");
        this.glassDocumentationPage = new GlassDocumentationPage(driver);
        glassDocumentationPage.clickOnVersionsTab();
        Assertions.assertTrue(glassDocumentationPage.validateCreatedVersion(versionName));
    }

}
