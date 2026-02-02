package com.framework.driver;

import com.framework.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", ConfigManager.getProperty("browser", "chrome")).toLowerCase();

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                String chromeArgs = ConfigManager.getProperty("chrome.arguments", "");
                if (!chromeArgs.isEmpty()) {
                    for (String arg : chromeArgs.split(",")) {
                        chromeOptions.addArguments(arg.trim());
                    }
                }
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                String firefoxArgs = ConfigManager.getProperty("firefox.arguments", "");
                if (!firefoxArgs.isEmpty()) {
                    for (String arg : firefoxArgs.split(",")) {
                        firefoxOptions.addArguments(arg.trim());
                    }
                }
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }
}
