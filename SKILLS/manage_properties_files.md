---
name: manage-properties-files
description: Define and manage configuration values in properties files.
version: 1.0.0
dependencies: []
tags:
  - configuration
  - properties
  - test-data
---

# Manage Properties Files

This skill defines the process for managing configuration and test data using Java properties files, primarily `config.properties`.

## When to Use This Skill

Use when:

- Adding new URLs for test environments.
- Defining test data (e.g., usernames, passwords, default inputs).
- Configuring browser settings or timeout values.
- Storing locators (if using a properties-based locator strategy, though Page Objects are preferred for this).

---

## Core Capabilities

1. **Configuration Management**: Centralizing environment-specific settings.
2. **Test Data Externalization**: Separating data from test logic.
3. **Key-Value Retrieval**: Accessing values via `ConfigManager`.

---

## Workflow / Process

### Phase 1: Identify Configuration Need

1. Determine if the value is static (hardcoded) or dynamic (configurable).
2. Check if the value is environment-specific.
3. Ensure the value isn't already defined.

### Phase 2: Update `config.properties`

1. Open `src/test/resources/config.properties`.
2. Add a new key-value pair.
    - Format: `key.name=value`
    - Group related keys (e.g., `login.url`, `login.username`).
3. Save the file.

### Phase 3: Access in Code

1. Use `ConfigManager.getProperty("key.name")` to retrieve the value.
2. Handle potential null values if necessary (though `ConfigManager` should handle file loading).

---

## Outputs & Deliverables

- **Primary Output**: Updated `src/test/resources/config.properties`.
- **Secondary Output**: Usage of the key in Java code.
- **Success Criteria**: The application reads the correct value during test execution.

---

## Standards & Best Practices

### For Naming Conventions

- Use dot notation for hierarchy (e.g., `page.login.title`).
- Use lowercase for keys.
- Be descriptive but concise.

### For Security

- **Avoid committing real secrets/passwords**. Use placeholders or environment variables for sensitive data in real projects. (Note: The current `config.properties` contains example passwords, which is acceptable for this practice repo).

---

## Integration Points

| Phase | Input From | Output To | Context |
|-------|-----------|-----------|---------|
| Configuration | Test Requirement | Write Step Definitions | Steps use config values for data/URLs. |

---

## Constraints

**Technical Constraints:**
- Standard Java Properties file format (`ISO-8859-1` encoding typically, but `ConfigManager` might handle UTF-8 if implemented).
- No duplicate keys (last one wins).

**Scope Constraints:**
- In Scope: Global configuration, static test data.
- Out of Scope: Dynamic runtime data generation.

---

## Common Pitfalls

- **Pitfall**: Typo in key name.
    - **Avoidance**: Copy-paste keys or use constants.
- **Pitfall**: Leaving trailing spaces in values.
    - **Avoidance**: Trim values in code or be careful when editing.

---

## Dependencies

- **Related Skills**: `write-step-definitions`, `create-feature-files`
