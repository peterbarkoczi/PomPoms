package FunctionalTests;

import POM.DashboardPage;
import POM.LoginPage;
import POM.LogoutPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogoutTest extends BaseTest {

    LogoutPage logoutPage;

    @BeforeEach
    void navigate() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        logoutPage = new LogoutPage(driver);
        navigateTo(baseUrl + loginPage.getUrl());
        loginPage.login(username, password);
    }

    @AfterEach
    void checkLoginButton() {
        Assertions.assertTrue(logoutPage.isLoginButtonPresent());
    }

    @Test
    void testLogout() {
        dashboardPage.clickOnAvatar();
        dashboardPage.clickOnLogoutButton();
    }

    @Test
    void testLogoutAndNavigateBrowserBack() {
        testLogout();
        driver.navigate().back();
    }

    @Test
    void testLogoutOnTwoTabs() {
        dashboardPage.openNewTab(baseUrl + dashboardPage.getUrl());
        dashboardPage.changeTab(1);
        testLogout();
        dashboardPage.changeTab(2);
        driver.navigate().refresh();
    }

}
