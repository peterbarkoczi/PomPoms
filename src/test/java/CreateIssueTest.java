import POM.CreateIssueModal;
import POM.IssuePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        if (!createIssueModal.isCanceled()) {
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

    private Stream<Arguments> generateArgumentsForSimpleCreateIssueTest() {
        return Stream.of(Arguments.of(getUUID()));
    }

    @ParameterizedTest(name = "test available issue types {index}: {0}")
    @MethodSource("generateParametersForAvailableIssueTypesTest")
    void testAvailableIssueTypes(String project, List<String> expectedIssueTypes) {
        createIssueModal = dashboardPage.initiateCreateIssue();
        createIssueModal.selectProject(project);
        createIssueModal.clickOnIssueTypesDropdown();
        Assertions.assertTrue(
                createIssueModal.getAvailableIssueTypes()
                        .containsAll(expectedIssueTypes));
        createIssueModal.cancel();
    }

    private Stream<Arguments> generateParametersForAvailableIssueTypesTest() {
        Stream.Builder<Arguments> stream = Stream.builder();
        for (String project : projects) {
            stream.add(Arguments.of(
                    project, issueTypes
            ));
        }
        return stream.build();
    }

    @ParameterizedTest(name = "test sub-tasks {index}: project = \"{0}\", issue type = \"{1}\"")
    @MethodSource("generateParametersForSubTaskTest")
    void testIsSubTaskCreatable(String project, String issueType, String summary) {
        IssuePage issue = dashboardPage.createIssue(project, issueType, summary);
        issue.createSubTask(summary);
        Assertions.assertTrue(issue.doesSubTaskListContainSubTaskWithSummary(summary));
    }

    private Stream<Arguments> generateParametersForSubTaskTest() {
        Stream.Builder<Arguments> stream = Stream.builder();
        for (String project : projects) {
            for (String issueType : issueTypes) {
                stream.add(Arguments.of(
                        project, issueType, getUUID()
                ));
            }
        }
        return stream.build();
    }

//    @ParameterizedTest(name = "Create issue test {index} for project \"{0}\" and issue type \"{1}\"")
//    @MethodSource("generateArgumentsForComplexCreateIssueTest")
//    void testCreateIssue(String project, String issueType, String summary) {
//        Assertions.assertEquals(
//                summary,
//                dashboardPage
//                        .createIssue(project, issueType, summary)
//                        .getSummary());
//    }
//
//    private Stream<Arguments> generateArgumentsForComplexCreateIssueTest() {
//        List<String> projects = Arrays.asList("TOUCAN", "JETI", "COALA");
//        List<String> issueTypes = Arrays.asList("Story", "Task", "Bug");
//        Stream.Builder<Arguments> stream = Stream.builder();
//
//        for (String project : projects) {
//            for (String issueType : issueTypes) {
//                stream.add(Arguments.of(
//                        project, issueType, getUUID()
//                ));
//            }
//        }
//        return stream.build();
//    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}
