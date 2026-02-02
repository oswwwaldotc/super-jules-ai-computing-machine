package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.FormValidationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class FormValidationSteps {

    private FormValidationPage formValidationPage;

    @Given("I navigate to the Form Validation page")
    public void iNavigateToTheFormValidationPage() {
        formValidationPage = new FormValidationPage(DriverManager.getDriver());
        formValidationPage.navigateTo();
    }

    @When("I enter contact name {string}")
    public void iEnterContactName(String name) {
        formValidationPage.enterContactName(name);
    }

    @When("I enter contact number {string}")
    public void iEnterContactNumber(String number) {
        formValidationPage.enterContactNumber(number);
    }

    @When("I enter pickup date {string}")
    public void iEnterPickupDate(String date) {
        formValidationPage.enterPickupDate(date);
    }

    @When("I select payment method {string}")
    public void iSelectPaymentMethod(String method) {
        formValidationPage.selectPaymentMethod(method);
    }

    @When("I click the Register button")
    public void iClickTheRegisterButton() {
        formValidationPage.clickRegister();
    }

    @Then("I should be redirected to the confirmation page")
    public void iShouldBeRedirectedToTheConfirmationPage() {
        Assertions.assertTrue(formValidationPage.isSuccess(), "Not redirected to confirmation page");
    }

    @When("I leave all fields empty")
    public void iLeaveAllFieldsEmpty() {
        formValidationPage.enterContactName("");
        formValidationPage.enterContactNumber("");
        formValidationPage.enterPickupDate("");
        // Select logic might need empty or default
    }

    @Then("I should see validation errors")
    public void iShouldSeeValidationErrors() {
        Assertions.assertTrue(formValidationPage.isValidationErrorDisplayed("any"), "Validation errors not displayed");
    }
}
