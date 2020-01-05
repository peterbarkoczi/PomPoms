package FunctionalTests;

import POM.IssuePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BrowseIssueTest extends BaseTest {
    private IssuePage issuePage;

    @BeforeEach
    void login() {
        loginPage.login(username, password);
        this.issuePage = new IssuePage(driver);
    }

    @ParameterizedTest(name = "browse issue test {index}: issue {0}")
    @MethodSource("generateParametersForBrowseIssueTests")
    void testBrowseIssue(String issueKey, String url) {
        navigateTo(url);
        Assertions.assertEquals(issueKey, issuePage.getIssueKey());
    }

    private Stream<Arguments> generateParametersForBrowseIssueTests() {
        Stream.Builder<Arguments> stream = Stream.builder();
        int idRange = 3;
        for (String project : projects) {
            for (int id = 1; id <= idRange; id++) {
                String issueKey = project + "-" + id;
                String url = baseUrl + "browse/" + issueKey;
                stream.add(Arguments.of(issueKey, url));
            }
        } return stream.build();
    }
}
