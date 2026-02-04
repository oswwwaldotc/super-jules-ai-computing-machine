---
name: create-feature-files
description: Create and organize Cucumber feature files using Gherkin syntax.
version: 1.0.0
dependencies: []
tags:
  - bdd
  - cucumber
  - gherkin
  - testing
---

# Create Feature Files

This skill defines the process for creating and maintaining Cucumber Feature files within the project. These files serve as the source of truth for test requirements and scenarios.

## When to Use This Skill

Use when:

- Defining requirements for a new feature.
- Adding new test scenarios to an existing feature.
- Refactoring existing scenarios for clarity or coverage.
- Translating user stories into executable specifications.

---

## Core Capabilities

1. **Gherkin Authoring**: Writing clear, concise scenarios using Given/When/Then syntax.
2. **Scenario Organization**: Structuring feature files logically within `src/test/resources/features`.
3. **Data-Driven Testing**: Utilizing Scenario Outlines and Examples tables for parameterized tests.

---

## Workflow / Process

### Phase 1: Requirement Analysis

1. Understand the user story or requirement.
2. Identify the key behaviors and acceptance criteria.
3. Determine if a new feature file is needed or if an existing one should be updated.

### Phase 2: Drafting the Feature File

1. Create a new `.feature` file in `src/test/resources/features` if necessary. Naming convention: PascalCase (e.g., `Login.feature`).
2. Define the `Feature` title and description.
3. Write `Scenario` or `Scenario Outline` blocks.
4. Use tags (e.g., `@smoke`, `@regression`) to categorize scenarios.

### Phase 3: Review and Refinement

1. Ensure steps are reusable and readable.
2. Verify that the Gherkin syntax is valid.
3. Check against existing steps to avoid duplication (see `write_step_definitions` skill).

---

## Outputs & Deliverables

- **Primary Output**: A `.feature` file located in `src/test/resources/features`.
- **Success Criteria**: The feature file is syntactically correct and clearly describes the expected behavior.
- **Quality Gate**: Peer review or self-review to ensure adherence to Gherkin best practices.

---

## Standards & Best Practices

### For Gherkin Syntax

- Use the declarative style (describe *what*, not *how*).
- Keep scenarios independent and atomic.
- Use `Background` for repeated setup steps.
- Use `Scenario Outline` for testing multiple data sets.

### For File Management

- Store all feature files in `src/test/resources/features`.
- Name files descriptively using PascalCase.

---

## Integration Points

| Phase | Input From | Output To | Context |
|-------|-----------|-----------|---------|
| Drafting | Requirements/User Stories | Write Step Definitions | Feature files drive the need for step definitions. |

---

## Constraints

**Technical Constraints:**
- Must be valid Gherkin syntax compatible with Cucumber JVM.
- Must be encoded in UTF-8.

**Scope Constraints:**
- In Scope: Defining test scenarios and test data in Examples.
- Out of Scope: Implementation of test logic (handled in Step Definitions).

---

## Common Pitfalls

- **Pitfall**: Writing imperative steps (e.g., "I click the button with ID 123").
    - **Avoidance**: Write declarative steps (e.g., "I submit the login form").
- **Pitfall**: Complex scenarios with too many steps.
    - **Avoidance**: Break down into smaller scenarios or use high-level steps.

---

## Dependencies

- **Related Skills**: `write-step-definitions`, `manage-properties-files`
