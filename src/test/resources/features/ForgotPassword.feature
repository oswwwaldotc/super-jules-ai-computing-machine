@web @chrome @firefox @forgotpassword
Feature: Forgot Password Functionality
  Verify the forgot password functionality of the application

  Scenario: Successful Password Reset
    Given I navigate to the Forgot Password page
    When I enter a valid email "test@example.com"
    And I click the Retrieve password button
    Then I should see a success message indicating the email has been sent

  Scenario: Invalid Email Format
    Given I navigate to the Forgot Password page
    When I enter an invalid email "invalid-email"
    And I click the Retrieve password button
    Then I should see an error message indicating invalid email
