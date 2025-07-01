# 1068. Product Sales Analysis I

**LeetCode Link:** [1068. Product Sales Analysis I](https://leetcode.com/problems/product-sales-analysis-i/)

## Problem Description

You are given two tables: `Sales` and `Product`.

### Sales Table

| Column Name | Type  | Description                                                        |
|-------------|-------|--------------------------------------------------------------------|
| sale_id     | int   | Unique identifier for the sale (together with year forms PK)       |
| product_id  | int   | References Product table (foreign key)                             |
| year        | int   | Year of the sale                                                   |
| quantity    | int   | Number of units sold                                               |
| price       | int   | Price per unit                                                     |

- `(sale_id, year)` is the primary key.

### Product Table

| Column Name  | Type    | Description                     |
|--------------|---------|---------------------------------|
| product_id   | int     | Primary key, unique per product |
| product_name | varchar | Human-readable name of product  |

## Task

For **each sale** in the `Sales` table, report the `product_name`, `year`, and `price`.

- Output columns: `product_name`, `year`, `price`
- Include all sales.
- The result can be in any order.

## Example

### Input

**Sales Table**

| sale_id | product_id | year | quantity | price |
|---------|------------|------|----------|-------|
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |

**Product Table**

| product_id | product_name |
|------------|-------------|
| 100        | Nokia       |
| 200        | Apple       |
| 300        | Samsung     |

### Output

| product_name | year | price |
|--------------|------|-------|
| Nokia        | 2008 | 5000  |
| Nokia        | 2009 | 5000  |
| Apple        | 2011 | 9000  |

## Explanation

- For sale_id = 1, product_id = 100, product_name = Nokia, year = 2008, price = 5000
- For sale_id = 2, product_id = 100, product_name = Nokia, year = 2009, price = 5000
- For sale_id = 7, product_id = 200, product_name = Apple, year = 2011, price = 9000

## Solution Approach

This is a basic SQL JOIN problem:

- Join `Sales` and `Product` using `product_id`
- Select the fields: `product_name`, `year`, `price`

## SQL Solution (MySQL)

```sql
SELECT
    p.product_name,
    s.year,
    s.price
FROM
    Sales s
JOIN
    Product p
ON
    s.product_id = p.product_id;
```

## Key Concepts

- **JOIN**: Matches data across tables based on a common field (`product_id`).
- **Selecting Specific Columns**: Only the required columns are shown.
- **Primary & Foreign Keys**: Ensures referential integrity.

## Visual Diagram

```
Sales Table                Product Table
+---------+------------+   +------------+--------------+
| sale_id | product_id |   | product_id | product_name |
+---------+------------+   +------------+--------------+
|    1    |    100     |   |   100      |   Nokia      |
|    2    |    100     |   |   200      |   Apple      |
|    7    |    200     |   |   300      |   Samsung    |
+---------+------------+   +------------+--------------+

         Join on product_id
```
