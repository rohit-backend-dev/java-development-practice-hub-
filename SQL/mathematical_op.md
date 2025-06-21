# Mathematical Operations in SQL 

PostgreSQL supports a wide range of mathematical operations you can perform directly in SQL queries. These operations are useful for calculations, data analysis, and reporting. You can use them in `SELECT` queries, `UPDATE` statements, `WHERE` clauses, and more. Below are the most common mathematical operators and functions, with practical examples you can run in pgAdmin.

---

## 1. Basic Arithmetic Operators

| Operator | Description     | Example    | Result |
|----------|----------------|------------|--------|
| `+`      | Addition       | 2 + 3      | 5      |
| `-`      | Subtraction    | 5 - 2      | 3      |
| `*`      | Multiplication | 4 * 3      | 12     |
| `/`      | Division       | 10 / 2     | 5      |
| `%`      | Modulus        | 10 % 3     | 1      |

### Usage Example

```sql
SELECT 10 + 5 AS addition,
       10 - 5 AS subtraction,
       10 * 5 AS multiplication,
       10 / 5 AS division,
       10 % 5 AS modulus;
```

**Output:**
| addition | subtraction | multiplication | division | modulus |
|----------|-------------|----------------|----------|---------|
| 15       | 5           | 50             | 2        | 0       |

---

## 2. Using Mathematical Operations on Table Columns

Suppose you have a table named `products`:

```sql
CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(50),
    price NUMERIC(10,2),
    quantity INT
);

INSERT INTO products (product_name, price, quantity) VALUES
('Book', 150.00, 3),
('Pen', 10.50, 20),
('Bag', 500.00, 2);
```

### Example: Calculate Total Price for Each Product

```sql
SELECT product_name,
       price,
       quantity,
       price * quantity AS total_price
FROM products;
```

**Output:**
| product_name | price  | quantity | total_price |
|--------------|--------|----------|-------------|
| Book         | 150.00 | 3        | 450.00      |
| Pen          | 10.50  | 20       | 210.00      |
| Bag          | 500.00 | 2        | 1000.00     |

---

## 3. Mathematical Functions

### Common PostgreSQL Math Functions

| Function         | Description                     | Example                        | Result   |
|------------------|---------------------------------|--------------------------------|----------|
| `ABS(x)`         | Absolute value                  | `ABS(-5)`                     | 5        |
| `CEIL(x)`        | Smallest integer ≥ x            | `CEIL(4.2)`                    | 5        |
| `FLOOR(x)`       | Largest integer ≤ x             | `FLOOR(4.7)`                   | 4        |
| `ROUND(x, n)`    | Round to n decimal places       | `ROUND(12.345, 2)`             | 12.35    |
| `POWER(x, y)`    | x raised to the power of y      | `POWER(2, 3)`                  | 8        |
| `SQRT(x)`        | Square root                     | `SQRT(16)`                     | 4        |
| `EXP(x)`         | Exponential (e^x)               | `EXP(1)`                       | 2.718... |
| `LN(x)`          | Natural logarithm (base e)      | `LN(10)`                       | 2.302... |
| `LOG(b, x)`      | Logarithm base b                | `LOG(10, 1000)`                | 3        |
| `MOD(x, y)`      | Remainder of x divided by y     | `MOD(10, 4)`                   | 2        |
| `RANDOM()`       | Random value [0,1)              | `RANDOM()`                     | 0.567... |
| `TRUNC(x, n)`    | Truncate to n decimal places    | `TRUNC(12.789, 1)`             | 12.7     |

### Example Usage

```sql
SELECT ABS(-10) AS abs_val,
       CEIL(5.2) AS ceil_val,
       FLOOR(5.8) AS floor_val,
       ROUND(10.678, 1) AS round_val,
       POWER(3, 2) AS squared,
       SQRT(25) AS square_root,
       EXP(1) AS e_power_1,
       LN(100) AS ln_val,
       LOG(10, 1000) AS log10_val,
       MOD(17, 4) AS remainder,
       TRUNC(12.789, 1) AS truncated,
       RANDOM() AS random_number;
```

---

## 4. Example: Conditional Calculations

You can combine math operations with `CASE` for conditional calculations.

### Example: Discount Calculation

```sql
SELECT product_name,
       price,
       quantity,
       CASE
           WHEN quantity >= 10 THEN price * quantity * 0.9  -- 10% discount
           ELSE price * quantity
       END AS final_amount
FROM products;
```

---

## 5. Using Math in WHERE Clauses

Find products with total price (price × quantity) over 300.

```sql
SELECT product_name, price, quantity
FROM products
WHERE (price * quantity) > 300;
```

---

## 6. Aggregate with Math

Find the average and total value of all products.

```sql
SELECT SUM(price * quantity) AS total_inventory_value,
       AVG(price * quantity) AS avg_product_value
FROM products;
```

---

## 7. Using Mathematical Functions with Dates

For date arithmetic, you can use interval calculations.

```sql
-- Add 7 days to current date
SELECT CURRENT_DATE + INTERVAL '7 days' AS next_week;
```

---

## 8. More Examples

### Calculate percentage:

```sql
SELECT product_name,
       quantity,
       (quantity * 100.0) / SUM(quantity) OVER () AS percent_of_total
FROM products;
```

---

## Summary Table

| Operation                      | Example                               | Description                      |
|---------------------------------|---------------------------------------|----------------------------------|
| Addition                       | price + 100                           | Add 100 to each price            |
| Multiplication                 | price * quantity                      | Total price for each product     |
| Round to 2 decimals            | ROUND(price, 2)                       |                                   |
| Square root                    | SQRT(price)                           |                                   |
| Modulus                        | quantity % 3                          | Remainder after division by 3    |
| Power                          | POWER(quantity, 2)                    | Quantity squared                 |
| Percentage of total            | (quantity * 100.0) / SUM(quantity)... | Row as percentage of total qty   |
| Absolute value                 | ABS(price - 100)                      | Distance from 100                |

---

## Notes

- You can use mathematical expressions in `SELECT`, `WHERE`, `ORDER BY`, `GROUP BY`, and `HAVING` clauses.
- Always enclose operations in parentheses `()` to ensure correct order of calculations.
- For decimal division, at least one value must be decimal (e.g., `10.0 / 3`).

---
