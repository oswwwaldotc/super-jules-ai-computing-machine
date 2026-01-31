package com.framework.steps;

import com.framework.driver.DriverManager;
import com.framework.pages.ExamplePage;
import com.framework.utils.ConfigManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
}
