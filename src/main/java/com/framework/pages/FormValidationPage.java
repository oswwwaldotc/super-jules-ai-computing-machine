package com.framework.pages;

import com.framework.utils.ConfigManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class FormValidationPage {
    private Page page;

    private String contactNameInput = "[name='ContactName']";
    private String contactNumberInput = "[name='contactnumber']";
    private String pickupDateInput = "[name='pickupdate']";
    private String paymentMethodSelect = "[name='payment']";
    private String registerButton = "//button[@type='submit']";

    public FormValidationPage(Page page) {
        this.page = page;
    }

    public void navigateTo() {
        String url = ConfigManager.getProperty("formvalidation.url", "https://practice.expandtesting.com/form-validation");
        page.navigate(url);
    }

    public void enterContactName(String name) {
        page.fill(contactNameInput, name);
    }

    public void enterContactNumber(String number) {
        page.fill(contactNumberInput, number);
    }

    public void enterPickupDate(String date) {
        page.fill(pickupDateInput, date);
    }

    public void selectPaymentMethod(String method) {
        page.selectOption(paymentMethodSelect, method);
    }

    public void clickRegister() {
        try {
            page.click(registerButton);
        } catch (Exception e) {
            page.locator(registerButton).click(new Locator.ClickOptions().setForce(true));
        }
    }

    public boolean isSuccess() {
        try {
             page.waitForURL(url -> url.contains("form-confirmation"), new Page.WaitForURLOptions().setTimeout(5000));
             return true;
        } catch (Exception e) {
             return false;
        }
    }

    public boolean isValidationErrorDisplayed(String fieldName) {
        try {
            page.waitForSelector(".invalid-feedback", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2000));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
