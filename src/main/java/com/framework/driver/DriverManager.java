package com.framework.driver;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static boolean isDriverInitialized() {
        return driver.get() != null;
    }

    public static void clearDriver() {
        driver.remove();
    }   

    public static void initializeDriver(WebDriver webDriver) {
        if (driver.get() == null) {
            driver.set(webDriver);
        }
    }

    public static void closeBrowser() {
        if (driver.get() != null) {
            driver.get().close();
        }
    }
}
