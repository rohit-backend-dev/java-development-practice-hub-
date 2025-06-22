# 📘 SQL Window Functions - Complete Guide with Test Tables & Examples

## 🔹 What Are Window Functions?

**Window functions** perform calculations across a set of table rows related to the current row, without collapsing rows into a single output like aggregate functions do.

---

## 🔹 Example Table

Let's use this sample table for all examples:

```sql
CREATE TABLE employees (
  id INT PRIMARY KEY,
  name VARCHAR(50),
  department VARCHAR(50),
  salary INT
);

INSERT INTO employees (id, name, department, salary) VALUES
(1, 'Alice',   'Engineering', 90000),
(2, 'Bob',     'Engineering', 85000),
(3, 'Carol',   'HR',          60000),
(4, 'Dave',    'HR',          70000),
(5, 'Eve',     'HR',          65000),
(6, 'Frank',   'Finance',     80000),
(7, 'Grace',   'Finance',     95000),
(8, 'Heidi',   'Engineering', 95000);
```

**Table Data:**

| id | name   | department   | salary |
|----|--------|--------------|--------|
| 1  | Alice  | Engineering  | 90000  |
| 2  | Bob    | Engineering  | 85000  |
| 3  | Carol  | HR           | 60000  |
| 4  | Dave   | HR           | 70000  |
| 5  | Eve    | HR           | 65000  |
| 6  | Frank  | Finance      | 80000  |
| 7  | Grace  | Finance      | 95000  |
| 8  | Heidi  | Engineering  | 95000  |

---

## 🔹 Syntax

```sql
<function_name>() OVER (
  [PARTITION BY column1, column2, ...]
  [ORDER BY column3 ASC|DESC]
  [ROWS BETWEEN ...]
)
```

---

## 🔹 Key Concepts

| Concept          | Description                                             |
| ---------------- | ------------------------------------------------------ |
| **OVER()**       | Defines the window frame for the function              |
| **PARTITION BY** | Divides result set into groups                         |
| **ORDER BY**     | Orders rows within each partition                      |
| **ROWS/RANGE**   | Specifies the window frame (rows to include)           |

---

## 🔹 Common Window Functions (with Examples)

### 1. **ROW_NUMBER()**  
Gives a unique number to each row in its partition.

```sql
SELECT name, department, salary,
       ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) AS dept_rank
FROM employees;
```
**Result:**  
Ranks employees by salary within each department.

---

### 2. **RANK()**  
Ranks rows in each partition. Ties get the same rank, and gaps follow.

```sql
SELECT name, department, salary,
       RANK() OVER (PARTITION BY department ORDER BY salary DESC) AS rank_in_dept
FROM employees;
```
**Result:**  
Same as `ROW_NUMBER()`, but if two employees have the same salary, they share the same rank, and the next rank is skipped.

---

### 3. **DENSE_RANK()**  
Like `RANK()`, but no gaps in rank numbers after ties.

```sql
SELECT name, department, salary,
       DENSE_RANK() OVER (PARTITION BY department ORDER BY salary DESC) AS dense_rank_in_dept
FROM employees;
```
---

### 4. **NTILE(n)**  
Divides rows into `n` equal buckets and assigns a bucket number.

```sql
SELECT name, salary,
       NTILE(4) OVER (ORDER BY salary DESC) AS salary_quartile
FROM employees;
```
**Result:**  
Divides all employees into salary quartiles.

---

## 🔹 Aggregate Functions as Window Functions

### 5. **SUM()**  
Get the total salary in each department.

```sql
SELECT name, department, salary,
       SUM(salary) OVER (PARTITION BY department) AS dept_total_salary
FROM employees;
```

---

### 6. **AVG()**  
Rolling average salary (current row and 2 previous).

```sql
SELECT name, salary,
       AVG(salary) OVER (ORDER BY salary ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) AS avg_salary
FROM employees;
```

---

### 7. **MIN() / MAX()**  
Get highest salary within each department.

```sql
SELECT name, department, salary,
       MAX(salary) OVER (PARTITION BY department) AS max_in_dept
FROM employees;
```

---

## 🔹 Value-based Window Functions

### 8. **LAG()**  
Returns value from the previous row.

```sql
SELECT name, salary,
       LAG(salary, 1) OVER (ORDER BY salary) AS previous_salary
FROM employees;
```
**Result:**  
Shows each employee's salary and the previous salary in the ordered list.

---

### 9. **LEAD()**  
Returns value from the next row.

```sql
SELECT name, salary,
       LEAD(salary, 1) OVER (ORDER BY salary) AS next_salary
FROM employees;
```
---

## 🔹 FIRST_VALUE() and LAST_VALUE()

### 10. **FIRST_VALUE()**  
Returns the first value in the window frame.

```sql
SELECT name, department, salary,
       FIRST_VALUE(salary) OVER (PARTITION BY department ORDER BY salary) AS min_salary_in_dept
FROM employees;
```
---

### 11. **LAST_VALUE()**  
Returns the last value in the window frame (be careful: window frame defaults matter!).

```sql
SELECT name, department, salary,
       LAST_VALUE(salary) OVER (
           PARTITION BY department
           ORDER BY salary
           ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING
       ) AS max_salary_in_dept
FROM employees;
```
---

## 🔹 Window Frame Clauses

Specify which rows are considered for each calculation:

```sql
ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
RANGE BETWEEN 1 PRECEDING AND 1 FOLLOWING
```

**Example – Moving Average:**

```sql
SELECT name, salary,
       AVG(salary) OVER (
         ORDER BY salary
         ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING
       ) AS moving_avg
FROM employees;
```
---

## 🔹 Use Case Examples

### ✅ Running Total

```sql
SELECT name, salary,
       SUM(salary) OVER (ORDER BY salary) AS running_total
FROM employees;
```

---

### ✅ Cumulative Average

```sql
SELECT name, salary,
       AVG(salary) OVER (ORDER BY salary ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS cum_avg
FROM employees;
```

---

### ✅ Salary Difference with Previous Employee

```sql
SELECT name, salary,
       salary - LAG(salary, 1, 0) OVER (ORDER BY salary) AS salary_diff
FROM employees;
```

---

## 🔹 Tips for Using Window Functions

- Use `PARTITION BY` to group rows logically.
- Always pair `ORDER BY` for meaningful sequences (especially in RANK, LAG, etc.).
- For `FIRST_VALUE()` or `LAST_VALUE()`, **specify window frame explicitly**.
- Essential for reports, leaderboards, financials, and time-series analysis.

---

## 🔹 Difference: Aggregate vs Window Function

| Feature             | Aggregate Function | Window Function               |
|---------------------|-------------------|-------------------------------|
| Output              | One row per group | One row per input row         |
| Use `GROUP BY`?     | Yes               | No (`OVER()` used instead)    |
| Collapses data?     | Yes               | No                            |
| Can use with WHERE? | Yes               | Limited (use CTE/subquery)    |

---

## 📌 Bonus: Combine Window Functions with CTEs

```sql
WITH ranked_employees AS (
  SELECT name, department, salary,
         RANK() OVER (PARTITION BY department ORDER BY salary DESC) AS rnk
  FROM employees
)
SELECT * FROM ranked_employees
WHERE rnk = 1;
-- Returns highest paid employee from each department
```
