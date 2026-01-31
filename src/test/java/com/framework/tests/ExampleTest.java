package com.framework.tests;

import com.framework.driver.DriverManager;
import com.framework.pages.ExamplePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Feature("Example Domain Tests")
public class ExampleTest extends BaseTest {

    @Test
    @Description("Verify that the example domain page loads correctly")
    public void testExampleDomain() {
        ExamplePage examplePage = new ExamplePage(DriverManager.getDriver());
        examplePage.navigateTo();

        Assertions.assertEquals("Example Domain", examplePage.getPageTitle());
        Assertions.assertEquals("Example Domain", examplePage.getHeaderText());
        Assertions.assertTrue(examplePage.getMoreInformationLinkText().contains("Learn more"),
                "Link text was: " + examplePage.getMoreInformationLinkText());
    }
}
