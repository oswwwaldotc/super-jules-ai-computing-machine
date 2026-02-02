@web @chrome @firefox @login @loginfunctionality
Feature: Login Functionality
  Verify the login functionality of the application

  Scenario: Successful Login
    Given I launch the browser and navigate to the login page
    When I enter username "login.username" and password "login.password"
    And I click the Login button
    Then I should be redirected to the secure page
    And I should see the success message "You logged into a secure area!"
    And I should see the Logout button

  Scenario: Invalid Username
    Given I launch the browser and navigate to the login page
    When I enter username "failUser" and password "login.password"
    And I click the Login button
    Then I should see the error message "Your username is invalid!"
    And I should remain on the login page

  Scenario: Invalid Password
    Given I launch the browser and navigate to the login page
    When I enter username "login.username" and password "WrongPassword"
    And I click the Login button
    Then I should see the error message "Your password is invalid!"
    And I should remain on the login page

  Scenario: Empty Credentials
    Given I launch the browser and navigate to the login page
    When I leave the username and password fields empty
    And I click the Login button
    Then I should see the error message "Your username is invalid!"
    And I should remain on the login page

  Scenario: Logout Functionality
    Given I have successfully logged in with username "login.username" and password "login.password"
    When I click the Logout button
    Then I should be redirected to the login page
    And I should see the message "You logged out of the secure area!"
    And the Login button should be visible

  Scenario: Remember Me Functionality
    Given I launch the browser and navigate to the login page
    When I enter username "login.username" and password "login.password"
    And I select the Remember Me option
    And I click the Login button
    Then I should be redirected to the secure page
    When I close and reopen the browser
    And I navigate to the secure page
    Then I should still be logged in