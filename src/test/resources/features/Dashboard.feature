Feature: Dashboard

  Background:
    Given the user is on the login page
    When the user logs into the application with user credentials

  @smoke @regression
  Scenario: Verify dashboard is displayed after successful login
    Then the user should see the "Dashboard" overview