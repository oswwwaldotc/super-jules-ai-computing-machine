@web @chrome @firefox @example @ignorefornow
Feature: Example Domain
  Verify the functionality of the Example Domain page

  Scenario: Verify page title and content
    Given I navigate to the Example Domain page
    Then the page title should be "page.title"
    And the header text should be "header.text"
    And the "More information" link should contain "link.contain.text"

  Scenario: Verify link navigation
    Given I navigate to the Example Domain page
    When I click on the "More information" link
    Then I should be redirected to "link.url"
    And the new page title should be "Example Domains"
    And the new page URL should be "link.url"
    And the new page should contain the text "Example Domains"
  
  Scenario: Verify non-existent element handling
    Given I navigate to the Example Domain page
    Then I should not find an element with the locator "non.existent.element"
    And an appropriate error message should be logged indicating the element was not found
    And the test should continue without failing
  
  Scenario: Verify page load time
    Given I navigate to the Example Domain page
    Then the page should load within "page.load.time" seconds
    And a log entry should be created with the actual load time