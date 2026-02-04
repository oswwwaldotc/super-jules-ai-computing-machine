package com.framework.pages;

import com.framework.utils.ConfigManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {
    private Page page;

    private String usernameInput;
    private String passwordInput;
    private String loginButton;
    private String flashMessage;
    private String logoutButton;
    private String rememberMeCheckbox;

    public LoginPage(Page page) {
        this.page = page;

        this.usernameInput = "#" + ConfigManager.getProperty("locator.username.id");
        this.passwordInput = "#" + ConfigManager.getProperty("locator.password.id");
        this.loginButton = ConfigManager.getProperty("locator.loginButton.xpath");
        this.flashMessage = "#" + ConfigManager.getProperty("locator.flashMessage.id");
        this.logoutButton = ConfigManager.getProperty("locator.logoutButton.xpath");
        this.rememberMeCheckbox = "#" + ConfigManager.getProperty("locator.rememberMe.id", "remember-me");
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("login.url");
        page.navigate(url);
    }

    public void navigateTo(String path) {
        String baseUrl = ConfigManager.getProperty("base.url", "http://localhost:7080");
        page.navigate(baseUrl + path);
    }

    public void enterUsername(String username) {
        page.fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordInput, password);
    }

    public void clickLogin() {
        try {
            page.click(loginButton);
        } catch (Exception e) {
            page.locator(loginButton).click(new Locator.ClickOptions().setForce(true));
        }
    }

    public String getFlashMessage() {
        return page.textContent(flashMessage);
    }

    public boolean isLogoutButtonDisplayed() {
        try {
            return page.isVisible(logoutButton);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return page.isVisible(loginButton);
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return page.url();
    }

    public void waitForPageLoad() {
        page.waitForLoadState();
    }

    public void IwaitForElementVisible(String locator) {
        page.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void IwaitForElementClickable(String locator) {
        // Just wait for it to be visible/enabled implicitly by Playwright or explicit wait
        page.waitForSelector(locator);
    }

    public void clickLogout() {
        try {
            page.click(logoutButton);
        } catch (Exception e) {
            page.locator(logoutButton).click(new Locator.ClickOptions().setForce(true));
        }
    }

    public void selectRememberMe() {
        Locator checkbox = page.locator(rememberMeCheckbox);
        if (!checkbox.isChecked()) {
            checkbox.click(new Locator.ClickOptions().setForce(true));
        }
    }

    public void logout() {
        clickLogout();
    }
}
