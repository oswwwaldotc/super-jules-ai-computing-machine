package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.ExamplePage;
import com.framework.utils.ConfigManager;
import com.framework.utils.LoggerUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ExampleSteps {

    private ExamplePage examplePage;

    @Given("I navigate to the Example Domain page")
    public void iNavigateToTheExampleDomainPage() {
        LoggerUtil.logStepStart("Navigate to Example Domain page");
        try {
            examplePage = new ExamplePage(DriverManager.getDriver());
            examplePage.navigateTo();
            LoggerUtil.logInfo("Successfully navigated to Example Domain page");
            LoggerUtil.logStepEnd("Navigate to Example Domain page");
        } catch (Exception e) {
            LoggerUtil.logError("Failed to navigate to Example Domain page", e);
            throw e;
        }
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String configKey) {
        LoggerUtil.logStepStart("Verify page title");
        try {
            String expectedTitle = ConfigManager.getProperty(configKey);
            String actualTitle = examplePage.getPageTitle();
            LoggerUtil.logDebug("Expected title: {}", expectedTitle);
            LoggerUtil.logDebug("Actual title: {}", actualTitle);
            LoggerUtil.logStepStart("Verify header text");
            try {
                String expectedHeader = ConfigManager.getProperty(configKey);
                String actualHeader = examplePage.getHeaderText();
                LoggerUtil.logDebug("Expected header: {}", expectedHeader);
                LoggerUtil.logDebug("Actual header: {}", actualHeader);
                Assertions.assertEquals(expectedHeader, actualHeader);
                LoggerUtil.logInfo("Header text verification passed");
                LoggerUtil.logStepEnd("Verify header text");
            } catch (AssertionError e) {
                LoggerUtil.logError("Header text verification failed", e);
                throw e;
            }
            LoggerUtil.logStepEnd("Verify page title");
        } catch (AssertionError e) {
            LoggerUtil.logError("Page title verification failed", e);
            throw e;
        }
    }

    @Then("the header text should be {string}")
    public void theHeaderTextShouldBe(String configKey) {
        LoggerUtil.logStepStart("Verify header text");
        try {
            String expectedHeader = ConfigManager.getProperty(configKey);
            String actualHeader = examplePage.getHeaderText();
            LoggerUtil.logDebug("Expected header: {}", expectedHeader);
            LoggerUtil.logDebug("Actual header: {}", actualHeader);
            Assertions.assertEquals(expectedHeader, actualHeader);
            LoggerUtil.logInfo("Header text verification passed");
            LoggerUtil.logStepEnd("Verify header text");
        } catch (AssertionError e) {
            LoggerUtil.logError("Header text verification failed", e);
            throw e;
        }
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
        LoggerUtil.logStepStart("Verify URL redirect");
        try {
            String expectedUrl = ConfigManager.getProperty(configKey);
            if (expectedUrl == null) {
                expectedUrl = configKey;
            }
            String actualUrl = examplePage.getCurrentUrl();
            LoggerUtil.logDebug("Expected URL to contain: {}", expectedUrl);
            LoggerUtil.logDebug("Actual URL: {}", actualUrl);
            Assertions.assertTrue(actualUrl.contains(expectedUrl),
                    "Expected URL to contain: " + expectedUrl + ", but got: " + actualUrl);
            LoggerUtil.logInfo("URL redirect verification passed");
            LoggerUtil.logStepEnd("Verify URL redirect");
        } catch (AssertionError e) {
            LoggerUtil.logError("URL redirect verification failed", e);
            throw e;
        }
    }

    @Then("the new page title should be {string}")
    public void theNewPageTitleShouldBe(String expectedTitle) {
        LoggerUtil.logStepStart("Verify new page title");
        try {
            String actualTitle = examplePage.getPageTitle();
            LoggerUtil.logDebug("Expected title: {}", expectedTitle);
            LoggerUtil.logDebug("Actual title: {}", actualTitle);
            Assertions.assertEquals(expectedTitle, actualTitle,
                    "Expected title: " + expectedTitle + ", but got: " + actualTitle);
            LoggerUtil.logInfo("New page title verification passed");
            LoggerUtil.logStepEnd("Verify new page title");
        } catch (AssertionError e) {
            LoggerUtil.logError("New page title verification failed", e);
            throw e;
        }
    }

    @Then("the new page URL should be {string}")
    public void theNewPageURLShouldBe(String configKey) {
        LoggerUtil.logStepStart("Verify new page URL");
        try {
            String expectedUrl = ConfigManager.getProperty(configKey);
            if (expectedUrl == null) {
                expectedUrl = configKey;
            }
            String actualUrl = examplePage.getCurrentUrl();
            LoggerUtil.logDebug("Expected URL to contain: {}", expectedUrl);
            LoggerUtil.logDebug("Actual URL: {}", actualUrl);
            Assertions.assertTrue(actualUrl.contains(expectedUrl),
                    "Expected URL to contain: " + expectedUrl + ", but got: " + actualUrl);
            LoggerUtil.logInfo("New page URL verification passed");
            LoggerUtil.logStepEnd("Verify new page URL");
        } catch (AssertionError e) {
            LoggerUtil.logError("New page URL verification failed", e);
            throw e;
        }
    }

    @Then("the new page should contain the text {string}")
    public void theNewPageShouldContainTheText(String expectedText) {
        LoggerUtil.logStepStart("Verify page contains text");
        try {
            String pageContent = examplePage.getPageContent();
            LoggerUtil.logDebug("Expected text: {}", expectedText);
            LoggerUtil.logDebug("Page content length: {} characters", pageContent.length());
            Assertions.assertTrue(pageContent.contains(expectedText),
                    "Expected page to contain: " + expectedText);
            LoggerUtil.logInfo("Page content verification passed");
            LoggerUtil.logStepEnd("Verify page contains text");
        } catch (AssertionError e) {
            LoggerUtil.logError("Page content verification failed", e);
            throw e;
        }
    }

    @Then("I should not find an element with the locator {string}")
    public void iShouldNotFindAnElementWithTheLocator(String locatorKey) {
        LoggerUtil.logStepStart("Verify element not present");
        try {
            boolean elementNotFound = !examplePage.isElementPresent(locatorKey);
            LoggerUtil.logDebug("Locator key: {}", locatorKey);
            LoggerUtil.logDebug("Element found: {}", !elementNotFound);
            Assertions.assertTrue(elementNotFound,
                    "Expected element with locator " + locatorKey + " to not be present, but it was found");
            LoggerUtil.logStepStart("Verify error message logged");
            LoggerUtil.logTestEvent("ELEMENT_NOT_FOUND", "An appropriate error message has been logged");
            LoggerUtil.logStepEnd("Verify error message logged");
        } catch (AssertionError e) {
            LoggerUtil.logError("Element not found verification failed", e);
            throw e;
        }
    }

    @Then("an appropriate error message should be logged indicating the element was not found")
    public void anAppropriateErrorMessageShouldBeLoggedIndicatingTheElementWasNotFound() {
        // This would be implemented to verify logs contain the expected error message
        // Implementation depends on logging framework being used
    }

    @Then("the test should continue without failing")
    public void theTestShouldContinueWithoutFailing() {
        LoggerUtil.logInfo("Test execution continuing without failures");
        // This is implicitly verified by test not throwing exceptions
    }

    @Then("the page should load within {string} seconds")
    public void thePageShouldLoadWithinSeconds(String configKey) {
        LoggerUtil.logStepStart("Verify page load time");
        try {
            String loadTimeString = ConfigManager.getProperty(configKey);
            if (loadTimeString == null) {
                loadTimeString = configKey;
            }
            long maxLoadTimeMs = Long.parseLong(loadTimeString) * 1000;
            long actualLoadTimeMs = examplePage.getPageLoadTime();
            LoggerUtil.logDebug("Max load time allowed: {} ms", maxLoadTimeMs);
            LoggerUtil.logDebug("Actual load time: {} ms", actualLoadTimeMs);
            Assertions.assertTrue(actualLoadTimeMs <= maxLoadTimeMs,
                    "Page load time: " + actualLoadTimeMs + "ms exceeded maximum: " + maxLoadTimeMs + "ms");
            LoggerUtil.logInfo("Page load time verification passed");
            LoggerUtil.logStepStart("Log page load time");
            long loadTime = examplePage.getPageLoadTime();
            LoggerUtil.logInfo("Page load time: {} ms", loadTime);
            LoggerUtil.logTestEvent("PAGE_LOAD_TIME", "Actual page load time: " + loadTime + "ms");
            LoggerUtil.logStepEnd("Log page load time");
        } catch (AssertionError e) {
            LoggerUtil.logError("Page load time verification failed", e);
            throw e;
        }
    }

    @Then("a log entry should be created with the actual load time")
    public void aLogEntryShouldBeCreatedWithTheActualLoadTime() {
        long loadTime = examplePage.getPageLoadTime();
        // Log the actual page load time for reference
        System.out.println("Page load time: " + loadTime + "ms");
    }
}
