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

    private By usernameInput;
    private By passwordInput;
    private By loginButton;
    private By flashMessage;
    private By logoutButton;
    private By rememberMeCheckbox;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.usernameInput = By.id(ConfigManager.getProperty("locator.username.id"));
        this.passwordInput = By.id(ConfigManager.getProperty("locator.password.id"));
        this.loginButton = By.xpath(ConfigManager.getProperty("locator.loginButton.xpath"));
        this.flashMessage = By.id(ConfigManager.getProperty("locator.flashMessage.id"));
        this.logoutButton = By.xpath(ConfigManager.getProperty("locator.logoutButton.xpath"));
        this.rememberMeCheckbox = By.id(ConfigManager.getProperty("locator.rememberMe.id", "remember-me"));
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("login.url");
        driver.get(url);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigManager.getProperty("base.url", "http://localhost:7080");
        driver.get(baseUrl + path);
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

    public boolean isLoginButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForPageLoad() {
        wait.until(webDriver -> ((String) ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")).equals("complete"));
    }

    public void IwaitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void IwaitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void clickLogout() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public void selectRememberMe() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (!checkbox.isSelected()) {
            try {
                checkbox.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
            }
        }
    }

    public void logout() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }
}
