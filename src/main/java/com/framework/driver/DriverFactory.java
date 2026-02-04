package com.framework.driver;

import com.framework.utils.ConfigManager;
import com.microsoft.playwright.*;
import java.util.ArrayList;
import java.util.List;

public class DriverFactory {

    public static Page createPage() {
        Playwright playwright = Playwright.create();
        DriverManager.setPlaywright(playwright);

        String browserName = System.getProperty("browser", ConfigManager.getProperty("browser", "chrome")).toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", ConfigManager.getProperty("headless", "true")));

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);

        // Basic argument handling
        String args = "";
        if (browserName.contains("chrome")) {
             args = ConfigManager.getProperty("chrome.arguments", "");
        } else if (browserName.contains("firefox")) {
             args = ConfigManager.getProperty("firefox.arguments", "");
        }

        if (!args.isEmpty()) {
            List<String> argList = new ArrayList<>();
            for (String arg : args.split(",")) {
                String trimmed = arg.trim();
                if (trimmed.isEmpty()) continue;
                if (trimmed.startsWith("--headless")) continue;
                if (trimmed.startsWith("--window-size")) continue;
                if (!trimmed.startsWith("-")) continue; // avoid malformed args treated as URLs
                argList.add(trimmed);
            }
            launchOptions.setArgs(argList);
        }

        Browser browser;
        switch (browserName) {
            case "chrome":
            case "chromium":
                browser = playwright.chromium().launch(launchOptions);
                break;
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                break;
            case "webkit":
            case "safari":
                browser = playwright.webkit().launch(launchOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
        DriverManager.setBrowser(browser);

        // Set viewport size if needed (defaulting to 1920x1080 for consistency with previous args)
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions().setViewportSize(1920, 1080);

        BrowserContext context = browser.newContext(contextOptions);
        DriverManager.setContext(context);

        Page page = context.newPage();
        DriverManager.setPage(page);

        return page;
    }
}
