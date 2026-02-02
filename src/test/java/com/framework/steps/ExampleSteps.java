package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.ExamplePage;
import com.framework.utils.ConfigManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ExampleSteps {

    private ExamplePage examplePage;

    @Given("I navigate to the Example Domain page")
    public void iNavigateToTheExampleDomainPage() {
        examplePage = new ExamplePage(DriverManager.getDriver());
        examplePage.navigateTo();
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String configKey) {
        String expectedTitle = ConfigManager.getProperty(configKey);
        Assertions.assertEquals(expectedTitle, examplePage.getPageTitle());
    }

    @Then("the header text should be {string}")
    public void theHeaderTextShouldBe(String configKey) {
        String expectedHeader = ConfigManager.getProperty(configKey);
        Assertions.assertEquals(expectedHeader, examplePage.getHeaderText());
    }

    @Then("the {string} link should contain {string}")
    public void theLinkShouldContain(String linkName, String configKey) {
        String expectedText = ConfigManager.getProperty(configKey);
        Assertions.assertTrue(examplePage.getMoreInformationLinkText().contains(expectedText),
                "Link text was: " + examplePage.getMoreInformationLinkText());
    }

    @When("I click on the {string} link")
    public void iClickOnTheLink(String linkName) {
        examplePage.clickMoreInformationLink();
    }

    @Then("I should be redirected to {string}")
    public void iShouldBeRedirectedTo(String configKey) {
        String expectedUrl = ConfigManager.getProperty(configKey);
        if (expectedUrl == null) {
            expectedUrl = configKey;
        }
        Assertions.assertTrue(examplePage.getCurrentUrl().contains(expectedUrl),
                "Expected URL to contain: " + expectedUrl + ", but got: " + examplePage.getCurrentUrl());
    }

    @Then("the new page title should be {string}")
    public void theNewPageTitleShouldBe(String expectedTitle) {
        String actualTitle = examplePage.getPageTitle();
        Assertions.assertEquals(expectedTitle, actualTitle,
                "Expected title: " + expectedTitle + ", but got: " + actualTitle);
    }

    @Then("the new page URL should be {string}")
    public void theNewPageURLShouldBe(String configKey) {
        String expectedUrl = ConfigManager.getProperty(configKey);
        if (expectedUrl == null) {
            expectedUrl = configKey;
        }
        Assertions.assertTrue(examplePage.getCurrentUrl().contains(expectedUrl),
                "Expected URL to contain: " + expectedUrl + ", but got: " + examplePage.getCurrentUrl());
    }

    @Then("the new page should contain the text {string}")
    public void theNewPageShouldContainTheText(String expectedText) {
        Assertions.assertTrue(examplePage.getPageContent().contains(expectedText),
                "Expected page to contain: " + expectedText);
    }

    @Then("I should not find an element with the locator {string}")
    public void iShouldNotFindAnElementWithTheLocator(String locatorKey) {
        boolean elementNotFound = !examplePage.isElementPresent(locatorKey);
        Assertions.assertTrue(elementNotFound,
                "Expected element with locator " + locatorKey + " to not be present, but it was found");
    }

    @Then("an appropriate error message should be logged indicating the element was not found")
    public void anAppropriateErrorMessageShouldBeLoggedIndicatingTheElementWasNotFound() {
        // This would be implemented to verify logs contain the expected error message
        // Implementation depends on logging framework being used
    }

    @Then("the test should continue without failing")
    public void theTestShouldContinueWithoutFailing() {
        // This step verifies the test execution continues without interruption
        // This is implicitly verified by test not throwing exceptions
    }

    @Then("the page should load within {string} seconds")
    public void thePageShouldLoadWithinSeconds(String configKey) {
        String loadTimeString = ConfigManager.getProperty(configKey);
        if (loadTimeString == null) {
            loadTimeString = configKey;
        }
        long maxLoadTimeMs = Long.parseLong(loadTimeString) * 1000;
        long actualLoadTimeMs = examplePage.getPageLoadTime();
        Assertions.assertTrue(actualLoadTimeMs <= maxLoadTimeMs,
                "Page load time: " + actualLoadTimeMs + "ms exceeded maximum: " + maxLoadTimeMs + "ms");
    }

    @Then("a log entry should be created with the actual load time")
    public void aLogEntryShouldBeCreatedWithTheActualLoadTime() {
        long loadTime = examplePage.getPageLoadTime();
        // Log the actual page load time for reference
        System.out.println("Page load time: " + loadTime + "ms");
    }
}
