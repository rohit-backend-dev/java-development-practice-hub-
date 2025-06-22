# SQL Joins & Set Operations 

---

## What is a Join in SQL?

A **join** in SQL is an operation used to combine data from two or more tables based on a related column. This allows you to write queries that answer real-world questions using data that is spread across multiple tables—such as "Which customers have placed orders?"

- The related column is usually a **primary key** in one table and a **foreign key** in another.
- Joins help normalize your database, avoiding redundant data.
- You specify the join condition using `ON table1.common_column = table2.common_column`.

---

## Why Use Set Operations?

**Set operations** allow you to combine, compare, and filter entire result sets from multiple queries. They are powerful for data analysis, reporting, and building complex logic in SQL.

- All set operations require the same number of columns and compatible data types in each query.
- Use set operations to unify, intersect, or find differences between datasets.

---

## 1. Example Tables (Copy and Paste These First!)

```sql
-- Drop tables if they exist (for repeated testing)
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS A;
DROP TABLE IF EXISTS B;

-- Customers table
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(50),
    city VARCHAR(50)
);
INSERT INTO Customers VALUES
(1, 'Alice', 'Delhi'),
(2, 'Bob', 'Mumbai'),
(3, 'Charlie', 'Bangalore'),
(4, 'David', 'Kolkata');

SELECT * FROM Customers;

-- Orders table
CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    product_id INT,
    product VARCHAR(50)
);
INSERT INTO Orders VALUES
(101, 1, 501, 'Laptop'),
(102, 3, 502, 'Phone'),
(103, 4, 503, 'Tablet'),
(104, 5, 504, 'Monitor'); 

SELECT * FROM Orders;

-- Products table
CREATE TABLE Products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(50)
);
INSERT INTO Products VALUES
(501, 'Laptop'),
(502, 'Phone'),
(503, 'Tablet'),
(504, 'Monitor');

SELECT * FROM Products;

-- Employees table (for self join)
CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(50),
    manager_id INT
);
INSERT INTO Employees VALUES
(1, 'Alice', NULL),
(2, 'Bob', 1),
(3, 'Carol', 1),
(4, 'Dave', 2);

SELECT * FROM Employees;

-- Tables for set operations
CREATE TABLE A (name VARCHAR(50));
CREATE TABLE B (name VARCHAR(50));
INSERT INTO A VALUES ('Alice'), ('Bob'), ('Charlie');
INSERT INTO B VALUES ('Bob'), ('David');
```

---

## 2. SQL Joins

Joins are fundamental for combining data from multiple tables. Here’s a detailed overview of the most important join types.

### 2.1. INNER JOIN

**Definition**: Returns rows where there is a match in both tables, based on the join condition.

**Use Case**: When you only want results that exist in both tables.

**Syntax:**
```sql
SELECT t1.col, t2.col
FROM t1
INNER JOIN t2 ON t1.common_col = t2.common_col;
```

**Example:**
```sql
SELECT Customers.customer_id, Customers.name, Orders.order_id, Orders.product
FROM Customers
INNER JOIN Orders
ON Customers.customer_id = Orders.customer_id;
```
**Output:**
| customer_id | name    | order_id | product |
|-------------|---------|----------|---------|
| 1           | Alice   | 101      | Laptop  |
| 3           | Charlie | 102      | Phone   |
| 4           | David   | 103      | Tablet  |

- Only rows with matching customer_id in both tables are shown.

**Notes:**
- INNER JOIN is the most common type of join.
- If no matches are found, the row is excluded from the result.

---

### 2.2. LEFT JOIN (LEFT OUTER JOIN)

**Definition**: Returns all rows from the left table, matched rows from the right table, and NULL when there is no match.

**Use Case**: When you want all rows from the first table, even if they have no match in the second.

**Syntax:**
```sql
SELECT t1.col, t2.col
FROM t1
LEFT JOIN t2 ON t1.common_col = t2.common_col;
```

**Example:**
```sql
SELECT Customers.name, Orders.product
FROM Customers
LEFT JOIN Orders ON Customers.customer_id = Orders.customer_id;
```
**Result:** All customers, NULL in product if no order.

**Notes:**
- Useful for finding all entities in the left table and any related data in the right table.
- NULLs in right table columns indicate no match.

---

### 2.3. RIGHT JOIN (RIGHT OUTER JOIN)

**Definition**: Returns all rows from the right table, matched rows from the left table, and NULL when there is no match.

**Use Case**: When you want all rows from the second (right) table, even if they have no match in the first.

