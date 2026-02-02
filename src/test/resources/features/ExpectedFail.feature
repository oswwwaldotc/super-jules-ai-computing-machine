@web @chrome @firefox @expectedfail
Feature: Expected Issues Functionality
    Verify the expected issues functionality of the application

    Scenario: Known Issue Verification
        Given I navigate to the Known Issues page
        Then I should see the issue with ID "ISSUE-1234"
        And the issue status should be "Open"
        And the issue priority should be "High"

    Scenario: Verify Issue Commenting
        Given I navigate to the Known Issues page
        When I add a comment "This is a test comment" to the issue with ID "ISSUE-1234"
        Then the comment should be successfully added to the issue
        And I should see my comment in the issue's comment section

    Scenario: Verify Issue Assignment
        Given I navigate to the Known Issues page
        When I assign the issue with ID "ISSUE-1234" to user "testuser"
        Then the issue should be assigned to "testuser"
        And I should see "testuser" as the assignee for the issue
