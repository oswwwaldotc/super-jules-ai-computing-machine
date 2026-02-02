package com.framework.pages;

import com.framework.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExamplePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private long pageLoadStartTime;

    private By header = By.cssSelector("h1");
    private By moreInfoLink = By.cssSelector("a");

    public ExamplePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("base.url", "https://example.com");
        pageLoadStartTime = System.currentTimeMillis();
        driver.get(url);
        waitForPageLoad();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }

    public String getMoreInformationLinkText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(moreInfoLink)).getText();
    }

    public void clickMoreInformationLink() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink)).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageContent() {
        return driver.findElement(By.tagName("body")).getText();
    }

    public boolean isElementPresent(String locatorKey) {
        try {
            String locatorValue = ConfigManager.getProperty(locatorKey);
            if (locatorValue == null) {
                locatorValue = locatorKey;
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getPageLoadTime() {
        long pageLoadEndTime = System.currentTimeMillis();
        return pageLoadEndTime - pageLoadStartTime;
    }

    public void waitForPageLoad() {
        wait.until(webDriver -> ((String) ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")).equals("complete"));
    }

    public void IwaitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
