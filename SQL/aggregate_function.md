# SQL Aggregate Functions and the HAVING Clause

Aggregate functions in SQL perform calculations on a set of values to return a single, summary value. These functions are invaluable for generating reports, statistics, and data summaries. They're often paired with the `GROUP BY` clause to group rows that share a property and compute summary values for each group.

---

## Common SQL Aggregate Functions

| Function   | Purpose                                                        |
|------------|----------------------------------------------------------------|
| MIN()      | Returns the smallest (minimum) value from the selected column. |
| MAX()      | Returns the largest (maximum) value from the selected column.  |
| COUNT()    | Returns the number of rows or non-null values in a column.     |
| SUM()      | Returns the total sum of values in a numerical column.         |
| AVG()      | Returns the average value of a numerical column.               |

> **Note:** All aggregate functions (except `COUNT(*)`) ignore `NULL` values.

---

## Sample Table and Data

### Table Definition

```sql
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    address TEXT,
    city VARCHAR(50),
    state VARCHAR(50),
    postal_code VARCHAR(10),
    country VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Sample Data

```sql
INSERT INTO customers (first_name, last_name, email, phone, address, city, state, postal_code, country)
VALUES 
('Rohit', 'Kumar', 'rohit.kumar@example.com', '9876543210', '123 A Street', 'Delhi', 'Delhi', '110001', 'India'),
('Rohit', 'Sharma', NULL, '9876543211', '456 B Avenue', 'Mumbai', 'Maharashtra', '400001', 'India'),
('Sneha', 'Sharma', 'sneha.sharma@example.com', NULL, '789 C Road', 'Kolkata', 'West Bengal', '700001', 'India'),
('Sneha', 'Patel', NULL, NULL, NULL, 'Ahmedabad', 'Gujarat', '380001', 'India'),
('Amit', 'Verma', 'amit.verma@example.com', '9876543212', '321 D Lane', 'Chennai', 'Tamil Nadu', NULL, 'India'),
('Amit', 'Kumar', 'amit.k@example.com', NULL, '654 E Blvd', 'Delhi', 'Delhi', '110001', 'India'),
('Priya', 'Singh', NULL, NULL, NULL, 'Mumbai', 'Maharashtra', '400001', 'India'),
('Priya', 'Mehta', 'priya.m@example.com', '9876543215', '987 F Road', NULL, NULL, NULL, 'India'),
('Rahul', 'Yadav', 'rahul.yadav@example.com', '9876543216', '111 G Street', 'Lucknow', 'Uttar Pradesh', '226001', 'India'),
('Rahul', 'Yadav', NULL, '9876543220', '555 K Road', 'Lucknow', NULL, '226001', 'India'),
('Deepa', 'Rao', NULL, '9876543217', '222 H Circle', 'Hyderabad', 'Telangana', '500001', 'India'),
('Deepa', 'Desai', 'deepa.d@example.com', NULL, '444 J Path', 'Surat', 'Gujarat', NULL, 'India');
```

---

## Basic Aggregate Functions

### 1. `COUNT()`
- **Description:** Returns the number of rows matching a condition.

**Example 1:** Count all customers (including NULLs).
```sql
SELECT COUNT(*) FROM customers;
```

**Example 2:** Count customers with a non-null email.
```sql
SELECT COUNT(email) FROM customers;
```

---

### 2. `SUM()`
- **Description:** Returns the sum of a numeric column.

**Example:** Sum of all customer IDs.
```sql
SELECT SUM(customer_id) FROM customers;
```

---

### 3. `AVG()`
- **Description:** Returns the average value of a numeric column.

**Example:** Average customer ID.
```sql
SELECT AVG(customer_id) FROM customers;
```

---

### 4. `MIN()`
- **Description:** Returns the minimum value in a column.

**Example:** Earliest registration date.
```sql
SELECT MIN(created_at) FROM customers;
```

---

### 5. `MAX()`
- **Description:** Returns the maximum value in a column.

**Example:** Most recent registration date.
```sql
SELECT MAX(created_at) FROM customers;
```

---

## Advanced/Extended Aggregate Functions

### 6. `GROUP_CONCAT()` / `STRING_AGG()`
- **Description:** Concatenates values from multiple rows into a single string.

**MySQL Example:**
```sql
SELECT GROUP_CONCAT(first_name) FROM customers;
```

**PostgreSQL Example:**
```sql
SELECT STRING_AGG(first_name, ', ') FROM customers;
```

---

### 7. `VARIANCE()` / `VAR_POP()` / `VAR_SAMP()`
- **Description:** Calculates the variance of a numeric column.

**Example (PostgreSQL):**
```sql
SELECT VAR_SAMP(customer_id) FROM customers;
```

---

### 8. `STDDEV()` / `STDDEV_POP()` / `STDDEV_SAMP()`
- **Description:** Calculates the standard deviation of a numeric column.

**Example (PostgreSQL):**
```sql
SELECT STDDEV_SAMP(customer_id) FROM customers;
```

---

### 9. `BIT_AND()`, `BIT_OR()`, `BOOL_AND()`, `BOOL_OR()`
- **Description:** Bitwise and boolean aggregation (PostgreSQL/MySQL).

**Example (PostgreSQL):** Check if all customers have a non-null email.
```sql
SELECT BOOL_AND(email IS NOT NULL) FROM customers;
```

---

### 10. `JSON_AGG()`, `ARRAY_AGG()`
- **Description:** Aggregates values into a JSON array or SQL array (PostgreSQL).

**JSON Example:**
```sql
SELECT JSON_AGG(first_name) FROM customers;
```

**Array Example:**
```sql
SELECT ARRAY_AGG(city) FROM customers;
```

---

### 11. `EVERY()`
- **Description:** Returns `true` if all input values are true (PostgreSQL).

**Example:** Check if every customer has a phone number.
```sql
SELECT EVERY(phone IS NOT NULL) FROM customers;
```

---

### 12. `MEDIAN()` / `PERCENTILE_CONT()`
- **Description:** Median value from a numeric column.

**Example (PostgreSQL):**
```sql
SELECT percentile_cont(0.5) WITHIN GROUP (ORDER BY customer_id) FROM customers;
```

---

### 13. `MODE()`
- **Description:** Most frequent value in a column.

**Example (PostgreSQL):**
```sql
SELECT mode() WITHIN GROUP (ORDER BY city) FROM customers;
```

---

### 14. `PERCENTILE_CONT()`, `PERCENTILE_DISC()`
- **Description:** Value at a specified percentile.

**Example (PostgreSQL):**
```sql
SELECT percentile_cont(0.9) WITHIN GROUP (ORDER BY customer_id) FROM customers;
```

---

### 15. `CUME_DIST()`
- **Description:** Cumulative distribution of a value in a group (window function).

**Example:**
```sql
SELECT customer_id, CUME_DIST() OVER (ORDER BY customer_id) AS cum_dist
FROM customers;
```

---

### 16. `RANK()`, `DENSE_RANK()`, `ROW_NUMBER()`
- **Description:** Ranking window functions.

**Example:** Rank customers by registration date.
```sql
SELECT customer_id, created_at,
       RANK() OVER (ORDER BY created_at) AS rank_num
