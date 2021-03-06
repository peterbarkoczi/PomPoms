import POM.EditIssueModal;
import POM.IssuePage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EditIssueTest extends BaseTest {

    private EditIssueModal editIssueModal;
    private IssuePage issue;

    @BeforeEach
    void setup() {
        loginPage.login(username, password);
        this.editIssueModal = new EditIssueModal(driver);
        this.issue = new IssuePage(driver);
    }

    @AfterEach
    void resetTestData() {
        if (!editIssueModal.isCanceled()) issue.deleteIssue();
    }

    @ParameterizedTest
    @CsvSource({"updated summary"})
    void testEditIssue(String inputText) {
        dashboardPage.createIssue("Main Testing Project", "Bug", "test issue");
        issue.clickOnEditButton();
        editIssueModal.fillAndUpdateSummary(inputText);
        issue.waitForSuccessMessage();
        Assertions.assertEquals(inputText, issue.getSummary());
    }

    @Test
    void testAbortEditingWithCancel() {
        navigateTo("https://jira.codecool.codecanvas.hu/browse/MTP-154");
        String summary = issue.getSummary();
        issue.clickOnEditButton();
        editIssueModal.fillAndCancelEditing("updated summary");
        driver.switchTo().alert().accept();
        Assertions.assertEquals(summary, issue.getSummary());
    }
}
