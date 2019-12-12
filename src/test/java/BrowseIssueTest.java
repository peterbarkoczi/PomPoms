import org.junit.jupiter.api.BeforeEach;

public class BrowseIssueTest extends BaseTest {

    @BeforeEach
    void login() {
        loginPage.login(username, password);

    }

}
