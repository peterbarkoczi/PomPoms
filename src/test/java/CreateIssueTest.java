import POM.CreateIssueModal;
import POM.IssuePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class CreateIssueTest extends BaseTest {
    private CreateIssueModal createIssueModal;

    @BeforeEach
    void setup() {
        loginPage.login(username, password);
        this.createIssueModal = new CreateIssueModal(driver);
    }

    @AfterEach
    void resetTestData() {
        if (createIssueModal.isSubmitted()) {
            IssuePage issue = new IssuePage(driver);
            issue.deleteIssue();
        }
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

    @ParameterizedTest(name = "test available issue types {index}: {0}")
    @MethodSource("generateParametersForAvailableIssueTypesTest")
    void testAvailableIssueTypes(String project, List<String> expectedIssueTypes) {
        CreateIssueModal modal = dashboardPage.initiateCreateIssue();
        modal.selectProject(project);
        modal.clickOnIssueTypesDropdown();
        Assertions.assertTrue(
                modal.getAvailableIssueTypes()
                        .containsAll(expectedIssueTypes));
        modal.cancel();
    }

    private Stream<Arguments> generateParametersForAvailableIssueTypesTest() {
        List<String> projects = Arrays.asList("TOUCAN", "JETI", "COALA");
        List<String> issueTypes = Arrays.asList("Story", "Task", "Bug");

        Stream.Builder<Arguments> stream = Stream.builder();
        for (String project : projects) {
            stream.add(Arguments.of(
                    project, issueTypes
            ));
        }
        return stream.build();
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    private Stream<Arguments> generateArgumentsForSimpleCreateIssueTest() {
        return Stream.of(Arguments.of(getUUID()));
    }

}
