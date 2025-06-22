# Subqueries in SQL

## What is a Subquery?

A **subquery** is a query nested inside another SQL query. Subqueries can be placed in various clauses, such as `SELECT`, `FROM`, `WHERE`, and `HAVING`. They are powerful tools for breaking down complex problems into manageable parts.

---

## Types of Subqueries

- **Correlated Subquery**: Depends on the outer query for its values.
- **Uncorrelated Subquery**: Independent of the outer query; runs once and returns a value or set of values.

---

## What is an Uncorrelated Subquery?

An **uncorrelated subquery** is a SELECT statement that does not reference any columns from the outer/main query. It is self-contained and executes only once for the entire outer query.

### Key Characteristics

| Feature        | Description                                               |
| -------------- | -------------------------------------------------------- |
| Independence   | Does not use outer query columns                         |
| Execution      | Runs once before the outer query                         |
| Return Values  | Can return a single value or a set of values (for `IN`)  |
| Usage          | Typically used with aggregate functions (`MIN`, `MAX`, etc.) or to filter lists |

---

## Syntax

```sql
SELECT column1
FROM table
WHERE column2 = (SELECT column FROM another_table WHERE condition);
```

---

## Examples

### 1. Get Customers from the Same City as ‘Charlie’

```sql
SELECT * 
FROM Customers
WHERE city = (
    SELECT city 
    FROM Customers 
    WHERE name = 'Charlie'
);
```
#### Explanation:
- The subquery fetches "Bangalore" (Charlie's city).
- The main query retrieves all customers in "Bangalore".

---

### 2. Get All Orders for the Most Expensive Product (Lexically Last)

```sql
SELECT * 
FROM Orders
WHERE product = (
    SELECT MAX(product)
    FROM Orders
);
```
- `MAX(product)` gets the lexically largest product (e.g., "Tablet").
- The outer query fetches all orders for that product.

---

### 3. Show Customers with the Smallest Customer ID

```sql
SELECT * 
FROM Customers
WHERE customer_id = (
    SELECT MIN(customer_id)
    FROM Customers
);
```
- Subquery returns `1` (the minimum customer_id).
- Main query returns the customer with that ID.

---

### 4. Use `IN` for Multiple Values

When the subquery can return multiple values, use the `IN` operator instead of `=`.

```sql
SELECT * 
FROM Customers
WHERE city IN (
    SELECT DISTINCT city
    FROM Orders o
    JOIN Customers c ON o.customer_id = c.customer_id
);
```
- Subquery returns all cities that have at least one order.
- The outer query lists all customers in those cities.

---

## Common Mistakes

- **Using `=` with multi-row subqueries**:  
  If the subquery returns more than one row, use `IN` instead of `=` to avoid errors.
- **Confusing correlated and uncorrelated subqueries**:  
  Make sure your subquery does not depend on columns from the outer query if you intend it to be uncorrelated.

---

## When to Use Uncorrelated Subqueries

- When you need a single value for comparison (e.g., `MIN`, `MAX`, `AVG`).
- When you want to filter using a list of values (`IN`).
- When the subquery does not need data from the outer query.

---

## Comparison: Uncorrelated vs Correlated Subqueries

| Feature                  | Uncorrelated Subquery           | Correlated Subquery                     |
|--------------------------|---------------------------------|-----------------------------------------|
| Dependency               | Independent of outer query      | References outer query columns          |
| Execution                | Runs once                       | Runs once per row of outer query        |
| Use case                 | Aggregate checks, static lists  | Row-by-row filtering                    |
| Performance              | Usually faster                  | Can be slower for large datasets        |

---

## Advanced Examples with Aggregates

### 1. Find Customers Who Placed the Most Orders

```sql
SELECT * FROM Customers
WHERE customer_id = (
    SELECT customer_id
    FROM Orders
    GROUP BY customer_id
    ORDER BY COUNT(*) DESC
    LIMIT 1
);
```
- Subquery returns the `customer_id` with the most orders.
- Outer query fetches that customer.

### 2. Show Orders Above Average Value

```sql
SELECT * FROM Orders
WHERE amount > (
    SELECT AVG(amount)
    FROM Orders
);
```
- Subquery calculates the average order amount.
- Outer query shows orders greater than that average.

---

## Best Practices

- Use uncorrelated subqueries for efficiency when possible.
- Prefer `JOIN` for performance in large datasets if the same result can be achieved.
- Always use `IN` or `ANY`/`ALL` if the subquery returns multiple rows.
- Use meaningful aliases for readability.

---

## Summary Table

| Operator | Use with Subquery     | Example Use Case                        |
|----------|----------------------|-----------------------------------------|
| =        | Single value         | Find customer with minimum ID           |
| IN       | Multiple values      | List customers from certain cities      |
| EXISTS   | Correlated subqueries| Check if related records exist          |
| >, <, >= | Single value         | Orders above average value              |

---
