package com.framework.pages;

import com.framework.utils.ConfigManager;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ExamplePage {
    private Page page;
    private long pageLoadStartTime;

    private String header = "h1";
    private String moreInfoLink = "a";

    public ExamplePage(Page page) {
        this.page = page;
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("base.url", "https://example.com");
        pageLoadStartTime = System.currentTimeMillis();
        page.navigate(url);
        waitForPageLoad();
    }

    public String getPageTitle() {
        return page.title();
    }

    public String getHeaderText() {
        return page.textContent(header);
    }

    public String getMoreInformationLinkText() {
        return page.textContent(moreInfoLink);
    }

    public void clickMoreInformationLink() {
        page.click(moreInfoLink);
    }

    public String getCurrentUrl() {
        return page.url();
    }

    public String getPageContent() {
        return page.locator("body").innerText();
    }

    public boolean isElementPresent(String locatorKey) {
        try {
            String locatorValue = ConfigManager.getProperty(locatorKey);
            if (locatorValue == null) {
                locatorValue = locatorKey;
            }
            // Assuming locatorValue is a valid selector (XPath or CSS)
            page.waitForSelector(locatorValue, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(5000));
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
        page.waitForLoadState();
    }

    public void IwaitForElementVisible(String locator) {
        page.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }
}
