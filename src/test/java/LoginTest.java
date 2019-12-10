import POM.DashboardPage;
import POM.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private String url;

    @BeforeAll
    void setUrl() {
        this.url = "secure/Dashboard.jspa";
    }

    @BeforeEach
    void navigate() {
        navigateTo(baseUrl + url);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    void testSuccessfulLoginToJira() {
        loginPage.login(username, password);
        Assertions.assertEquals(
                username,
                dashboardPage.getUsernameFromAvatar());
    }

    @ParameterizedTest(name = "failed login test {index}: username = \"{0}\", password = \"{1}\", expected result = \"{2}\"")
    @MethodSource("getParametersForLoginTest")
    void testFailedLoginToJira(String username, String password, boolean expectedResult) {
        loginPage.login(username, password);
        Assertions.assertEquals(expectedResult, dashboardPage.isLogoutButtonPresent());
    }

    private Stream<Arguments> getParametersForLoginTest() {
        return Stream.of(
                Arguments.of(username, password, true),
                Arguments.of(username, password.toUpperCase(), false),
                Arguments.of(username.toUpperCase(), password, false),
                Arguments.of("incorrect username", password, false),
                Arguments.of("", "", false)
        );
    }

}