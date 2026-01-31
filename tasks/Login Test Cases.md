---
"creation-date": "2026-01-31T16:10:00-06:00"
tags:
  - "clippings"
---

# Login Test Cases

To perform these tests, please navigate to this [page](https://practice.expandtesting.com/login)

## Login Automation Test Cases

### Test Case 1: Successful Login

1. Launch the browser.
2. Navigate to the [login](https://practice.expandtesting.com/login) page URL.
3. Verify that the login page is displayed successfully.
4. Enter **Username**: `practice`.
5. Enter **Password**: `SuperSecretPassword!`.
6. Click the **Login** button.
7. Verify that the user is redirected to the `/secure` page.
8. Confirm the success message "You logged into a secure area!" is visible.
9. Verify that a **Logout** button is displayed.

---

### Test Case 2: Invalid Username

1. Launch the browser.
2. Navigate to the login page URL.
3. Verify that the login page is displayed successfully.
4. Enter an incorrect **Username** (e.g., `wrongUser`).
5. Enter **Password**: `SuperSecretPassword!`.
6. Click the **Login** button.
7. Verify that an error message "Invalid username." is displayed.
8. Ensure the user remains on the login page.

---

### Test Case 3: Invalid Password

1. Launch the browser.
2. Navigate to the login page URL.
3. Verify that the login page is displayed successfully.
4. Enter **Username**: `practice`.
5. Enter an incorrect **Password** (e.g., `WrongPassword`).
6. Click the **Login** button.
7. Verify that an error message "Invalid password." is displayed.
8. Ensure the user remains on the login page.

# References
- https://practice.expandtesting.com/test-cases/login
- Retrieved from Obsidian Web Clipper 🪨