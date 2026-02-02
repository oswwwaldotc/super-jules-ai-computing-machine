@web @chrome @firefox @formvalidation
Feature: Form Validation
  Verify the form validation functionality

  Scenario: Successful Form Submission
    Given I navigate to the Form Validation page
    When I enter contact name "John Doe"
    And I enter contact number "012-3456789"
    And I enter pickup date "2024-12-31"
    And I select payment method "card"
    And I click the Register button
    Then I should be redirected to the confirmation page

  Scenario: Validation Error on Empty Fields
    Given I navigate to the Form Validation page
    When I leave all fields empty
    And I click the Register button
    Then I should see validation errors
