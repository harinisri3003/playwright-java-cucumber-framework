
Feature: Employee Management

  Background:
    Given the user is on the login page
    When the user logs into the application with user credentials

  Scenario: Verify employee management page
    When the user navigates to the Employee List page
    Then the employee list page should be displayed