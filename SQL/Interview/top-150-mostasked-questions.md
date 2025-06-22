# Top 200 Most Frequently Asked SQL Questions for Interviews, Exams & Competitive Coding

The following questions are carefully selected and explained for practical interview and coding success.  

**Sectioned for clarity, each includes concise explanation and sample query when appropriate.**

---

### SQL Basics

#### 1. What is SQL?  
SQL (Structured Query Language) is used to manage and retrieve data in relational databases.

#### 2. What is a database?  
A structured collection of data stored electronically.

#### 3. What are tables, rows, and columns?  
- Table: Collection of rows and columns.
- Row: A record.
- Column: Attribute/field of the data.

#### 4. What is the difference between SQL and NoSQL?  
SQL: Structured, table-based; NoSQL: Unstructured or semi-structured, document, key-value, etc.

#### 5. What is a primary key?  
A column or set of columns that uniquely identifies each row in a table.

#### 6. What is a foreign key?  
A column that references the primary key of another table, used for relationships.

#### 7. What is normalization? Name its types.  
Process to minimize redundancy: 1NF, 2NF, 3NF, BCNF, 4NF, 5NF.

#### 8. What is denormalization?  
The process of combining tables to improve read performance.

#### 9. What is an index?  
A database object that improves the speed of data retrieval.

#### 10. What is a view?  
A virtual table based on the result-set of a SQL statement.

---

### SELECT and Filtering

#### 11. Select all records from a table.
```sql
SELECT * FROM students;
```

#### 12. Select specific columns.
```sql
SELECT name, age FROM students;
```

#### 13. Select distinct values.
```sql
SELECT DISTINCT(course) FROM students;
```

#### 14. Rename a column in the result.
```sql
SELECT name AS student_name FROM students;
```

#### 15. Limit the number of returned rows.
```sql
SELECT * FROM students LIMIT 5;
```

#### 16. Order by a column (descending).
```sql
SELECT * FROM students ORDER BY marks DESC;
```

#### 17. Filter rows using WHERE.
```sql
SELECT * FROM students WHERE age > 18;
```

#### 18. Filter with BETWEEN, IN, LIKE.
```sql
SELECT * FROM students WHERE age BETWEEN 18 AND 22;
SELECT * FROM students WHERE course IN ('B.Tech', 'MCA');
SELECT * FROM students WHERE name LIKE 'A%';
```

#### 19. Check for NULL values.
```sql
SELECT * FROM students WHERE email IS NULL;
```

#### 20. Combine multiple conditions.
```sql
SELECT * FROM students WHERE age > 18 AND course = 'B.Tech';
```

#### 21. Names ending with 'son' or containing 'man'.
```sql
SELECT * FROM students WHERE name LIKE '%son';
SELECT * FROM students WHERE name ILIKE '%man%';
```

---

### JOINs

#### 22. What is an INNER JOIN?
```sql
SELECT s.name, e.marks FROM students s INNER JOIN exams e ON s.id = e.student_id;
```

#### 23. LEFT JOIN example.
```sql
SELECT s.name, e.marks FROM students s LEFT JOIN exams e ON s.id = e.student_id;
```

#### 24. RIGHT JOIN example.
```sql
SELECT s.name, e.marks FROM students s RIGHT JOIN exams e ON s.id = e.student_id;
```

#### 25. FULL OUTER JOIN example.
```sql
SELECT s.name, e.marks FROM students s FULL OUTER JOIN exams e ON s.id = e.student_id;
```

#### 26. CROSS JOIN example.
```sql
SELECT * FROM students CROSS JOIN courses;
```

#### 27. SELF JOIN example.
```sql
SELECT s1.name, s2.name AS friend FROM students s1 JOIN students s2 ON s1.friend_id = s2.id;
```

---

### GROUP BY and Aggregation

#### 28. List aggregate functions.
SUM(), AVG(), COUNT(), MIN(), MAX()

#### 29. Total marks by course.
```sql
SELECT course, SUM(marks) FROM students GROUP BY course;
```

#### 30. Count students in each course.
```sql
SELECT course, COUNT(*) FROM students GROUP BY course;
```

