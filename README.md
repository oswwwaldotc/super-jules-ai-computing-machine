# Super Jules AI Computing Machine - Test Automation Framework

## Overview
This repository contains a robust Test Automation Framework built using Java and Selenium WebDriver. It is designed to be scalable, maintainable, and easy to use for web application testing. The framework follows the Page Object Model (POM) design pattern and utilizes the Factory Pattern for WebDriver management.

## Technologies Used
*   **Java 21**: The programming language used for the framework.
*   **Selenium 4.28.0**: For web browser automation.
*   **JUnit 5.11.0**: The testing framework for defining and running tests.
*   **Allure 2.25.0**: For generating comprehensive test execution reports.
*   **Log4j2 2.23.1**: For logging framework activities and test steps.
*   **Maven**: For dependency management and build automation.

## Project Structure
The project is organized as follows:

*   `src/main/java/com/framework/driver`: Contains `DriverFactory` and `DriverManager` for handling WebDriver instances (thread-safe implementation).
*   `src/main/java/com/framework/pages`: Contains Page Object classes representing web pages.
*   `src/test/java/com/framework/tests`: Contains the actual test classes (e.g., `BaseTest`, `ExampleTest`).

## Prerequisites
Before running the tests, ensure you have the following installed:
*   **Java JDK 21**
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

## Reporting
This framework integrates with Allure for reporting.

### View Allure Report
To generate and serve the report locally after running tests:
```bash
mvn allure:serve
```

To generate the report in `target/site/allure-maven-plugin`:
```bash
mvn allure:report
```

## Logging
Logs are generated using Log4j2. Configuration can be found (or added) in `src/main/resources/log4j2.xml`. Logs typically output to the console and/or a log file in the root directory.
