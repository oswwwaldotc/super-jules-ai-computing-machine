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

    @When("I leave the username and password fields empty")
    public void iLeaveTheUsernameAndPasswordFieldsEmpty() {
        // Fields are left empty by not entering anything
        // This is a placeholder step as the fields should already be empty on page load
    }

    @Given("I have successfully logged in with username {string} and password {string}")
    public void iHaveSuccessfullyLoggedInWithUsernameAndPassword(String usernameKey, String passwordKey) {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateTo();
        
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
        loginPage.clickLogin();
        
        // Verify login was successful
        Assertions.assertTrue(loginPage.getCurrentUrl().contains("/secure"),
                "Login failed. User was not redirected to secure page.");
    }

    @When("I click the Logout button")
    public void iClickTheLogoutButton() {
        loginPage.clickLogout();
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String expectedMessage) {
        Assertions.assertTrue(loginPage.getFlashMessage().contains(expectedMessage),
                "Expected message to contain: " + expectedMessage + ", but got: " + loginPage.getFlashMessage());
    }

    @Then("the Login button should be visible")
    public void theLoginButtonShouldBeVisible() {
        Assertions.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed");
    }

    @When("I select the Remember Me option")
    public void iSelectTheRememberMeOption() {
        loginPage.selectRememberMe();
    }

    @When("I close and reopen the browser")
    public void iCloseAndReopenTheBrowser() {
        // Close the current browser instance
        DriverManager.closeBrowser();
        
        // Reopen a new instance (driver will be reinitialized)
        // Note: This will need implementation in DriverManager to handle persistence
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("I navigate to the secure page")
    public void iNavigateToTheSecurePage() {
        loginPage.navigateTo("/secure");
    }

    @Then("I should still be logged in")
    public void iShouldStillBeLoggedIn() {
        Assertions.assertTrue(loginPage.getCurrentUrl().contains("/secure"),
                "User was not logged in. Current URL: " + loginPage.getCurrentUrl());
        Assertions.assertTrue(loginPage.isLogoutButtonDisplayed(), "Logout button is not displayed, indicating user is not logged in");
    }
}