#### 31. Highest marks in each course.
```sql
SELECT course, MAX(marks) FROM students GROUP BY course;
```

#### 32. WHERE vs HAVING.
WHERE filters rows; HAVING filters groups after aggregation.

#### 33. Courses with more than 5 students.
```sql
SELECT course FROM students GROUP BY course HAVING COUNT(*) > 5;
```

#### 34. Average marks for courses with ≥3 students.
```sql
SELECT course, AVG(marks) FROM students GROUP BY course HAVING COUNT(*) >= 3;
```

#### 35. Employees with salary above department average.
```sql
SELECT e.*
FROM employees e
JOIN (
  SELECT department, AVG(salary) AS avg_salary FROM employees GROUP BY department
) a ON e.department = a.department
WHERE e.salary > a.avg_salary;
```

---

### Subqueries

#### 36. Find students with marks above average.
```sql
SELECT * FROM students WHERE marks > (SELECT AVG(marks) FROM students);
```

#### 37. Students in courses with more than 10 students.
```sql
SELECT * FROM students WHERE course IN (
  SELECT course FROM students GROUP BY course HAVING COUNT(*) > 10
);
```

#### 38. Correlated subquery for marks above course average.
```sql
SELECT s1.* FROM students s1
WHERE marks > (
  SELECT AVG(marks) FROM students s2 WHERE s2.course = s1.course
);
```

#### 39. Find students not enrolled in any exam.
```sql
SELECT * FROM students s
WHERE NOT EXISTS (
  SELECT 1 FROM exams e WHERE e.student_id = s.id
);
```

#### 40. Select students with the highest marks.
```sql
SELECT * FROM students WHERE marks = (SELECT MAX(marks) FROM students);
```

#### 41. Find departments with no employees.
```sql
SELECT d.department_id FROM departments d
LEFT JOIN employees e ON d.department_id = e.department_id
WHERE e.employee_id IS NULL;
```

---

### String Functions

#### 42. Uppercase all names.
```sql
SELECT UPPER(name) FROM students;
```

#### 43. Concatenate first and last name.
```sql
SELECT first_name || ' ' || last_name AS full_name FROM students;
```

#### 44. Extract first 3 characters.
```sql
SELECT SUBSTRING(name FROM 1 FOR 3) FROM students;
```

#### 45. Find the length of names.
```sql
SELECT LENGTH(name) FROM students;
```

#### 46. Remove leading/trailing spaces.
```sql
SELECT TRIM(name) FROM students;
```

#### 47. Find position of '@' in email.
```sql
SELECT POSITION('@' IN email) FROM students;
```

#### 48. Replace 'HR' with 'Human Resources' in department.
```sql
SELECT REPLACE(department, 'HR', 'Human Resources') FROM employees;
```

---

### Date and Time

#### 49. Get current date and time.
```sql
SELECT NOW();
```

#### 50. Extract year from date_of_birth.
```sql
SELECT EXTRACT(YEAR FROM date_of_birth) FROM students;
```

#### 51. Add 7 days to date_of_birth.
```sql
SELECT date_of_birth + INTERVAL '7 days' FROM students;
```

#### 52. Find difference in days between two dates.
```sql
SELECT (end_date - start_date) AS diff_days FROM attendance;
```

#### 53. Find students born in January.
```sql
SELECT * FROM students WHERE EXTRACT(MONTH FROM date_of_birth) = 1;
```

#### 54. Find students older than 20 years.
```sql
SELECT * FROM students WHERE AGE(NOW(), date_of_birth) > INTERVAL '20 years';
```

#### 55. Format date as 'YYYY-MM-DD'.
```sql
SELECT TO_CHAR(date_of_birth, 'YYYY-MM-DD') FROM students;
```

#### 56. Find employees whose contract ends in the next 30 days.
```sql
SELECT * FROM employees WHERE contract_end_date BETWEEN NOW() AND NOW() + INTERVAL '30 days';
```

---

### Set Operations (UNION, INTERSECT, EXCEPT)

#### 57. What does UNION do?  
Combines result sets, removes duplicates.

#### 58. Get all unique course names from students and alumni.
```sql
SELECT course FROM students UNION SELECT course FROM alumni;
```

