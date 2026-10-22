
Feature: User Management

  Background:
    Given the user is on the login page
    When the user logs into the application with user credentials

  @regression
  Scenario: Verify System Users page is displayed
    When the user navigates to the System Users page
    Then the System Users page should be displayed