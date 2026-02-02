package com.framework.pages;

import com.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormValidationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By contactNameInput = By.name("ContactName");
    private By contactNumberInput = By.name("contactnumber");
    private By pickupDateInput = By.name("pickupdate");
    private By paymentMethodSelect = By.name("payment");
    private By registerButton = By.xpath("//button[@type='submit']");

    public FormValidationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("formvalidation.url", "https://practice.expandtesting.com/form-validation");
        driver.get(url);
    }

    public void enterContactName(String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(contactNameInput));
        element.clear();
        element.sendKeys(name);
    }

    public void enterContactNumber(String number) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(contactNumberInput));
        element.clear();
        element.sendKeys(number);
    }

    public void enterPickupDate(String date) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(pickupDateInput));
        element.clear();
        element.sendKeys(date);
    }

    public void selectPaymentMethod(String method) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodSelect));
        Select select = new Select(element);
        select.selectByValue(method);
    }

    public void clickRegister() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        try {
            button.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public boolean isSuccess() {
        // Assuming redirection to /form-confirmation
        try {
             return wait.until(ExpectedConditions.urlContains("form-confirmation"));
        } catch (Exception e) {
             return false;
        }
    }

    public boolean isValidationErrorDisplayed(String fieldName) {
        try {
            // Wait for at least one invalid feedback to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".invalid-feedback")));
            return true;
        } catch (Exception e) {
            // If timeout, return false
            return false;
        }
    }
}
