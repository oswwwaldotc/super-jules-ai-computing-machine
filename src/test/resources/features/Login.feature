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
