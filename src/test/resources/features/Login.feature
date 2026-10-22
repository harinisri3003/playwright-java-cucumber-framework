Feature: User Login

  Background:
    Given the user is on the login page

  Scenario: Login with valid credentials
    When the user logs into the application with user credentials
    Then the user should see the "Dashboard" overview

  Scenario: Login with invalid credentials
    When the user logs into the application with username "InvalidUser" and password "WrongPass123"
    Then the user should see the "Invalid credentials" error message

  Scenario: Login with empty credentials
    When the user logs into the application with username "" and password ""
    Then the user should see the required field validation

  Scenario: Login with invalid password
    When the user logs into the application with username "Admin" and password "WrongPass123"
    Then the user should see the "Invalid credentials" error message
    
  @Logout
  Scenario: Logout successfully
    When the user logs into the application with user credentials
    And the user logs out
    Then the user should be redirected to the login page