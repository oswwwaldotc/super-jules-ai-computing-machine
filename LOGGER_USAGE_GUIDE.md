# Logger Utility Usage Guide

## Overview

The `LoggerUtil` class has been added to the framework to provide centralized logging functionality for local debugging. All logs are stored in `logs/app.log` and also displayed in the console output.

## Logger Configuration

The logger is configured via `src/test/resources/log4j2.xml` with the following settings:

- **Console Output**: Displays logs with timestamp and level
- **File Output**: Stores logs in `logs/app.log` with detailed formatting
- **Default Level**: INFO
- **Appenders**: Both Console and File

## Available Logger Methods

### 1. Info Logging
```java
LoggerUtil.logInfo("User successfully logged in");
LoggerUtil.logInfo("Test step completed: {}", stepName);
```

### 2. Warning Logging
```java
LoggerUtil.logWarn("Element not found, retrying...");
LoggerUtil.logWarn("Expected value: {}, but got: {}", expected, actual);
```

### 3. Error Logging
```java
LoggerUtil.logError("Test failed with error message");
LoggerUtil.logError("Exception occurred during test", exception);
LoggerUtil.logError("Error: {}", errorMessage);
```

### 4. Debug Logging
```java
LoggerUtil.logDebug("Clicking element with locator: {}", locator);
LoggerUtil.logDebug("Current page title: {}", pageTitle);
```

### 5. Step-Specific Logging
```java
LoggerUtil.logStepStart("Login Step");
// ... perform step actions ...
LoggerUtil.logStepEnd("Login Step");
```

### 6. Test Event Logging
```java
LoggerUtil.logTestEvent("LOGIN_SUCCESSFUL", "User logged in successfully");
LoggerUtil.logTestEvent("PAGE_LOAD", "Home page loaded in 2.5 seconds");
```

### 7. Thread Context Logging
```java
// Set context value to be included in all logs for this thread
LoggerUtil.setThreadContext("TestName", "LoginTest");
LoggerUtil.setThreadContext("Browser", "Chrome");

// Clear specific context
LoggerUtil.clearThreadContext("TestName");

// Clear all context
LoggerUtil.clearAllThreadContext();
```

## Usage Examples

### Example 1: Basic Step with Logging
```java
@When("User enters credentials")
public void userEntersCredentials(String username, String password) {
    LoggerUtil.logStepStart("Enter credentials");
    try {
        loginPage.enterUsername(username);
        LoggerUtil.logDebug("Username entered: {}", username);
        
        loginPage.enterPassword(password);
        LoggerUtil.logDebug("Password entered");
        
        LoggerUtil.logInfo("Credentials entered successfully");
        LoggerUtil.logStepEnd("Enter credentials");
    } catch (Exception e) {
        LoggerUtil.logError("Failed to enter credentials", e);
        throw e;
    }
}
```

### Example 2: Assertion with Logging
```java
@Then("Login should be successful")
public void loginShouldBeSuccessful() {
    LoggerUtil.logStepStart("Verify login success");
    try {
        String expectedMessage = "Welcome";
        String actualMessage = homePage.getWelcomeMessage();
        
        LoggerUtil.logDebug("Expected message: {}", expectedMessage);
        LoggerUtil.logDebug("Actual message: {}", actualMessage);
        
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
        LoggerUtil.logInfo("Login verification passed");
        LoggerUtil.logStepEnd("Verify login success");
    } catch (AssertionError e) {
        LoggerUtil.logError("Login verification failed", e);
        throw e;
    }
}
```

### Example 3: Error Handling with Logging
```java
@When("I submit the form")
public void submitForm() {
    LoggerUtil.logStepStart("Submit form");
    try {
        formPage.submitForm();
        LoggerUtil.logInfo("Form submitted successfully");
        LoggerUtil.logStepEnd("Submit form");
    } catch (TimeoutException e) {
        LoggerUtil.logError("Form submission timeout - element not clickable", e);
        throw e;
    } catch (Exception e) {
        LoggerUtil.logError("Unexpected error during form submission", e);
        throw e;
    }
}
```

## Log Levels Explained

| Level | Usage | Example |
|-------|-------|---------|
| **DEBUG** | Detailed technical information | Variable values, method calls, element locators |
| **INFO** | General informational messages | Step completion, successful actions, important events |
| **WARN** | Warning messages | Deprecated usage, unexpected but recoverable conditions |
| **ERROR** | Error messages | Test failures, exceptions, critical issues |

## Viewing Logs

### Local File
Navigate to `logs/app.log` to view the complete log history for the current test run.

### Console Output
Logs are also printed to the console in real-time during test execution.

### Log Format
```
2026-02-02 14:30:45.123 [main] INFO  com.framework.steps.LoginSteps - ========== STARTING STEP: Login Step ==========
2026-02-02 14:30:45.456 [main] DEBUG com.framework.steps.LoginSteps - Username entered: testuser
2026-02-02 14:30:46.789 [main] INFO  com.framework.steps.LoginSteps - Login successful
2026-02-02 14:30:46.901 [main] INFO  com.framework.steps.LoginSteps - ========== STEP COMPLETED: Login Step ==========
```

## Best Practices

1. **Always log step start and end** using `logStepStart()` and `logStepEnd()`
2. **Log before and after critical actions** to trace execution flow
3. **Use debug level** for variable values and detailed technical info
4. **Use info level** for step completion and important events
5. **Always log exceptions** with context information
6. **Use formatted messages** with placeholders instead of string concatenation
7. **Set thread context** at the beginning of test execution for better traceability

## Integration with Other Steps

The logger has been integrated into:
- `ExampleSteps.java` - All test steps now use LoggerUtil

To integrate into other step files, simply:
1. Import: `import com.framework.utils.LoggerUtil;`
2. Add logging calls to your step methods following the patterns shown above

## Troubleshooting

### Logs not appearing in file
- Verify `logs/` directory exists in project root
- Check Log4j2 configuration in `src/test/resources/log4j2.xml`
- Ensure proper write permissions for the logs directory

### Performance impact
- Logging has minimal performance impact
- Debug level logging is only enabled when configured
- Consider using appropriate log levels in production environments

### Clearing logs
- Logs are appended to `logs/app.log` by default
- Delete the log file manually to start fresh, or configure rotation in log4j2.xml
