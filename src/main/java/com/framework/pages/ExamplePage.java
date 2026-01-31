package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExamplePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By header = By.cssSelector("h1");
    private By moreInfoLink = By.cssSelector("a");

    public ExamplePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://example.com");
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
}