#### 59. What is UNION ALL?  
Combines result sets, includes duplicates.

#### 60. Find students who are also alumni.
```sql
SELECT id FROM students INTERSECT SELECT id FROM alumni;
```

#### 61. List students not present in alumni.
```sql
SELECT id FROM students EXCEPT SELECT id FROM alumni;
```

#### 62. Can you use ORDER BY with UNION?
Yes, but only after the last SELECT.

---

### Data Definition Language (DDL)

#### 63. Create a table for students.
```sql
CREATE TABLE students (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  age INT
);
```

#### 64. Alter table to add an email column.
```sql
ALTER TABLE students ADD COLUMN email VARCHAR(100);
```

#### 65. Change data type of email to TEXT.
```sql
ALTER TABLE students ALTER COLUMN email TYPE TEXT;
```

#### 66. Drop the age column.
```sql
ALTER TABLE students DROP COLUMN age;
```

#### 67. Rename the students table to learners.
```sql
ALTER TABLE students RENAME TO learners;
```

#### 68. Drop the learners table.
```sql
DROP TABLE learners;
```

#### 69. Create an index on name.
```sql
CREATE INDEX idx_name ON students(name);
```

#### 70. Drop the index idx_name.
```sql
DROP INDEX idx_name;
```

---

### Data Manipulation Language (DML)

#### 71. Insert a new student.
```sql
INSERT INTO students (name, age) VALUES ('John', 20);
```

#### 72. Update a student's age.
```sql
UPDATE students SET age = 21 WHERE id = 1;
```

#### 73. Delete students aged under 18.
```sql
DELETE FROM students WHERE age < 18;
```

#### 74. Insert multiple rows.
```sql
INSERT INTO students (name, age) VALUES ('Alice', 22), ('Bob', 19);
```

#### 75. Update multiple columns.
```sql
UPDATE students SET age = age + 1, name = 'Senior ' || name WHERE course = 'B.Tech';
```

#### 76. Delete all rows from a table.
```sql
DELETE FROM students;
```
*Tip:* Use `TRUNCATE students;` for faster, irreversible deletion.

---

### Constraints and Keys

#### 77. Add a unique constraint on email.
```sql
ALTER TABLE students ADD CONSTRAINT unique_email UNIQUE(email);
```

#### 78. Make name NOT NULL.
```sql
ALTER TABLE students ALTER COLUMN name SET NOT NULL;
```

#### 79. Add a CHECK constraint for age >= 18.
```sql
ALTER TABLE students ADD CONSTRAINT chk_age CHECK (age >= 18);
```

#### 80. Remove a constraint.
```sql
ALTER TABLE students DROP CONSTRAINT chk_age;
```

#### 81. Composite key example.
```sql
CREATE TABLE enrollment (
  student_id INT,
  course_id INT,
  PRIMARY KEY (student_id, course_id)
);
```

#### 82. Difference between primary key and unique key.
Primary key: Unique + NOT NULL, only one per table.  
Unique key: Unique, allows NULLs, multiple per table.

#### 83. How do you enforce referential integrity?
Using foreign key constraints.

---

### Views, Indexes, and Transactions

#### 84. Create a view for high scoring students.
```sql
CREATE VIEW toppers AS SELECT * FROM students WHERE marks > 90;
```

#### 85. What is an index, and when should you use it?
Speeds up SELECT queries on columns with many distinct values.

#### 86. What is a transaction?
A set of SQL statements executed as a single unit.

#### 87. Start and commit a transaction.
```sql
BEGIN;
-- SQL statements
COMMIT;
```

#### 88. Rollback a transaction.
```sql
BEGIN;
-- SQL statements
ROLLBACK;
```

#### 89. What is a materialized view?
Stores the result physically, unlike a regular view.

#### 90. Refresh a materialized view.
```sql
REFRESH MATERIALIZED VIEW toppers;
```

#### 91. Difference between TRUNCATE and DELETE.
TRUNCATE removes all rows, faster, cannot be rolled back (in most cases). DELETE can be rolled back.

#### 92. List all views in the database.
```sql
SELECT table_name FROM information_schema.views WHERE table_schema = 'public';
```

