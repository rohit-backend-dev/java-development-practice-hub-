
# 📘 SQL Interview Cheat Sheet (Most Asked Questions & Answers)

---

## **1. Basics & Fundamentals**

**Q1. Difference between SQL and NoSQL?**

* SQL → Structured Query Language, relational DB (tables, rows, fixed schema).
* NoSQL → Non-relational, stores unstructured/semi-structured data (JSON, key-value, documents, graphs).

---

**Q2. What are DDL, DML, DCL, TCL?**

* **DDL (Data Definition Language):** CREATE, ALTER, DROP, TRUNCATE.
* **DML (Data Manipulation Language):** INSERT, UPDATE, DELETE.
* **DCL (Data Control Language):** GRANT, REVOKE.
* **TCL (Transaction Control Language):** COMMIT, ROLLBACK, SAVEPOINT.

---

**Q3. Difference between DELETE, TRUNCATE, DROP?**

* DELETE → Removes rows (can use WHERE, logged, rollback possible).
* TRUNCATE → Removes all rows, faster, can’t use WHERE, minimal logging.
* DROP → Deletes entire table structure.

---

**Q4. Difference between Primary Key and Unique Key?**

* Primary Key: uniquely identifies a record, only one per table, auto NOT NULL.
* Unique Key: ensures uniqueness, multiple allowed, NULL allowed.

---

**Q5. Difference between Primary Key and Foreign Key?**

* Primary Key → Unique identifier in table.
* Foreign Key → References Primary Key of another table (maintains relationship).

---

**Q6. Difference between WHERE and HAVING?**

* WHERE → Filters rows **before** grouping.
* HAVING → Filters groups **after** aggregation.

---

**Q7. Difference between INNER, LEFT, RIGHT, FULL OUTER JOIN?**

* INNER JOIN → Matching records only.
* LEFT JOIN → All from left + matches from right.
* RIGHT JOIN → All from right + matches from left.
* FULL OUTER → All records from both, unmatched = NULL.

---

**Q8. Difference between UNION and UNION ALL?**

* UNION → Removes duplicates.
* UNION ALL → Keeps duplicates, faster.

---

**Q9. What is Normalization (1NF, 2NF, 3NF, BCNF)?**

* **1NF:** Atomic values, no repeating groups.
* **2NF:** 1NF + no partial dependency.
* **3NF:** 2NF + no transitive dependency.
* **BCNF:** Stronger form of 3NF (every determinant is a candidate key).

---

**Q10. What is Denormalization?**

* Adding redundancy (duplicate data) to improve query performance at cost of space.

---

## **2. Queries & Operators**

**Q11. Query: Second highest salary**

```sql
SELECT MAX(salary) 
FROM employees 
WHERE salary < (SELECT MAX(salary) FROM employees);
```

---

**Q12. Query: Duplicate rows**

```sql
SELECT name, COUNT(*) 
FROM employees 
GROUP BY name 
HAVING COUNT(*) > 1;
```

---

**Q13. Query: Nth highest salary (ex: 3rd highest)**

```sql
SELECT DISTINCT salary 
FROM employees e1 
WHERE 3 = (
   SELECT COUNT(DISTINCT salary) 
   FROM employees e2 
   WHERE e2.salary >= e1.salary
);
```

---

**Q14. Employees without a manager**

```sql
SELECT * FROM employees WHERE manager_id IS NULL;
```

---

**Q15. Employees joined last 30 days**

```sql
SELECT * FROM employees 
WHERE join_date >= CURRENT_DATE - INTERVAL '30 days';
```

---

**Q16. Departments with more than 5 employees**

```sql
SELECT dept_id, COUNT(*) 
FROM employees 
GROUP BY dept_id 
HAVING COUNT(*) > 5;
```

---

**Q17. Common records from two tables**

```sql
SELECT * FROM table1
INTERSECT
SELECT * FROM table2;
```

---

**Q18. Records in one table but not another**

```sql
SELECT * FROM table1
EXCEPT
SELECT * FROM table2;
```

---

**Q19. Top 3 salaries per department**

```sql
SELECT * FROM (
   SELECT emp_id, dept_id, salary,
          ROW_NUMBER() OVER (PARTITION BY dept_id ORDER BY salary DESC) AS rnk
   FROM employees
) t WHERE rnk <= 3;
```

---

**Q20. Difference between IN, BETWEEN, EXISTS?**

