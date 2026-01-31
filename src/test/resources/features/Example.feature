Feature: Example Domain
  Verify the functionality of the Example Domain page

  Scenario: Verify page title and content
    Given I navigate to the Example Domain page
    Then the page title should be "Example Domain"
    And the header text should be "Example Domain"
    And the "More information" link should contain "Learn more"