**Syntax:**
```sql
SELECT t1.col, t2.col
FROM t1
RIGHT JOIN t2 ON t1.common_col = t2.common_col;
```

**Example:**
```sql
SELECT Customers.name, Orders.product
FROM Customers
RIGHT JOIN Orders ON Customers.customer_id = Orders.customer_id;
```
**Result:** All orders, NULL in name if order has no matching customer.

> **Note:** Not supported in SQLite.

**Notes:**
- Useful for finding all entities in the right table and any related data in the left table.
- NULLs in left table columns mean no match.

---

### 2.4. FULL JOIN (FULL OUTER JOIN)

**Definition**: Returns all rows from both tables, matched where possible, NULL where not.

**Use Case**: When you want all data from both tables, matched where possible.

**Syntax:**
```sql
SELECT t1.col, t2.col
FROM t1
FULL OUTER JOIN t2 ON t1.common_col = t2.common_col;
```

**Example:**
```sql
SELECT Customers.name, Orders.product
FROM Customers
FULL OUTER JOIN Orders ON Customers.customer_id = Orders.customer_id;
```
**Result:** All customers and all orders, NULLs where no match.

> **Note:** Not supported in MySQL or SQLite.

**MySQL FULL JOIN Alternative:**
```sql
SELECT Customers.name, Orders.product
FROM Customers
LEFT JOIN Orders ON Customers.customer_id = Orders.customer_id
UNION
SELECT Customers.name, Orders.product
FROM Customers
RIGHT JOIN Orders ON Customers.customer_id = Orders.customer_id;
```

**Important:**
- FULL OUTER JOIN is only available in some databases (e.g., PostgreSQL, SQL Server).
- NULLs appear in columns where there is no match on either side.

---

### 2.5. CROSS JOIN (CARTESIAN JOIN)

**Definition**: Returns the Cartesian product—every row of the first table with every row of the second.

**Use Case**: When you want all possible combinations between two tables.

**Syntax:**
```sql
SELECT t1.col, t2.col
FROM t1
CROSS JOIN t2;
```

**Example:**
```sql
SELECT Customers.name, Products.product_name
FROM Customers
CROSS JOIN Products;
```
**Result:** 4 customers × 4 products = 16 rows.

**Notes:**
- CROSS JOINs can generate very large result sets.
- Use only when you *really* want all combinations.

---

### 2.6. SELF JOIN

**Definition**: Joins a table to itself. Useful for hierarchical or networked data.

**Use Case**: For example, finding employees and their managers.

**Syntax:**
```sql
SELECT A.col, B.col
FROM table_name A
JOIN table_name B ON A.related_col = B.related_col;
```

**Example:**
```sql
SELECT E1.name AS employee, E2.name AS manager
FROM Employees E1
LEFT JOIN Employees E2 ON E1.manager_id = E2.employee_id;
```
**Output:**
| employee | manager |
|----------|---------|
| Alice    | NULL    |
| Bob      | Alice   |
| Carol    | Alice   |
| Dave     | Bob     |

**Notes:**
- Use table aliases to distinguish between the two references to the same table.

---

### 2.7. Joining More Than Two Tables

**Definition**: You can chain multiple JOINs to merge three or more tables.

**Syntax:**
```sql
SELECT t1.col, t2.col, t3.col
FROM t1
JOIN t2 ON t1.col = t2.col
JOIN t3 ON t2.col2 = t3.col2;
```

**Example:**
```sql
SELECT Customers.name, Orders.order_id, Products.product_name
FROM Customers
JOIN Orders ON Customers.customer_id = Orders.customer_id
JOIN Products ON Orders.product_id = Products.product_id;
```
**Output:**
| name   | order_id | product_name |
|--------|----------|-------------|
| Alice  | 101      | Laptop      |
| Charlie| 102      | Phone       |
| David  | 103      | Tablet      |

**Tips:**
- Always use table aliases (`c`, `o`, `p`) for readability and to avoid ambiguity.
- The order of JOINs can affect the readability and sometimes performance but not the correctness.

---

## 3. Set Operations: UNION, UNION ALL, INTERSECT, EXCEPT

Set operations combine the result sets of multiple SELECT queries.

### 3.1. UNION

**Purpose**: Combine results of two queries, removing duplicates.

**Syntax:**
```sql
SELECT col FROM A
UNION
SELECT col FROM B;
```

**Example:**
```sql
SELECT name FROM A
UNION
SELECT name FROM B;
```
**Output:**
| name    |
|---------|
| Alice   |
| Bob     |
| Charlie |
| David   |

**Notes:**
- Both queries must have the same number of columns with compatible types.
- Removes duplicates automatically.
- Use when you want a merged, unique list.

