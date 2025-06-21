# LeetCode 1757: Recyclable and Low Fat Products

## Problem Recap

Given a table called **Products** with the following columns:

| Column Name | Type    | Description                               |
|-------------|---------|-------------------------------------------|
| product_id  | int     | Primary key, unique for each product      |
| low_fats    | enum    | Values: 'Y' (yes), 'N' (no) — Low fat?   |
| recyclable  | enum    | Values: 'Y' (yes), 'N' (no) — Recyclable?|

Each row describes a product and whether it is low fat and/or recyclable.

---

## 🧩 Problem Breakdown – LeetCode 1757

❓ **What are we trying to do?**

We are given a table `Products` with three columns:

- `product_id`
- `low_fats`
- `recyclable`

Each row represents a product and tells us:

- If it's low fat (`low_fats = 'Y'`)
- If it's recyclable (`recyclable = 'Y'`)

✅ **Goal:**  
Return `product_id` for all products where:

- `low_fats = 'Y'`
- **AND**
- `recyclable = 'Y'`

---

## Example

Given the table:

| product_id | low_fats | recyclable |
|------------|----------|------------|
| 0          | Y        | N          |
| 1          | Y        | Y          |
| 2          | N        | Y          |
| 3          | Y        | Y          |
| 4          | N        | N          |

**Output:**

| product_id |
|------------|
| 1          |
| 3          |

Only products with IDs 1 and 3 are both low fat and recyclable.

---

## Solution

### SQL Query

```sql
SELECT product_id
FROM Products
WHERE low_fats = 'Y' AND recyclable = 'Y';
```

### Explanation

- **SELECT product_id**: We want to output only the product's ID.
- **FROM Products**: Query from the Products table.
- **WHERE low_fats = 'Y' AND recyclable = 'Y'**: 
  - Filter so we only get products where both conditions are true:
    - The product is low fat.
    - The product is recyclable.

---

## Key SQL Concepts

- **WHERE Clause**: Filters rows based on conditions.
- **AND**: Combines multiple conditions; both must be true for a row to be selected.
- **ENUM Values**: Match the exact string value (e.g., `'Y'` for yes).
