# 1378. Replace Employee ID With The Unique Identifier
# EASY 
# LEETCODE

This document describes the structure, concepts, and solutions for the **Employee Unique ID lookup** problem, a classic SQL and data join exercise commonly found in coding interviews and platforms like LeetCode.

---

## Database Schema

### Table: Employees

| Column Name | Type    | Description                              |
|-------------|---------|------------------------------------------|
| id          | int     | Primary key, unique employee identifier  |
| name        | varchar | The name of the employee                 |

### Table: EmployeeUNI

| Column Name | Type    | Description                                     |
|-------------|---------|-------------------------------------------------|
| id          | int     | Employee ID (references Employees.id)           |
| unique_id   | int     | Unique universal identifier for the employee    |

- The primary key for **Employees** is `id`.
- The primary key for **EmployeeUNI** is a combination of `(id, unique_id)`.

---

## Problem Statement

> For each employee in the Employees table, display their corresponding `unique_id` from the EmployeeUNI table. If an employee does **not** have a unique id, show `null` for the `unique_id`.

- **The output must include every employee, even if they do not have a unique ID.**
- If an employee's `id` is not present in EmployeeUNI, their `unique_id` should be `null`.

---

## Example

### Input

#### Employees

| id | name     |
|----|----------|
| 1  | Alice    |
| 7  | Bob      |
| 11 | Meir     |
| 90 | Winston  |
| 3  | Jonathan |

#### EmployeeUNI

| id | unique_id |
|----|-----------|
| 3  | 1         |
| 11 | 2         |
| 90 | 3         |

### Output

| unique_id | name     |
|-----------|----------|
| null      | Alice    |
| null      | Bob      |
| 2         | Meir     |
| 3         | Winston  |
| 1         | Jonathan |

---

## Conceptual Approach

- The key concept is understanding **LEFT JOIN** (or LEFT OUTER JOIN).
- A **LEFT JOIN** returns all records from the left table (`Employees`), and the matched records from the right table (`EmployeeUNI`). If there is no match, the result is `null` on the right side.
- This technique ensures every employee is shown, even those without a unique id.

---

## SQL Solution

```sql
SELECT 
    EmployeeUNI.unique_id,
    Employees.name
FROM 
    Employees
LEFT JOIN 
    EmployeeUNI
ON 
    Employees.id = EmployeeUNI.id;
```

---

## Key Points to Remember

- **LEFT JOIN** is crucial for including all employees, even those with no unique id.
- If there is no unique id, the result will be `null` (SQL) or `NaN` (Pandas).
- The order of output does **not** matter unless specified.
- Carefully read the question: always check if you need to include all entries from the left table (Employees) regardless of matches.

---