#### 93. Remove a view.
```sql
DROP VIEW toppers;
```

---

### Stored Procedures and Triggers

#### 94. What is a stored procedure?
A set of SQL statements stored in the database.

#### 95. Create a stored procedure to increment marks.
```sql
CREATE OR REPLACE PROCEDURE inc_marks()
LANGUAGE plpgsql
AS $$
BEGIN
  UPDATE students SET marks = marks + 5;
END;
$$;
```

#### 96. Call a stored procedure.
```sql
CALL inc_marks();
```

#### 97. What is a trigger?
A function that runs automatically on table events (INSERT/UPDATE/DELETE).

#### 98. Create a trigger to log deletions.
```sql
CREATE TABLE student_deletions (id INT, deleted_at TIMESTAMP);
CREATE OR REPLACE FUNCTION log_student_deletion()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO student_deletions (id, deleted_at) VALUES (OLD.id, NOW());
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER after_student_delete AFTER DELETE ON students FOR EACH ROW EXECUTE PROCEDURE log_student_deletion();
```

---

### Window Functions & Advanced SQL

#### 99. Get running total of marks.
```sql
SELECT id, marks, SUM(marks) OVER (ORDER BY id) AS running_total FROM students;
```

#### 100. Find nth highest marks.
```sql
SELECT DISTINCT marks FROM students ORDER BY marks DESC OFFSET n-1 LIMIT 1;
```

#### 101. Rank students by marks.
```sql
SELECT id, marks, RANK() OVER (ORDER BY marks DESC) AS rnk FROM students;
```

#### 102. Top 3 marks per course.
```sql
SELECT * FROM (
  SELECT *, ROW_NUMBER() OVER (PARTITION BY course ORDER BY marks DESC) AS rn
  FROM students
) t WHERE rn <= 3;
```

#### 103. Moving average of marks over last 3 records.
```sql
SELECT id, marks, AVG(marks) OVER (ORDER BY id ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) AS moving_avg FROM students;
```

#### 104. Pivot total marks by course and gender.
```sql
SELECT course, SUM(CASE WHEN gender = 'M' THEN marks ELSE 0 END) AS male_marks, SUM(CASE WHEN gender = 'F' THEN marks ELSE 0 END) AS female_marks FROM students GROUP BY course;
```

#### 105. Unpivot: Convert columns to rows.
```sql
SELECT id, 'marks' AS attr, marks AS value FROM students UNION ALL SELECT id, 'bonus', bonus FROM students;
```

#### 106. ROW_NUMBER vs RANK vs DENSE_RANK.
ROW_NUMBER(): No gaps; RANK(): gaps after ties; DENSE_RANK(): no gaps after ties.

#### 107. Recursive query for factorial of 5.
```sql
WITH RECURSIVE factorial(n, fact) AS (
  SELECT 1, 1
  UNION ALL
  SELECT n + 1, (n + 1) * fact FROM factorial WHERE n < 5
)
SELECT * FROM factorial;
```

#### 108. Find employees with the same salary.
```sql
SELECT salary, COUNT(*) FROM employees GROUP BY salary HAVING COUNT(*) > 1;
```

#### 109. Find employees with minimum salary in each department.
```sql
SELECT * FROM (
  SELECT *, RANK() OVER (PARTITION BY department ORDER BY salary) as rnk FROM employees
) t WHERE rnk = 1;
```

#### 110. Find top 3 salaries per department.
```sql
SELECT * FROM (
  SELECT *, ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) AS rn FROM employees
) t WHERE rn <= 3;
```

#### 111. Find employees with the longest tenure.
```sql
SELECT * FROM employees WHERE hire_date = (SELECT MIN(hire_date) FROM employees);
```

#### 112. Find employees whose manager is also an employee.
```sql
SELECT e.* FROM employees e JOIN employees m ON e.manager_id = m.employee_id;
```

#### 113. List employees with no manager.
```sql
SELECT * FROM employees WHERE manager_id IS NULL;
```

#### 114. Find percentage of employees in each department.
```sql
SELECT department, COUNT(*) * 100.0 / (SELECT COUNT(*) FROM employees) AS pct FROM employees GROUP BY department;
```

