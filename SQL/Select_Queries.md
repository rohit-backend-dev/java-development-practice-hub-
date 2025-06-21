# 🏷️ SQL SELECT Queries Documentation

The `SELECT` statement is the foundation of SQL, used to fetch data from your database. Below, you'll find **all common types of SELECT queries**, explained in clear language, with syntax, examples, and results. The documentation uses your provided `customers` table for all sample queries, and every query from your original example is included and explained.

---

## 📝 Sample Table: `customers`

```sql
CREATE TABLE customers (
  customer_id SERIAL PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email VARCHAR(50) UNIQUE NOT NULL,
  city VARCHAR(30),
  state CHAR(2),
  phone VARCHAR(15)
);

INSERT INTO customers (first_name, last_name, email, city, state, phone) VALUES
('Ravi', 'Kumar', 'ravi.kumar@gmail.com', 'Delhi', 'DL', '9876543210'),
('Anjali', 'Sharma', 'anjali.sharma@gmail.com', 'Mumbai', 'MH', '9823456789'),
('Amit', 'Singh', 'amit.singh@gmail.com', 'Lucknow', 'UP', '9123456780'),
('Priya', 'Verma', 'priya.verma@yahoo.com', 'Bhopal', 'MP', '9845672310'),
('Raj', 'Joshi', 'raj.joshi@hotmail.com', 'Ahmedabad', 'GJ', '9988776655'),
('Sneha', 'Desai', 'sneha.desai@gmail.com', 'Pune', 'MH', '9765432109'),
('Karan', 'Mehta', 'karan.mehta@gmail.com', 'Chandigarh', 'CH', '9654321078'),
('Neha', 'Chopra', 'neha.chopra@gmail.com', 'Amritsar', 'PB', '9786543210'),
('Arjun', 'Kapoor', 'arjun.kapoor@gmail.com', 'Kolkata', 'WB', '9832104567'),
('Pooja', 'Rani', 'pooja.rani@gmail.com', 'Jaipur', 'RJ', '9912345678'),
('Rahul', 'Tiwari', 'rahul.tiwari@gmail.com', 'Varanasi', 'UP', '9753108642'),
('Divya', 'Gupta', 'divya.gupta@gmail.com', 'Indore', 'MP', '9887654321'),
('Manoj', 'Patel', 'manoj.patel@gmail.com', 'Surat', 'GJ', '9898989898'),
('Ritu', 'Yadav', 'ritu.yadav@gmail.com', 'Noida', 'UP', '9865321470'),
('Saurabh', 'Jain', 'saurabh.jain@gmail.com', 'Nagpur', 'MH', '9900123456'),
('Meena', 'Aggarwal', 'meena.aggarwal@gmail.com', 'Ludhiana', 'PB', '9890123789'),
('Nikhil', 'Bansal', 'nikhil.bansal@gmail.com', 'Gurgaon', 'HR', '9871012345'),
('Ayesha', 'Khan', 'ayesha.khan@gmail.com', 'Hyderabad', 'TS', '9845012345'),
('Vikram', 'Reddy', 'vikram.reddy@gmail.com', 'Chennai', 'TN', '9833012345'),
('Komal', 'Shah', 'komal.shah@gmail.com', 'Bangalore', 'KA', '9822012345');
```

---

## 1. Select All Columns

```sql
SELECT * FROM customers;
```
**Explanation:**  
Fetches all columns and all rows from the `customers` table.

---

## 2. Select Specific Columns (Single Table)

```sql
SELECT email, first_name FROM customers;
```
**Explanation:**  
Returns only the `email` and `first_name` columns for every customer.

---

## 3. Filtering Rows with WHERE

```sql
SELECT first_name FROM customers 
WHERE first_name= 'Ritu';
```
**Explanation:**  
Shows the first name for customers whose first name is 'Ritu'.

---

## 4. WHERE with Multiple Conditions

```sql
SELECT * FROM customers
WHERE email = 'ravi.kumar@gmail.com' OR first_name = 'Ravi';
```
**Explanation:**  
Returns all columns for customers whose email is 'ravi.kumar@gmail.com' or whose first name is 'Ravi'.

---

## 5. WHERE by State

```sql
SELECT * FROM customers
WHERE state='UP';
```
**Explanation:**  
Returns all customers from the state 'UP' (Uttar Pradesh).

---

## 6. WHERE with AND (Multiple Filters)

```sql
SELECT * FROM customers
WHERE state='UP'
AND city='Noida';
```
**Explanation:**  
Returns all customers located in both the state 'UP' and the city 'Noida'.