---

### 3.2. UNION ALL

**Purpose**: Combine results of two queries, keeping duplicates.

**Syntax:**
```sql
SELECT col FROM A
UNION ALL
SELECT col FROM B;
```

**Example:**
```sql
SELECT name FROM A
UNION ALL
SELECT name FROM B;
```
**Output:**
| name    |
|---------|
| Alice   |
| Bob     |
| Charlie |
| Bob     |  ← duplicate
| David   |

**Notes:**
- Faster than UNION (no duplicate removal).
- Use when you want to preserve all rows, including duplicates.

---

### 3.3. INTERSECT

**Purpose**: Return only rows present in both queries.

**Syntax:**
```sql
SELECT col FROM A
INTERSECT
SELECT col FROM B;
```

**Example:**
```sql
SELECT name FROM A
INTERSECT
SELECT name FROM B;
```
**Output:**
| name |
|------|
| Bob  |

> **Note:** Not supported in MySQL.

**MySQL Alternative:**
```sql
SELECT name FROM A WHERE name IN (SELECT name FROM B);
-- OR
SELECT A.name FROM A JOIN B ON A.name = B.name;
```

**Notes:**
- Good for finding common elements between two sets.

---

### 3.4. EXCEPT (MINUS)

**Purpose**: Return rows from first query not present in second.

**Syntax:**
```sql
SELECT col FROM A
EXCEPT
SELECT col FROM B;
```

**Example:**
```sql
SELECT name FROM A
EXCEPT
SELECT name FROM B;
```
**Output:**
| name    |
|---------|
| Alice   |
| Charlie |

> **Note:** Use `MINUS` instead of `EXCEPT` in Oracle.  
> Not supported in MySQL.

**MySQL Alternative:**
```sql
SELECT a.name
FROM A a
LEFT JOIN B b ON a.name = b.name
WHERE b.name IS NULL;
```

**Notes:**
- Use for set difference (what's in A, but not in B).
- Removes duplicates in result.

---

### 3.5. Comparison Table

| Feature    | `UNION`   | `UNION ALL` | `INTERSECT` | `EXCEPT` / `MINUS` |
|------------|-----------|-------------|-------------|--------------------|
| Duplicates | Removed   | Kept        | Removed     | Removed            |
| Purpose    | Combine   | Combine all | Common rows | Rows in A, not B   |
| SQL Support| All       | All         | Not MySQL   | Not MySQL/Oracle   |

---

### 3.6. Database Support

| Feature   | MySQL | PostgreSQL | SQL Server | Oracle        |
|-----------|-------|------------|------------|---------------|
| UNION     | ✅    | ✅         | ✅         | ✅            |
| UNION ALL | ✅    | ✅         | ✅         | ✅            |
| INTERSECT | ❌    | ✅         | ✅         | ✅            |
| EXCEPT    | ❌    | ✅         | ✅         | ❌ (MINUS)    |

---

## 4. Common Exceptions & Notes

- **All set operations:** Number and type of columns in both queries must match. Otherwise, you'll get an error.
- **ORDER BY:** Only at the end of the final statement, not in subqueries.
- **Aliases:** Only first SELECT’s aliases are used in result.
- **MySQL:** No native `INTERSECT` or `EXCEPT`, use alternatives above.
- **NULL handling:** Set operations treat NULLs as "unknown"—NULL never equals NULL, so be careful with NULL values.
- **Performance:** `UNION ALL` is faster than `UNION`, as it skips removing duplicates.

---

## 5. Quick Reference – How To Practice

1. **Copy and run the table setup in section 1.**  
   This guarantees you have the right data for every example.
2. **Try each join and set operation query directly** to see the actual result in your DBMS.
3. **Use `SELECT * FROM ...` to inspect table contents anytime.**
4. **Experiment**: Add new rows, try NULLs, mix and match joins and set operations.

---

## More Tips and Best Practices

- **Column Names:** Use explicit column lists in SELECT for clarity and to avoid ambiguity in joins.
- **Table Aliases:** Shorten table names (e.g., `Customers c`) for complex queries.
- **Foreign Keys:** Ensure your join columns are indexed for better performance.
- **Testing:** Always test on a small dataset before running set operations or joins on large tables.
- **Documentation:** Different databases (MySQL, PostgreSQL, SQL Server, Oracle) have subtle differences in join and set operation support—consult their docs for specifics.

---

*Always check your database’s documentation for feature support and syntax.  
Use table aliases for clarity in complex queries.  
Practice with the provided queries to master joins and set operations!*

---