#### 115. Recursive CTE to get all subordinates of a manager.
```sql
WITH RECURSIVE subordinates AS (
  SELECT employee_id, manager_id FROM employees WHERE manager_id = 1
  UNION
  SELECT e.employee_id, e.manager_id FROM employees e INNER JOIN subordinates s ON e.manager_id = s.employee_id
)
SELECT * FROM subordinates;
```

#### 116. Find moving average salary over last 3 records.
```sql
SELECT employee_id, salary, AVG(salary) OVER (ORDER BY employee_id ROWS BETWEEN 2 PRECEDING AND CURRENT ROW) AS moving_avg FROM employees;
```

#### 117. Find employees who joined before their manager.
```sql
SELECT e1.* FROM employees e1 JOIN employees e2 ON e1.manager_id = e2.employee_id WHERE e1.hire_date < e2.hire_date;
```

#### 118. Find duplicate records in a table.
```sql
SELECT name, COUNT(*) FROM students GROUP BY name HAVING COUNT(*) > 1;
```

#### 119. Delete duplicate rows, keeping one.
```sql
DELETE FROM students WHERE ctid NOT IN (SELECT MIN(ctid) FROM students GROUP BY name);
```

#### 120. Find gaps in an ID sequence.
```sql
SELECT id + 1 AS missing_id FROM students WHERE (id + 1) NOT IN (SELECT id FROM students);
```

#### 121. Categorize tree nodes as root, inner, or leaf.
```sql
SELECT id, CASE WHEN parent_id IS NULL THEN 'Root' WHEN id IN (SELECT parent_id FROM tree) THEN 'Inner' ELSE 'Leaf' END AS Type FROM tree ORDER BY id;
```

#### 122. Swap alternate seat IDs.
```sql
SELECT CASE WHEN MOD(id, 2) != 0 AND counts != id THEN id + 1 WHEN MOD(id, 2) != 0 AND counts = id THEN id ELSE id - 1 END as id, student FROM seat, (SELECT COUNT(*) as counts FROM seat) AS seat_count ORDER BY id;
```

#### 123. Median salary per company (without built-in median).
```sql
SELECT table.id, table.company, table.salary FROM (
  SELECT id, company, salary, DENSE_RANK() OVER (PARTITION BY company ORDER BY salary, id) AS Ranking, COUNT(1) OVER (PARTITION BY company) / 2.0 AS EmployeeCount FROM Employee
) table WHERE Ranking BETWEEN EmployeeCount AND EmployeeCount + 1;
```

#### 124. Stadium 3+ consecutive days with ≥100 people.
```sql
WITH cte AS (SELECT * FROM stadium WHERE count_people >= 100)
SELECT cte.id, cte.date_visited, cte.count_people FROM cte
WHERE ((cte.id + 1) IN (SELECT id FROM cte) AND (cte.id + 2) IN (SELECT id FROM cte))
   OR ((cte.id - 1) IN (SELECT id FROM cte) AND (cte.id - 2) IN (SELECT id FROM cte))
   OR ((cte.id + 1) IN (SELECT id FROM cte) AND (cte.id - 1) IN (SELECT id FROM cte))
ORDER BY cte.id;
```

#### 125. Explain ACID properties.
Atomicity, Consistency, Isolation, Durability: key transaction guarantees.

#### 126. What is a deadlock and how do you resolve it?
A deadlock occurs when transactions block each other. Use proper locking, set timeouts, or retry logic.

#### 127. Explain isolation levels.
Read Uncommitted, Read Committed, Repeatable Read, Serializable.

#### 128. What is a surrogate key?
A system-generated unique key (e.g. SERIAL).

#### 129. What is a natural key?
A key from the business data (e.g. email, SSN).

#### 130. What is a composite key?
A key formed from two or more columns to uniquely identify a row.

#### 131. What is a covering index?
An index that contains all the columns needed for a query.

#### 132. What is a clustered index?
Data rows are stored in order of the index.

#### 133. What is a non-clustered index?
A separate structure from the data rows.

#### 134. What is a partial index?
An index built on part of a table.

#### 135. Difference between DELETE, TRUNCATE, DROP.
DELETE: removes rows; TRUNCATE: removes all rows, resets identity; DROP: removes table.

