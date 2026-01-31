# Super Jules AI Computing Machine - Test Automation Framework

## Overview
This repository contains a robust Test Automation Framework built using Java and Selenium WebDriver. It is designed to be scalable, maintainable, and easy to use for web application testing. The framework follows the Page Object Model (POM) design pattern and utilizes the Factory Pattern for WebDriver management. It integrates Cucumber for BDD-style testing.

## Technologies Used
*   **Java 18**: The programming language used for the framework.
*   **Selenium 4.28.0**: For web browser automation.
*   **JUnit 5.11.0**: The testing framework for defining and running tests.
*   **Cucumber 7.15.0**: For behavior-driven development (BDD) testing.
*   **Allure 2.32.0**: For generating comprehensive test execution reports.
*   **Log4j2 2.23.1**: For logging framework activities and test steps.
*   **Maven 3.9+**: For dependency management and build automation.

## Project Structure
The project is organized as follows:

*   `src/main/java/com/framework/driver`: Contains `DriverFactory` and `DriverManager` for handling WebDriver instances (thread-safe implementation).
*   `src/main/java/com/framework/pages`: Contains Page Object classes representing web pages.
*   `src/main/java/com/framework/utils`: Utility classes like `ConfigManager`.
*   `src/test/java/com/framework/tests`: Contains the actual test classes (e.g., `BaseTest`, `ExampleTest`).
*   `src/test/java/com/framework/steps`: Contains Cucumber step definitions.
*   `src/test/java/com/framework/runners`: Contains Cucumber test runners.
*   `src/test/resources/features`: Contains Cucumber feature files.
*   `src/test/resources`: Configuration files (config.properties, log4j2.xml).

## Prerequisites
Before running the tests, ensure you have the following installed:
*   **Java JDK 18** or higher
*   **Maven** (version 3.9 or higher recommended)

## How to Run Tests
You can run the tests using Maven via the command line.

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn clean test -Dtest=ExampleTest
```

### Run Cucumber tests
Cucumber tests are automatically run as part of the `mvn clean test` command. Tests are discovered through the `CucumberTest` runner class.

## Reporting
This framework integrates with Allure for comprehensive test reporting.

### Generate Allure Report
To generate the Allure report after running tests:
```bash
mvn allure:report
```

The report will be generated in `target/site/allure-maven-plugin`.

### View Allure Report
To generate and serve the report locally in your browser:
```bash
mvn allure:serve
```

This will automatically open the report at `http://localhost:4040`.

### Combined Test and Report Generation
To run tests and generate the report in one command:
```bash
mvn clean test allure:report
```

## Configuration
### Environment Configuration
Application configuration is managed through `src/test/resources/config.properties`. Update this file with your test environment details (base URLs, credentials, etc.).

### Cucumber Configuration
Cucumber settings are configured in `src/test/resources/cucumber.properties`. This includes feature file location and output plugin settings.

## Logging
Logs are generated using Log4j2. Configuration can be found in `src/test/resources/log4j2.xml`. Logs output to:
*   Console: Real-time logging during test execution
*   File: Log file in the project root directory for detailed analysis

## Project Features
*   **Thread-Safe WebDriver Management**: Supports parallel test execution
*   **Page Object Model (POM)**: Maintainable and scalable test code structure
*   **Factory Pattern**: Flexible WebDriver instantiation
*   **BDD with Cucumber**: Human-readable test scenarios
*   **Comprehensive Reporting**: Detailed Allure reports with screenshots and logs
*   **Centralized Configuration**: Easy management of test environment settings