---

## 7. Logical Operators and Functions

```sql
SELECT first_name, LENGTH(first_name) AS first_name_length
FROM customers
WHERE LENGTH(first_name) > 4;
```
**Explanation:**  
Shows customers whose first name is longer than 4 characters, and also shows the length of their first name.

```sql
SELECT first_name, LENGTH(first_name) AS first_name_length
FROM customers
WHERE LENGTH(first_name) >= 4;
```
**Explanation:**  
First names with length greater than or equal to 4.

```sql
SELECT first_name, LENGTH(first_name) AS first_name_length
FROM customers
WHERE LENGTH(first_name) <= 4;
```
**Explanation:**  
First names with length less than or equal to 4.

---

## 8. IN Operator

```sql
SELECT first_name , last_name FROM customers
WHERE first_name IN ('Komal','Shah');
```
**Explanation:**  
Returns the first and last names of customers whose first name is either 'Komal' or 'Shah'.

---

## 9. Other Useful SELECT Query Features

Below are other common SELECT query features.  
*(You can use them in your `customers` table as shown in the examples with a `students` table.)*

### a. NOT IN

```sql
SELECT * FROM customers
WHERE state NOT IN ('UP', 'MH');
```
**Explanation:**  
Returns customers NOT from 'UP' or 'MH'.

---

### b. LIKE (Pattern Matching)

**Starts With Pattern**

```sql
SELECT * FROM customers
WHERE first_name LIKE 'A%';
```
Explanation:  
Finds customers whose first name starts with "A".

- The `%` symbol matches any sequence of characters (including zero characters) after "A".
- Examples matched: `"A"`, `"Anjali"`, `"Amit"`, `"Arjun"`.

---

- **Exact Character Count Pattern**

```sql
SELECT * FROM customers
WHERE first_name LIKE 'A_';
```
**Explanation:**  
Finds customers whose first name is exactly two characters long and starts with "A".

- The `_` symbol matches exactly one character.
- Examples matched: `"Al"`, `"An"`, `"Aj"`.
- Examples **not** matched: `"Amit"`, `"A"`.


- **Ends with a character:**
  ```sql
  SELECT * FROM customers WHERE first_name LIKE '%a';
  ```
  Finds names ending with "a" (e.g., `"Riya"`, `"Sneha"`).

 - **Contains a substring:**
  ```sql
  SELECT * FROM customers WHERE first_name LIKE '%it%';
  ```
  Finds names containing "it" anywhere (e.g., `"Amit"`, `"Ritu"`).

---

---

### c. BETWEEN (Range)

```sql
SELECT * FROM customers
WHERE customer_id BETWEEN 5 AND 10;
```
**Explanation:**  
Finds customers with IDs between 5 and 10 (inclusive).

---

### d. ORDER BY (Sorting)

```sql
SELECT first_name, city FROM customers
ORDER BY first_name ASC;
```
**Explanation:**  
Lists customers ordered alphabetically by first name.

---

### e. LIMIT (Limiting Results)

```sql
SELECT * FROM customers
LIMIT 5;
```
**Explanation:**  
Shows only the first 5 customers.

---

### f. DISTINCT (Unique Values)

```sql
SELECT DISTINCT city FROM customers;
```
**Explanation:**  
Lists each city only once, even if more than one customer is from that city.

---

### g. NULL Checks

Suppose some customers have a missing phone number:

```sql
SELECT * FROM customers
WHERE phone IS NULL;
```
**Explanation:**  
Shows customers without a phone number.

---

### h. Concatenation and Column Aliases

```sql
SELECT first_name || ' ' || last_name AS full_name, email
FROM customers;
```
**Explanation:**  
Shows customers' full name (first and last together) and their email.

---

### i. OFFSET (Pagination/Skip Rows)

```sql
SELECT * FROM customers
ORDER BY customer_id
OFFSET 5 LIMIT 5;
```
**Explanation:**  
Skips the first 5 customers and returns the next 5. Useful for pagination.

---

### j. EXISTS (Check for Existence of Related Data)

Suppose you have an `orders` table:

```sql
SELECT first_name FROM customers
WHERE EXISTS (
  SELECT 1 FROM orders WHERE orders.customer_id = customers.customer_id
);
```
**Explanation:**  
Returns first names of customers who have at least one order.

---

## 10. Aggregate Functions

```sql
SELECT COUNT(*) FROM customers;
```
**Explanation:**  
Counts total customers.

```sql
SELECT AVG(LENGTH(first_name)) FROM customers;
```
**Explanation:**  
Calculates the average length of all first names.