#### 136. What is a NULL value? How is it different from zero or blank?
NULL means missing or unknown; zero/blank are actual values.

#### 137. How do you handle NULLs in aggregate functions?
Aggregates ignore NULLs (except COUNT(*)).

#### 138. What is COALESCE?
Returns the first non-NULL value in a list.

#### 139. What is CASE? Give an example.
CASE is used for conditional expressions.

```sql
SELECT name, CASE WHEN marks > 90 THEN 'A' ELSE 'B' END AS grade FROM students;
```

#### 140. How do you prevent SQL injection?
Use parameterized queries and prepared statements.

#### 141. What is a CTE (Common Table Expression)?
A temporary result set using WITH.

#### 142. What are window frames?
The range of rows for window functions (e.g., ROWS BETWEEN).

#### 143. Difference between DENSE_RANK and RANK.
DENSE_RANK: no gaps in ranking; RANK: gaps after ties.

#### 144. How do you find the first and last record in a group?
Use ROW_NUMBER() or MIN/MAX with GROUP BY.

#### 145. Explain EXISTS and NOT EXISTS.
EXISTS checks for the existence of rows in a subquery.

#### 146. What are scalar, inline, and multi-statement functions?
Scalar: returns single value; inline: single SELECT statement; multi-statement: multiple statements.

#### 147. What is the difference between HAVING and WHERE?
WHERE filters rows before grouping; HAVING filters groups after aggregation.

#### 148. How do you copy a table structure without data?
```sql
CREATE TABLE new_table AS TABLE old_table WITH NO DATA;
```

#### 149. How to generate a sequence in PostgreSQL?
```sql
CREATE SEQUENCE seq_name START 1;
```

#### 150. How to get current user in PostgreSQL?
```sql
SELECT CURRENT_USER;
```

#### 151. How to find duplicate rows based on multiple columns?
```sql
SELECT col1, col2, COUNT(*) FROM table GROUP BY col1, col2 HAVING COUNT(*) > 1;
```

#### 152. How to remove duplicates based on multiple columns, keep one?
```sql
DELETE FROM table WHERE ctid NOT IN (SELECT MIN(ctid) FROM table GROUP BY col1, col2);
```

#### 153. How to change a column's default value?
```sql
ALTER TABLE students ALTER COLUMN age SET DEFAULT 18;
```

#### 154. How to get all constraints on a table?
```sql
SELECT * FROM information_schema.table_constraints WHERE table_name = 'students';
```

#### 155. How to list all tables in a database?
```sql
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';
```

#### 156. How to add a foreign key constraint?
```sql
ALTER TABLE orders ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers(id);
```

#### 157. How to drop a foreign key constraint?
```sql
ALTER TABLE orders DROP CONSTRAINT fk_customer;
```

#### 158. How to rename a column?
```sql
ALTER TABLE students RENAME COLUMN name TO full_name;
```

#### 159. How to add a NOT NULL constraint?
```sql
ALTER TABLE students ALTER COLUMN full_name SET NOT NULL;
```

#### 160. How to drop a NOT NULL constraint?
```sql
ALTER TABLE students ALTER COLUMN full_name DROP NOT NULL;
```

#### 161. How to set auto-increment for a column?
```sql
ALTER TABLE students ALTER COLUMN id SET DEFAULT nextval('students_id_seq');
```

#### 162. How to select all columns except one?
You must list all the desired columns except the one you want to exclude.

#### 163. How to get the second highest value in a column?
See Q1.

#### 164. How to find the maximum for each group?
```sql
SELECT department, MAX(salary) FROM employees GROUP BY department;
```

#### 165. How to get the count of NULL values in a column?
```sql
SELECT COUNT(*) - COUNT(column_name) FROM table;
```

#### 166. How to select random rows?
```sql
SELECT * FROM students ORDER BY RANDOM() LIMIT 1;
```

#### 167. How to implement pagination in SQL?
```sql
SELECT * FROM students ORDER BY id LIMIT 10 OFFSET 20;
```

#### 168. How to concatenate strings with a separator?
```sql
SELECT STRING_AGG(name, ', ') FROM students;
```

