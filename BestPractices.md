# 🛠️ Coding Best Practices Guide

This document outlines general coding conventions and best practices to follow across the **Employee Management Portal** project (both frontend and backend). Adhering to these practices ensures code readability, consistency, and maintainability for all contributors.

---

## 📌 Naming Conventions

- ✅ **Functions**: Use `camelCase`  
  Example: `getUserProfile()`

- ✅ **Variables**: Use `camelCase`  
  Example: `employeeId`, `userList`

- ✅ **Classes**: Use `PascalCase`  
  Example: `UserService`, `EmployeeController`

- ✅ **Constants**: Use `UPPER_SNAKE_CASE`  
  Example: `MAX_LOGIN_ATTEMPTS`, `DEFAULT_ROLE`

- ✅ **Files**:
    - Java files: match class name, e.g., `UserService.java`
    - React components: `PascalCase`, e.g., `EmployeeCard.jsx`
    - Configs & assets: `kebab-case`, e.g., `app-config.js`

---

## 🔁 Code Structure & Design

- Group related logic into reusable services or components.
- Use DTOs (Data Transfer Objects) to pass data in controllers.
- Avoid large functions (>50 lines); break into smaller functions.
- Separate concerns: controller → service → repository layers.
- Keep function responsibilities narrow and well-defined.

---

## 🔒 Security Practices

- Never log or print sensitive data (like passwords, tokens).
- Use environment variables for secrets (API keys, DB credentials).
- Validate all user inputs (`@Valid`, frontend validation too).
- Use HTTPS in production environments.

---

## 💡 Frontend Best Practices

- Use functional components and React hooks.
- Keep component files short and focused.
- Manage state properly (avoid prop drilling; use context or Redux if needed).
- Use `axios` or `fetch` in a service layer, not directly inside components.
- Validate forms on both frontend and backend.

---

## 🧪 Testing Guidelines

- Write unit tests for services and utilities.
- Use integration tests for APIs (controllers + service).
- Mock external dependencies using Mockito (backend) or MSW/Jest (frontend).

---

## 🧹 Clean Code Tips

- Avoid deeply nested conditionals.
- Use meaningful variable and function names.
- Avoid magic numbers or strings – use constants.
- Add Javadoc or comments only where logic isn't self-explanatory.
- Don’t leave unused or commented-out code in commits.

---

## 📝 Git & Workflow

- Create feature branches with descriptive names: `feature/login-api`, `fix/navbar-bug`
- Write clear commit messages: `fix: resolve null pointer in login API`
- Always pull before pushing.
- Open pull requests with proper description, screenshots, and references.

---

## ✅ Example Commit Message Format

```bash
feat: add attendance submission API
fix: correct time formatting bug in Timesheet UI
docs: update README with setup steps
test: add unit tests for PayrollService
refactor: simplify leave request validation logic
```

---

## 🤝 Code Review Checklist

- Code follows naming and structure conventions.
- No hardcoded secrets or credentials.
- Proper error handling is implemented.
- Tests are added/updated.
- Code is readable and maintainable.
- UI changes are tested on all screen sizes (if applicable).

---

Let's keep the code clean, readable, and scalable! 🚀