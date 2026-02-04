package com.framework.pages;

import com.framework.utils.ConfigManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ForgotPasswordPage {
    private Page page;

    private String emailInput = "#email";
    private String submitButton = "//button[@type='submit']";

    public ForgotPasswordPage(Page page) {
        this.page = page;
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("forgotpassword.url", "https://practice.expandtesting.com/forgot-password");
        page.navigate(url);
    }

    public void enterEmail(String email) {
        page.fill(emailInput, email);
    }

    public void clickRetrievePassword() {
        try {
            page.click(submitButton);
        } catch (Exception e) {
            page.locator(submitButton).click(new Locator.ClickOptions().setForce(true));
        }
        page.waitForLoadState();
    }

    public String getPageUrl() {
        return page.url();
    }

    public boolean isInputInvalid() {
       try {
           return page.isVisible(".invalid-feedback");
       } catch (Exception e) {
           return false;
       }
    }

    public boolean isSuccessMessageDisplayed() {
        return page.url().contains("email-sent") || page.content().contains("e-mail has been sent");
    }
}
