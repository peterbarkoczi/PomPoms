package FunctionalTests;

import POM.EditIssueModal;
import POM.IssuePage;
import POM.LoginPage;
import POM.LogoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions extends BaseTest {
    LogoutPage logoutPage;
    IssuePage issuePage;
    EditIssueModal editIssueModal;

    @Given("I have a valid Jira Account")
    public void iHaveAValidJiraAccount() {
    }

    @Given("I have a browser opened")
    public void iHaveABrowserOpened() {
        setDriver();
    }

    @Then("I exit the browser")
    public void iExitTheBrowser() {
        driver.quit();
    }

    // LOGIN STEPS

    @When("I visit the Jira login page")
    public void iVisitTheJiraLoginPage() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    @When("I log in with {string} and {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Then("the visibility of the log out button should be {string}")
    public void theVisibilityOfTheLogOutButtonShouldBe(String isSuccessful) {
        assertEquals(
                Boolean.parseBoolean(isSuccessful),
                dashboardPage.isLogoutButtonPresent());
    }

    // LOGOUT STEPS

    @Given("I am logged in to Jira with a valid account")
    public void iAmLoggedInToJiraWithAValidAccount() {
        setDriver();
        iVisitTheJiraLoginPage();
        iLogInWithUsernameAndPassword(
                System.getenv("JIRA_USERNAME"),
                System.getenv("JIRA_PASSWORD")
        );
    }

    @When("I click on the profile avatar")
    public void iClickOnTheProfileAvatar() {
        dashboardPage.clickOnAvatar();
    }

    @And("I click on {string} option")
    public void iClickOnOption(String arg0) {
        dashboardPage.clickOnLogoutButton();
    }

    @Then("Log In should be available")
    public void logInShouldBeAvailable() {
        logoutPage = new LogoutPage(driver);
        assertTrue(logoutPage.isLoginButtonPresent());
    }

    // EDIT ISSUE STEPS

    @Given("I have an editable issue with {string} in its Summary field")
    public void iHaveAnEditableIssue(String summary) {
        dashboardPage.createIssue("Main Testing Project", "Bug", summary);
        issuePage = new IssuePage(driver);
    }

    @When("I click on Edit button and edit its Summary to {string}")
    public void iClickOnEditButtonAndEditItsSummary(String updatedSummary) {
        issuePage.clickOnEditButton();
        editIssueModal = new EditIssueModal(driver);
        editIssueModal.fillAndUpdateSummary(updatedSummary);
        issuePage.waitForSuccessMessage();
    }

    @Then("I should see that the Summary changed to {string}")
    public void iShouldSeeThatTheSummaryChangedTo(String updatedSummary) {
        assertEquals(updatedSummary, issuePage.getSummary());
    }

    @Then("I reset test data and exit the browser")
    public void iResetTestDataAndExitTheBrowser() {
        issuePage.deleteIssue();
        driver.quit();
    }

}