#### 169. How to split a string into an array?
```sql
SELECT STRING_TO_ARRAY(name, ' ') FROM students;
```

#### 170. How to flatten an array column?
```sql
SELECT UNNEST(array_column) FROM table;
```

#### 171. How to use JSON columns in PostgreSQL?
```sql
SELECT data->>'key' FROM table;
```

#### 172. How to create and use ENUM types?
```sql
CREATE TYPE mood AS ENUM ('sad', 'ok', 'happy');
ALTER TABLE students ADD COLUMN mood mood;
```

#### 173. How to check table size in PostgreSQL?
```sql
SELECT pg_size_pretty(pg_total_relation_size('students'));
```

#### 174. How to find duplicate primary keys?
```sql
SELECT id, COUNT(*) FROM students GROUP BY id HAVING COUNT(*) > 1;
```

#### 175. How to create a temporary table?
```sql
CREATE TEMP TABLE temp_students (id INT, name TEXT);
```

#### 176. How to drop a temporary table?
```sql
DROP TABLE temp_students;
```

#### 177. How to use IF EXISTS in DROP statements?
```sql
DROP TABLE IF EXISTS students;
```

#### 178. How to get database version?
```sql
SELECT version();
```

#### 179. How to list all databases?
```sql
SELECT datname FROM pg_database;
```

#### 180. How to set a column to NULL?
```sql
UPDATE students SET email = NULL WHERE id = 1;
```

#### 181. How to use DISTINCT ON in PostgreSQL?
```sql
SELECT DISTINCT ON (course) * FROM students ORDER BY course, marks DESC;
```

#### 182. How to create a partial unique index?
```sql
CREATE UNIQUE INDEX idx_email ON students(email) WHERE email IS NOT NULL;
```

#### 183. How to optimize a query?
Use EXPLAIN, indexes, avoid SELECT *, write specific WHERE clauses.

#### 184. What is a transaction savepoint?
A point within a transaction to which you can roll back.

#### 185. How to use a savepoint?
```sql
SAVEPOINT my_savepoint;
ROLLBACK TO my_savepoint;
```

#### 186. How to get the current date?
```sql
SELECT CURRENT_DATE;
```

#### 187. How to get the day name from a date?
```sql
SELECT TO_CHAR(date_of_birth, 'Day') FROM students;
```

#### 188. How to find the last inserted ID?
```sql
INSERT INTO students(name) VALUES ('John') RETURNING id;
```

#### 189. How to use CASE in ORDER BY?
```sql
SELECT * FROM students ORDER BY CASE WHEN marks > 90 THEN 1 ELSE 2 END, name;
```

#### 190. How to check if a table exists?
```sql
SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'students');
```

#### 191. How to extract part of a string?
```sql
SELECT SUBSTRING(email FROM 1 FOR 5) FROM students;
```

#### 192. How to remove duplicates from a SELECT result?
```sql
SELECT DISTINCT name FROM students;
```

#### 193. How to create a function in PostgreSQL?
```sql
CREATE FUNCTION add(a INT, b INT) RETURNS INT AS $$
BEGIN
  RETURN a + b;
END; $$ LANGUAGE plpgsql;
```

#### 194. How to use lateral joins?
```sql
SELECT s.*, w.word FROM sentences s
CROSS JOIN LATERAL unnest(string_to_array(sentence, ' ')) AS w(word);
```

#### 195. How to get column names of a table?
```sql
SELECT column_name FROM information_schema.columns WHERE table_name = 'students';
```

#### 196. How to find the number of tables in a database?
```sql
SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public';
```

#### 197. How to create a check constraint for a value in a range?
```sql
ALTER TABLE students ADD CONSTRAINT age_range CHECK (age BETWEEN 18 AND 30);
```

#### 198. How to check if a value exists in a table?
```sql
SELECT EXISTS(SELECT 1 FROM students WHERE name = 'Alice');
```

#### 199. How to use a subquery in SELECT?
```sql
SELECT name, (SELECT AVG(marks) FROM students) AS avg_marks FROM students;
```

#### 200. How to limit query execution time in PostgreSQL?
```sql
SET statement_timeout = '5s';
```
