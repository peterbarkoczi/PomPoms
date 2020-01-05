Feature: Edit issue
  A logged in user should be able to edit an editable issue

  Scenario: Edit Summary of an issue
    Given I am logged in to Jira with a valid account
    And I have an editable issue with "text" in its Summary field
    When I click on Edit button and edit its Summary to "updated text"
    Then I should see that the Summary changed to "updated text"
    Then I reset test data and exit the browser
