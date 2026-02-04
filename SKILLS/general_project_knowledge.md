---
name: general-project-knowledge
description: Overview of the project architecture, tools, and standard workflows.
version: 1.0.0
dependencies: []
tags:
  - onboarding
  - architecture
  - documentation
---

# General Project Knowledge

This skill provides a high-level understanding of the test automation framework, its components, and how to work with it effectively.

## When to Use This Skill

Use when:

- Onboarding new team members.
- Understanding the folder structure and architectural decisions.
- Troubleshooting environment or build issues.
- Running tests and generating reports.

---

## Core Capabilities

1. **Architecture Understanding**: Page Object Model (POM), Cucumber BDD, Playwright.
2. **Build & Execution**: Using Maven for dependency management and test execution.
3. **Reporting**: Generating and viewing Allure reports.

---

## Workflow / Process

### Phase 1: Setup

1. Clone the repository.
2. Install dependencies: `mvn install -DskipTests`.
3. Ensure JDK 18+ is installed.

### Phase 2: Running Tests

1. Execute all tests: `mvn clean test`.
2. Execute specific tags: `mvn clean test -Dgroups="tag_name"`.

### Phase 3: Reporting

1. Generate report: `mvn allure:report`.
2. View report: `mvn allure:serve`.

---

## Outputs & Deliverables

- **Primary Output**: Knowledge and successful test execution.
- **Secondary Output**: Test reports (Allure).

---

## Standards & Best Practices

### Project Structure

- `src/main/java`: Utilities and core framework components (e.g., `ConfigManager`, `DriverFactory`).
- `src/test/java`: Step definitions, Runners, and Page Objects.
- `src/test/resources`: Feature files, configuration properties, logging config.

### Coding Standards

- Follow Java naming conventions.
- Keep methods small and focused.
- Log important actions using `Log4j2`.

---

## Integration Points

| Phase | Input From | Output To | Context |
|-------|-----------|-----------|---------|
| All | Repository | All Skills | Foundation for all other tasks. |

---

## Constraints

**Technical Constraints:**
- Java 18+.
- Maven 3.x.
- Playwright (managed by Maven).

**Scope Constraints:**
- In Scope: Framework architecture, build process, reporting.
- Out of Scope: Specific implementation details of individual features.

---

## Common Pitfalls

- **Pitfall**: "Port in use" errors during parallel execution or server restarts.
    - **Avoidance**: Clean up processes or use dynamic ports if possible.
- **Pitfall**: Browser version mismatch.
    - **Avoidance**: Playwright manages browser binaries, so ensure dependencies are up to date.

---

## Dependencies

- **Related Skills**: `create-feature-files`, `write-step-definitions`
