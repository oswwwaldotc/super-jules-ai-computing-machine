package com.framework.pages;

import com.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailInput = By.id("email");
    private By submitButton = By.xpath("//button[@type='submit']");
    // Assuming success message locator based on typical behavior, might need adjustment after running.
    // Looking at other pages, success messages are often in flash messages or specific divs.
    // The "Forgot Password" page redirects or shows a message.
    // Since I can't see the result page without submitting, I'll assume standard behavior or wait for URL change if redirection happens.
    // But let's assume there is a confirmation message.
    // I will try to look for any element that might appear.
    // If I look at Login.feature, it checks for redirection.
    // For now, I'll rely on URL or body text.

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("forgotpassword.url", "https://practice.expandtesting.com/forgot-password");
        driver.get(url);
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickRetrievePassword() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        try {
            button.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isInputInvalid() {
       // HTML5 validation usually adds pseudo-classes, or the browser handles it.
       // The form has 'needs-validation' class.
       // The input has 'invalid-feedback' sibling.
       try {
           WebElement invalidFeedback = driver.findElement(By.cssSelector(".invalid-feedback"));
           return invalidFeedback.isDisplayed();
       } catch (Exception e) {
           return false;
       }
    }

    public boolean isSuccessMessageDisplayed() {
        // The text is "An e-mail has been sent to you..."
        return driver.getCurrentUrl().contains("email-sent") || driver.getPageSource().contains("e-mail has been sent");
    }
}
