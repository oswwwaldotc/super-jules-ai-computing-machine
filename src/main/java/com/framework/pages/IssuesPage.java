package com.framework.pages;

import com.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IssuesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By issueContainer = By.className("issue-item");
    private By issueIdSelector = By.className("issue-id");
    private By issueStatusSelector = By.className("issue-status");
    private By issuePrioritySelector = By.className("issue-priority");
    private By commentInputField = By.id("comment-input");
    private By addCommentButton = By.id("add-comment-btn");
    private By commentSection = By.id("comments-section");
    private By assigneeField = By.id("assignee-select");
    private By assignButton = By.id("assign-btn");
    private By assigneeDisplay = By.className("assignee-name");

    public IssuesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("issues.url", "http://localhost:7080/issues");
        driver.get(url);
        waitForPageLoad();
    }

    public boolean isIssuePresent(String issueID) {
        try {
            java.util.List<WebElement> issues = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(issueContainer));
            for (WebElement issue : issues) {
                String id = issue.findElement(issueIdSelector).getText();
                if (id.contains(issueID)) {
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
            return wait.until(ExpectedConditions.visibilityOfElementLocated(issueStatusSelector)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getIssuePriority() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(issuePrioritySelector)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickOnIssue(String issueID) {
        try {
            java.util.List<WebElement> issues = driver.findElements(issueContainer);
            for (WebElement issue : issues) {
                String id = issue.findElement(issueIdSelector).getText();
                if (id.contains(issueID)) {
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
            WebElement commentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(commentInputField));
            commentInput.clear();
            commentInput.sendKeys(commentText);
            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addCommentButton));
            addBtn.click();
            waitForPageLoad();
        } catch (Exception e) {
            throw new RuntimeException("Could not add comment: " + e.getMessage());
        }
    }

    public boolean isCommentAdded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(commentSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCommentVisible() {
        try {
            WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(commentSection));
            java.util.List<WebElement> comments = section.findElements(By.className("comment"));
            return !comments.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void assignIssueTo(String username) {
        try {
            WebElement assigneeSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(assigneeField));
            assigneeSelect.click();
            
            // Find and click the user option
            WebElement userOption = driver.findElement(By.xpath("//option[contains(text(), '" + username + "')]"));
            userOption.click();
            
            WebElement assignBtn = wait.until(ExpectedConditions.elementToBeClickable(assignButton));
            assignBtn.click();
            waitForPageLoad();
        } catch (Exception e) {
            throw new RuntimeException("Could not assign issue to user: " + e.getMessage());
        }
    }

    public String getAssignedUser() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(assigneeDisplay)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isAssigneeDisplayed(String assignee) {
        try {
            java.util.List<WebElement> assignees = driver.findElements(assigneeDisplay);
            for (WebElement element : assignees) {
                if (element.getText().contains(assignee)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private void waitForPageLoad() {
        wait.until(webDriver -> ((String) ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")).equals("complete"));
    }
}
