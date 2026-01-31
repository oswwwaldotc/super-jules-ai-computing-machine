package com.framework.pages;

import com.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By flashMessage = By.id("flash");
    private By logoutButton = By.xpath("//a[contains(@href, '/logout')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("login.url");
        driver.get(url);
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        element.clear();
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        element.clear();
        element.sendKeys(password);
    }

    public void clickLogin() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public String getFlashMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
    }

    public boolean isLogoutButtonDisplayed() {
        try {
             return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