* **IN:** Checks if value in a list.
* **BETWEEN:** Checks within a range.
* **EXISTS:** Checks if subquery returns rows (efficient for correlated queries).

---

## **3. Advanced SQL Concepts**

**Q21. Indexes?**

* Improve search speed.
* **Clustered Index:** Sorts data physically (1 per table).
* **Non-clustered:** Stores pointers, many per table.

---

**Q22. What is a View?**

* Virtual table (based on query).
* Pros → Security, abstraction.
* Cons → Slower, can’t always update.

---

**Q23. Materialized View vs Normal View?**

* View → Stores only query.
* Materialized View → Stores query result, faster, needs refresh.

---

**Q24. Stored Procedure vs Function?**

* Procedure: Can return multiple values, allows DML.
* Function: Must return 1 value, can’t modify tables.

---

**Q25. Function vs Trigger?**

* Function: Called explicitly.
* Trigger: Fires automatically on INSERT/UPDATE/DELETE.

---

**Q26. What is Cursor?**

* Pointer to row-by-row processing of query result (used rarely).

---

**Q27. ACID Properties?**

* Atomicity, Consistency, Isolation, Durability.

---

**Q28. Transaction control commands?**

* COMMIT → Save changes.
* ROLLBACK → Undo.
* SAVEPOINT → Partial rollback point.

---

**Q29. Deadlock?**

* Two transactions wait indefinitely for resources held by each other. Prevent using proper lock hierarchy, timeouts.

---

**Q30. Window Functions?**

* Operate over partitions without collapsing rows.
* **ROW\_NUMBER()** → Sequential.
* **RANK()** → Gaps on ties.
* **DENSE\_RANK()** → No gaps.
* **NTILE(n)** → Divides rows into n groups.

---

## **4. Scenario-Based**

**Q31. Pivot rows to columns (example)**

```sql
SELECT dept_id,
   SUM(CASE WHEN gender='M' THEN 1 ELSE 0 END) AS males,
   SUM(CASE WHEN gender='F' THEN 1 ELSE 0 END) AS females
FROM employees GROUP BY dept_id;
```

---

**Q32. Cumulative salary (running total)**

```sql
SELECT emp_id, salary,
       SUM(salary) OVER (ORDER BY join_date) AS cumulative_salary
FROM employees;
```

---

**Q33. Max salary per department**

```sql
SELECT dept_id, MAX(salary) 
FROM employees 
GROUP BY dept_id;
```

---

**Q34. Swap two column values**

```sql
UPDATE employees
SET col1 = col2, col2 = col1;
```

---

**Q35. Employees with same salary**

```sql
SELECT salary, COUNT(*) 
FROM employees 
GROUP BY salary 
HAVING COUNT(*) > 1;
```

---

**Q36. Name starts with 'A' and ends with 'N'**

```sql
SELECT * FROM employees 
WHERE name LIKE 'A%N';
```

---

**Q37. Count distinct values**

```sql
SELECT COUNT(DISTINCT dept_id) FROM employees;
```

---

**Q38. Employees with NULL salary**

```sql
SELECT * FROM employees WHERE salary IS NULL;
```

---

**Q39. Update multiple rows different values**

```sql
UPDATE employees
SET salary = CASE emp_id
    WHEN 1 THEN 5000
    WHEN 2 THEN 6000
END
WHERE emp_id IN (1,2);
```

---

**Q40. Delete duplicates keep one**

```sql
DELETE FROM employees e1
WHERE ROWID > (
   SELECT MIN(ROWID) FROM employees e2 
   WHERE e1.name = e2.name
);
```

---

## **5. Performance & Optimization**

**Q41. Query optimization?**

* Use indexes, avoid SELECT \*, use joins instead of subqueries, partitioning, proper data types.

---

**Q42. Subquery vs Correlated Subquery?**

* Subquery: Independent, executes once.
* Correlated: Executes per row of outer query.

---

**Q43. Joins vs Subqueries?**

* Joins: Faster for combining multiple tables.
* Subqueries: Easier to read, but slower sometimes.

---

**Q44. OLTP vs OLAP?**

* OLTP → Transactional, normalized, fast writes (e.g., banking).
* OLAP → Analytical, denormalized, read-heavy (e.g., reporting).

---

**Q45. Sharding vs Partitioning?**

* **Partitioning:** Splitting table into parts in same DB.
* **Sharding:** Splitting data across multiple servers/databases.
