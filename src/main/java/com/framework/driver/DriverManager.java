package com.framework.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class DriverManager {
    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    private DriverManager() {}

    public static Page getPage() {
        return pageThreadLocal.get();
    }

    public static BrowserContext getContext() {
        return contextThreadLocal.get();
    }

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    public static void setPage(Page page) {
        pageThreadLocal.set(page);
    }

    public static void setContext(BrowserContext context) {
        contextThreadLocal.set(context);
    }

    public static void setBrowser(Browser browser) {
        browserThreadLocal.set(browser);
    }

    public static void setPlaywright(Playwright playwright) {
        playwrightThreadLocal.set(playwright);
    }

    public static void quitDriver() {
        if (pageThreadLocal.get() != null) {
            pageThreadLocal.get().close();
            pageThreadLocal.remove();
        }
        if (contextThreadLocal.get() != null) {
            contextThreadLocal.get().close();
            contextThreadLocal.remove();
        }
        if (browserThreadLocal.get() != null) {
            browserThreadLocal.get().close();
            browserThreadLocal.remove();
        }
        if (playwrightThreadLocal.get() != null) {
            playwrightThreadLocal.get().close();
            playwrightThreadLocal.remove();
        }
    }

    public static void closePage() {
        if (pageThreadLocal.get() != null) {
            pageThreadLocal.get().close();
        }
    }

    // For compatibility with steps that might ask for getDriver (we'll fix them, but good to have a getPage alias if needed)
    public static Page getDriver() {
        return getPage();
    }
}
