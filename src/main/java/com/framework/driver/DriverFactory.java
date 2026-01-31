package com.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--window-size=1920,1080");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }
}
