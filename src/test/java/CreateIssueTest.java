import POM.CreateIssueModal;
import POM.IssuePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

public class CreateIssueTest extends BaseTest {
    CreateIssueModal createIssueModal;

    @BeforeEach
    void setup() {
        loginPage.login(username, password);
        this.createIssueModal = new CreateIssueModal(driver);
    }

    @AfterEach
    void resetTestData() {
        IssuePage issue = new IssuePage(driver);
        issue.deleteIssue();
    }

    @ParameterizedTest(name = "Create issue test {index}")
    @MethodSource("generateArgumentsForSimpleCreateIssueTest")
    void testCreateIssue(String summary) {
        Assertions.assertEquals(
                summary,
                dashboardPage
                        .createIssue("Main Testing Project", "", summary)
                        .getSummary()
        );
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    private Stream<Arguments> generateArgumentsForSimpleCreateIssueTest() {
        return Stream.of(
                Arguments.of(getUUID())
        );
    }
}
