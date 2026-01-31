package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.LoginPage;
import com.framework.utils.ConfigManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("I launch the browser and navigate to the login page")
    public void iLaunchTheBrowserAndNavigateToTheLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateTo();
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String usernameKey, String passwordKey) {
        String username = ConfigManager.getProperty(usernameKey);
        if (username == null) {
            username = usernameKey;
        }

        String password = ConfigManager.getProperty(passwordKey);
        if (password == null) {
            password = passwordKey;
        }

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click the Login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the secure page")
    public void iShouldBeRedirectedToTheSecurePage() {
        Assertions.assertTrue(loginPage.getCurrentUrl().contains("/secure"),
                "User was not redirected to secure page. Current URL: " + loginPage.getCurrentUrl());
    }

    @Then("I should see the success message {string}")
    public void iShouldSeeTheSuccessMessage(String expectedMessage) {
        Assertions.assertTrue(loginPage.getFlashMessage().contains(expectedMessage),
                "Expected message to contain: " + expectedMessage + ", but got: " + loginPage.getFlashMessage());
    }

    @Then("I should see the Logout button")
    public void iShouldSeeTheLogoutButton() {
        Assertions.assertTrue(loginPage.isLogoutButtonDisplayed(), "Logout button is not displayed");
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        Assertions.assertTrue(loginPage.getFlashMessage().contains(expectedMessage),
                "Expected message to contain: " + expectedMessage + ", but got: " + loginPage.getFlashMessage());
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        Assertions.assertTrue(loginPage.getCurrentUrl().contains("/login"),
                "User did not remain on login page. Current URL: " + loginPage.getCurrentUrl());
    }
}
