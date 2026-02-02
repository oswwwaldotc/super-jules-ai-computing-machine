package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.IssuesPage;
import com.framework.utils.ConfigManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class IssuesSteps {

    private IssuesPage issuesPage;

    @Given("I navigate to the Known Issues page")
    public void iNavigateToTheKnownIssuesPage() {
        issuesPage = new IssuesPage(DriverManager.getDriver());
        issuesPage.navigateTo();
    }

    @Then("I should see the issue with ID {string}")
    public void iShouldSeeTheIssueWithID(String issueID) {
        Assertions.assertTrue(issuesPage.isIssuePresent(issueID),
                "Issue with ID " + issueID + " was not found on the page");
    }

    @Then("the issue status should be {string}")
    public void theIssueStatusShouldBe(String expectedStatus) {
        String actualStatus = issuesPage.getIssueStatus();
        Assertions.assertEquals(expectedStatus, actualStatus,
                "Expected issue status: " + expectedStatus + ", but got: " + actualStatus);
    }

    @Then("the issue priority should be {string}")
    public void theIssuePriorityShouldBe(String expectedPriority) {
        String actualPriority = issuesPage.getIssuePriority();
        Assertions.assertEquals(expectedPriority, actualPriority,
                "Expected issue priority: " + expectedPriority + ", but got: " + actualPriority);
    }

    @When("I add a comment {string} to the issue with ID {string}")
    public void iAddACommentToTheIssueWithID(String commentText, String issueID) {
        issuesPage.clickOnIssue(issueID);
        issuesPage.addComment(commentText);
    }

    @Then("the comment should be successfully added to the issue")
    public void theCommentShouldBeSuccessfullyAddedToTheIssue() {
        Assertions.assertTrue(issuesPage.isCommentAdded(),
                "Comment was not successfully added to the issue");
    }

    @Then("I should see my comment in the issue's comment section")
    public void iShouldSeeMyCommentInTheIssueCommentSection() {
        Assertions.assertTrue(issuesPage.isCommentVisible(),
                "Comment is not visible in the issue's comment section");
    }

    @When("I assign the issue with ID {string} to user {string}")
    public void iAssignTheIssueWithIDToUser(String issueID, String username) {
        issuesPage.clickOnIssue(issueID);
        issuesPage.assignIssueTo(username);
    }

    @Then("the issue should be assigned to {string}")
    public void theIssueShouldBeAssignedTo(String expectedAssignee) {
        String actualAssignee = issuesPage.getAssignedUser();
        Assertions.assertEquals(expectedAssignee, actualAssignee,
                "Expected issue to be assigned to: " + expectedAssignee + ", but it's assigned to: " + actualAssignee);
    }

    @Then("I should see {string} as the assignee for the issue")
    public void iShouldSeeAsTheAssigneeForTheIssue(String expectedAssignee) {
        Assertions.assertTrue(issuesPage.isAssigneeDisplayed(expectedAssignee),
                "Assignee " + expectedAssignee + " is not displayed for the issue");
    }
}
