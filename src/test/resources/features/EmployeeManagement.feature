Feature: Employee Management

  Background:
    Given the user is on the login page

  @regression
  Scenario: Verify employee management page after login
    When the user logs into the application with user credentials
    Then the user should see the "Dashboard" overview