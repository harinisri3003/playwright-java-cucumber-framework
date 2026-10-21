Feature: Forgot Password

  Background:
    Given the user is on the login page

  @regression
  Scenario: Verify forgot password flow starts from login
    When the user logs into the application with user credentials
    Then the user should see the "Dashboard" overview