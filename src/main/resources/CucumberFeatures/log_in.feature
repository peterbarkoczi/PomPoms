Feature: Login
  Login is successful with correct credentials
  Login fails with incorrect credentials

  Scenario Template: Login is successful or not
    Given I have a browser opened
    When I visit the Jira login page
    And I log in with "<username>" and "<password>"
    Then the visibility of the log out button should be "<is_successful>"
    Then I exit the browser

    Examples:
      | username           | password           | is_successful |
      | user12             | CoolCanvas19.      | true          |
      | user12             | COOLCANVAS19.      | false         |
      | USER12             | CoolCanvas19.      | true          |
      | incorrect username | incorrect password | false         |
      |                    |                    | false         |