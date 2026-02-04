package com.framework.steps;

import com.framework.driver.DriverFactory;
import com.framework.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        logger.info("Initializing Driver...");
        DriverFactory.createPage();
        logger.info("Driver Initialized.");
    }

    @After
    public void tearDown() {
        logger.info("Quitting Driver...");
        DriverManager.quitDriver();
        logger.info("Driver Quitted.");
    }
}
