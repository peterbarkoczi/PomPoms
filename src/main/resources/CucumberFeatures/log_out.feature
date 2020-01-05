Feature: Logout
  After logout, the user should see the Log In form

  Scenario: Simple logout
    Given I am logged in to Jira with a valid account
    When I click on the profile avatar
    And I click on "Log Out" option
    Then Log In should be available
    Then I exit the browser