FROM customers;
```

---

## Usage with GROUP BY

The `GROUP BY` clause is essential for producing summary statistics and reports. It groups rows sharing properties and computes aggregates for each group.

**Example: Number of Customers in Each City**
```sql
SELECT city, COUNT(*)
FROM customers
GROUP BY city;
```

**Sample Output:**

| city      | count |
|-----------|-------|
| Delhi     |   2   |
| Mumbai    |   2   |
| Kolkata   |   1   |
| Ahmedabad |   1   |
| Chennai   |   1   |
| Lucknow   |   2   |
| Hyderabad |   1   |
| Surat     |   1   |
| (null)    |   1   |

> *Customers with NULL cities are grouped together as `(null)`.*

**Additional Tips:**

- **Group by Multiple Columns:**
  ```sql
  SELECT city, country, COUNT(*) 
  FROM customers
  GROUP BY city, country;
  ```

- **Multiple Aggregates:**
  ```sql
  SELECT city, COUNT(*) AS total_customers, AVG(customer_id) AS avg_id
  FROM customers
  GROUP BY city;
  ```

- **Sorting Results:**
  ```sql
  SELECT city, COUNT(*) AS total_customers
  FROM customers
  GROUP BY city
  ORDER BY total_customers DESC;
  ```

- **Filtering Groups with HAVING:**
  ```sql
  SELECT city, COUNT(*) AS total_customers
  FROM customers
  GROUP BY city
  HAVING COUNT(*) > 1;
  ```

- **Advanced Example:**
  ```sql
  SELECT city,
         COUNT(*) as total_customers,
         COUNT(email) as with_email,
         COUNT(*) - COUNT(email) as without_email
  FROM customers
  GROUP BY city;
  ```

---

## The HAVING Clause in SQL

The `HAVING` clause filters groups created by `GROUP BY` using aggregate functions. It is applied after grouping, unlike `WHERE`, which filters rows before grouping.

### When to Use HAVING

- Use `WHERE` to filter rows before they are grouped.
- Use `HAVING` to filter groups after aggregation.
- You **cannot** use aggregate functions in the `WHERE` clause, but you **can** in `HAVING`.

### Syntax

```sql
SELECT column1, AGGREGATE_FUNCTION(column2)
FROM table_name
GROUP BY column1
HAVING aggregate_condition;
```
- `HAVING` comes after `GROUP BY` and before `ORDER BY`.

### Example: Cities with More Than 2 Customers

```sql
SELECT city, COUNT(*) AS total_customers
FROM customers
GROUP BY city
HAVING COUNT(*) > 2;
```

### More HAVING Clause Examples

**Average Customer ID Greater Than 5:**
```sql
SELECT city, AVG(customer_id) AS avg_id
FROM customers
GROUP BY city
HAVING AVG(customer_id) > 5;
```

**Max Customer ID Less Than 10, Grouped by City and Country:**
```sql
SELECT city, country, MAX(customer_id) AS max_id
FROM customers
GROUP BY city, country
HAVING MAX(customer_id) < 10;
```

### Combining WHERE and HAVING

You can use both `WHERE` and `HAVING` in a single query:

```sql
SELECT city, COUNT(*) AS total_customers
FROM customers
WHERE country = 'India'
GROUP BY city
HAVING COUNT(*) > 2;
```

---

## Summary Table: WHERE vs HAVING

| Clause | Filters                   | Used With | Can Use Aggregates? |
|--------|---------------------------|-----------|---------------------|
| WHERE  | Rows (before grouping)    | Any SELECT| No                  |
| HAVING | Groups (after grouping)   | GROUP BY  | Yes                 |

---

## Notes

- Aggregate functions generally ignore `NULL` values, except for `COUNT(*)`.
- Some aggregate functions are specific to certain database systems (check your SQL dialect).
- For custom aggregations (like median or mode), use window functions or built-in advanced aggregate functions if available.

---
