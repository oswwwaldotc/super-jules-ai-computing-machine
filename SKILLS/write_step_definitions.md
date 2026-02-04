---
name: write-step-definitions
description: Implement executable steps for Cucumber scenarios using Java and Playwright.
version: 1.0.0
dependencies:
  - create-feature-files
  - manage-properties-files
tags:
  - java
  - cucumber
  - playwright
  - automation
---

# Write Step Definitions

This skill defines the process for implementing the "glue code" that connects Gherkin steps in feature files to Java code that interacts with the application.

## When to Use This Skill

Use when:

- Implementing new scenarios defined in feature files.
- Updating existing steps due to UI changes.
- Adding assertions to verify test outcomes.

---

## Core Capabilities

1. **Step Mapping**: Linking `@Given`, `@When`, `@Then` annotations to Java methods.
2. **Page Object Interaction**: Using Page Object classes to interact with the UI.
3. **Assertion**: Verifying expected results using JUnit 5 assertions.

---

## Workflow / Process

### Phase 1: Generate Snippets

1. Run the feature file (e.g., via `mvn test` or IDE).
2. Identify undefined steps in the console output.
3. Copy the suggested code snippets.

### Phase 2: Create/Update Step Class

1. Locate or create a class in `src/test/java/com/framework/steps`. Naming convention: `FeatureNameSteps.java`.
2. Paste the snippets.
3. Import necessary annotations from `io.cucumber.java.en`.

### Phase 3: Implement Logic

1. Instantiate Page Objects (e.g., `LoginPage loginPage = new LoginPage(DriverManager.getDriver())`).
2. Use `ConfigManager` to retrieve data if needed.
3. Call methods on the Page Object to perform actions.
4. Add assertions using `org.junit.jupiter.api.Assertions`.

---

## Outputs & Deliverables

- **Primary Output**: Java class in `src/test/java/com/framework/steps`.
- **Success Criteria**: The feature file runs without "Undefined Step" errors and the steps perform the intended actions.
- **Quality Gate**: Code passes static analysis (if any) and peer review.

---

## Standards & Best Practices

### For Step Definitions

- Keep steps simple. Logic should reside in Page Objects.
- Use dependency injection or thread-local drivers (provided by `DriverManager`) for state management.
- Reuse steps where possible (e.g., "I am on the login page").

### For Naming

- Class names: `FeatureNameSteps`.
- Method names: camelCase, descriptive of the step.

---

## Integration Points

| Phase | Input From | Output To | Context |
|-------|-----------|-----------|---------|
| Implementation | Create Feature Files | Test Execution | Steps make features executable. |

---

## Constraints

**Technical Constraints:**
- Must use Java.
- Must use Cucumber annotations.
- Must assert using JUnit 5.

**Scope Constraints:**
- In Scope: Orchestrating page interactions and assertions.
- Out of Scope: Direct driver manipulation (should be in Page Objects or Driver Factory), complex business logic.

---

## Common Pitfalls

- **Pitfall**: Hardcoding test data in steps.
    - **Avoidance**: Use `ConfigManager` or Scenario Examples.
- **Pitfall**: Placing complex logic in steps.
    - **Avoidance**: Move logic to Page Object methods.

---

## Dependencies

- **Depends On**: `create-feature-files`
- **Related Skills**: `manage-properties-files`
