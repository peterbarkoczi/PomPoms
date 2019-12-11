import POM.CreateIssueModal;
import POM.IssuePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateIssueTest extends BaseTest {
    CreateIssueModal createIssueModal;

    @BeforeEach
    void setup() {
        loginPage.login(username, password);
        this.createIssueModal = new CreateIssueModal(driver);
    }

    @Test
    void testCreateIssue() {
        String issueSummary = dashboardPage.createIssue("Main Testing Project", "", "random");
        createIssueModal.catchPopup();
        IssuePage issue = new IssuePage(driver);
        Assertions.assertEquals(issueSummary, issue.getSummary());
    }




}