```sql
SELECT MIN(customer_id), MAX(customer_id) FROM customers;
```
**Explanation:**  
Shows the smallest and largest customer ID.

---

## 11. GROUP BY (Grouping Results)

```sql
SELECT city, COUNT(*) AS num_customers
FROM customers
GROUP BY city;
```
**Explanation:**  
Shows how many customers are from each city.

---

## 12. HAVING (Filter Groups)

```sql
SELECT state, COUNT(*) AS num_customers
FROM customers
GROUP BY state
HAVING COUNT(*) > 2;
```
**Explanation:**  
Shows only states with more than 2 customers.

---

## 13. UNION (Combining Results)

```sql
SELECT first_name FROM customers WHERE state = 'UP'
UNION
SELECT first_name FROM customers WHERE city = 'Delhi';
```
**Explanation:**  
Lists all first names from UP and all first names from Delhi (no duplicates).

---

## 14. Subqueries

```sql
SELECT first_name FROM customers
WHERE customer_id = (SELECT MAX(customer_id) FROM customers);
```
**Explanation:**  
Shows the first name of the customer with the highest ID.

---

## 15. JOINs (Multiple Tables)

Suppose you have another table:

```sql
CREATE TABLE orders (
  order_id SERIAL PRIMARY KEY,
  customer_id INT,
  order_date DATE
);
```

```sql
SELECT customers.first_name, orders.order_date
FROM customers
JOIN orders ON customers.customer_id = orders.customer_id;
```
**Explanation:**  
Shows each customer's first name with their order date.

---

## 16. CASE (Conditional Logic)

```sql
SELECT first_name,
  CASE
    WHEN city = 'Delhi' THEN 'North'
    WHEN city = 'Chennai' THEN 'South'
    ELSE 'Other'
  END AS region
FROM customers;
```
**Explanation:**  
Shows each customer's first name and their region, based on their city.

---

## 17. Window Functions (ROW_NUMBER, RANK, etc.)

```sql
SELECT first_name, city,
  ROW_NUMBER() OVER (ORDER BY city) AS row_num
FROM customers;
```
**Explanation:**  
Assigns a unique row number to each customer, ordered by city. Useful for advanced reporting and analytics.

---

# ✅ Summary Table
**Use this cheat sheet as a quick reference to write any SELECT query for your tables!**

| Query Type                   | Example Syntax                                 | Description                            |
|------------------------------|-----------------------------------------------|----------------------------------------|
| All columns                  | `SELECT * FROM customers`                     | Every column, every row                |
| Specific columns             | `SELECT email, first_name FROM customers`     | Only chosen columns                    |
| Filtering                    | `... WHERE state = 'UP'`                      | Only matching rows                     |
| Sorting                      | `... ORDER BY first_name ASC`                 | Sorts results                          |
| Limiting                     | `... LIMIT 5`                                 | Only first 5 rows                      |
| Offset/Pagination            | `... OFFSET 5 LIMIT 5`                        | Skip rows for paging                   |
| Renaming columns             | `... AS full_name`                            | Sets output column names               |
| Removing duplicates          | `SELECT DISTINCT city ...`                    | Each value only once                   |
| Multiple conditions          | `... WHERE state = 'UP' AND city = 'Noida'`   | Combines filters                       |
| Pattern matching             | `... WHERE first_name LIKE 'A%'`              | Wildcard search                        |
| Null checks                  | `... WHERE phone IS NULL`                     | Finds empty values                     |
| Set membership               | `... WHERE state IN ('UP', 'MH')`             | Value must be listed                   |
| Range selection              | `... WHERE customer_id BETWEEN 5 AND 10`      | Value must be within a range           |
| Aggregates                   | `SELECT AVG(LENGTH(first_name)) ...`          | Math on columns                        |
| Grouping                     | `... GROUP BY city`                           | Summarizes by group                    |
| Filtering groups             | `... HAVING COUNT(*) > 2`                     | Only certain group results             |
| Combining results            | `... UNION ...`                               | Merges results from queries            |
| Subqueries                   | `... WHERE customer_id = (SELECT MAX(...) )`  | Query inside query                     |
| JOINs                        | `... JOIN ... ON ...`                         | Combines tables                        |
| Exists                       | `... WHERE EXISTS (SELECT 1 FROM ...)`        | Checks for related data                |
| Conditionals                 | `... CASE ... END`                            | If-then logic in results               |
| Window functions             | `ROW_NUMBER() OVER (ORDER BY ...)`            | Advanced analytics/reporting           |

---

