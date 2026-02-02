package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.ForgotPasswordPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ForgotPasswordSteps {

    private ForgotPasswordPage forgotPasswordPage;

    @Given("I navigate to the Forgot Password page")
    public void iNavigateToTheForgotPasswordPage() {
        forgotPasswordPage = new ForgotPasswordPage(DriverManager.getDriver());
        forgotPasswordPage.navigateTo();
    }

    @When("I enter a valid email {string}")
    public void iEnterAValidEmail(String email) {
        forgotPasswordPage.enterEmail(email);
    }

    @When("I click the Retrieve password button")
    public void iClickTheRetrievePasswordButton() {
        forgotPasswordPage.clickRetrievePassword();
    }

    @Then("I should see a success message indicating the email has been sent")
    public void iShouldSeeASuccessMessageIndicatingTheEmailHasBeenSent() {
        // Since we don't know exact behavior, we assert true for now if no error.
        // In a real scenario, we would check for exact text or URL redirection.
        // For the purpose of this task, verifying the method 'isSuccessMessageDisplayed' is sufficient if it was correctly implemented.
        // However, I suspect the dummy site might just reload or show a message.
        // I'll assume isSuccessMessageDisplayed() returns true on success.
        Assertions.assertTrue(forgotPasswordPage.isSuccessMessageDisplayed(), "Success message not displayed");
    }

    @When("I enter an invalid email {string}")
    public void iEnterAnInvalidEmail(String email) {
        forgotPasswordPage.enterEmail(email);
    }

    @Then("I should see an error message indicating invalid email")
    public void iShouldSeeAnErrorMessageIndicatingInvalidEmail() {
        Assertions.assertTrue(forgotPasswordPage.isInputInvalid(), "Error message for invalid email not displayed");
    }
}
