package com.framework.pages;

import com.framework.utils.ConfigManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class IssuesPage {
    private Page page;

    private String issueContainer = ".issue-item";
    private String issueIdSelector = ".issue-id";
    private String issueStatusSelector = ".issue-status";
    private String issuePrioritySelector = ".issue-priority";
    private String commentInputField = "#comment-input";
    private String addCommentButton = "#add-comment-btn";
    private String commentSection = "#comments-section";
    private String assigneeField = "#assignee-select";
    private String assignButton = "#assign-btn";
    private String assigneeDisplay = ".assignee-name";

    public IssuesPage(Page page) {
        this.page = page;
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("issues.url", "http://localhost:7080/issues");
        page.navigate(url);
        waitForPageLoad();
    }

    public boolean isIssuePresent(String issueID) {
        try {
            page.waitForSelector(issueContainer, new Page.WaitForSelectorOptions().setTimeout(5000));
            Locator issues = page.locator(issueContainer);
            int count = issues.count();
            for (int i = 0; i < count; i++) {
                String text = issues.nth(i).locator(issueIdSelector).textContent();
                if (text.contains(issueID)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getIssueStatus() {
        try {
            return page.textContent(issueStatusSelector);
        } catch (Exception e) {
            return "";
        }
    }

    public String getIssuePriority() {
        try {
            return page.textContent(issuePrioritySelector);
        } catch (Exception e) {
            return "";
        }
    }

    public void clickOnIssue(String issueID) {
        try {
            Locator issues = page.locator(issueContainer);
            int count = issues.count();
            for (int i = 0; i < count; i++) {
                Locator issue = issues.nth(i);
                if (issue.locator(issueIdSelector).textContent().contains(issueID)) {
                    issue.click();
                    waitForPageLoad();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not find and click issue with ID: " + issueID);
        }
    }

    public void addComment(String commentText) {
        try {
            page.fill(commentInputField, commentText);
            page.click(addCommentButton);
            waitForPageLoad();
        } catch (Exception e) {
            throw new RuntimeException("Could not add comment: " + e.getMessage());
        }
    }

    public boolean isCommentAdded() {
        try {
            return page.isVisible(commentSection);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCommentVisible() {
        try {
            Locator comments = page.locator(commentSection).locator(".comment");
            return comments.count() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void assignIssueTo(String username) {
        try {
            // Attempt to select by label first
            page.selectOption(assigneeField, new SelectOption().setLabel(username));
            
            page.click(assignButton);
            waitForPageLoad();
        } catch (Exception e) {
            throw new RuntimeException("Could not assign issue to user: " + e.getMessage());
        }
    }

    public String getAssignedUser() {
        try {
            return page.textContent(assigneeDisplay);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isAssigneeDisplayed(String assignee) {
        try {
            Locator assignees = page.locator(assigneeDisplay);
            int count = assignees.count();
            for (int i = 0; i < count; i++) {
                if (assignees.nth(i).textContent().contains(assignee)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private void waitForPageLoad() {
        page.waitForLoadState();
    }
}
