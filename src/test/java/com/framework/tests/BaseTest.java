package com.framework.tests;

import com.framework.driver.DriverFactory;
import com.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        logger.info("Initializing Driver...");
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
        logger.info("Driver Initialized.");
    }

    @AfterEach
    public void tearDown() {
        logger.info("Quitting Driver...");
        DriverManager.quitDriver();
        logger.info("Driver Quitted.");
    }
}
