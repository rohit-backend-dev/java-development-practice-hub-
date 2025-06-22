# 584. Find Customer Referee

**Difficulty:** Easy  
**Topics:** SQL

## Problem Description

You are given a table named `Customer` that contains information about customers and who referred them. 

The table structure is:

| Column Name | Type    | Description                                            |
|-------------|---------|-------------------------------------------------------|
| id          | int     | Primary key, unique customer ID                       |
| name        | varchar | The name of the customer                              |
| referee_id  | int     | The ID of the customer who referred them (nullable)   |

Each row in the table represents a customer, their name, and the ID of the customer who referred them (if any).

---

## Task

**Find the names of customers who were _not_ referred by the customer with `id = 2`.**

Return the result as a table with a single column `name`.

---

## Example

Given the `Customer` table:

| id | name | referee_id |
|----|------|------------|
| 1  | Will | null       |
| 2  | Jane | null       |
| 3  | Alex | 2          |
| 4  | Bill | null       |
| 5  | Zack | 1          |
| 6  | Mark | 2          |

**Output:**

| name |
|------|
| Will |
| Jane |
| Bill |
| Zack |

- "Alex" and "Mark" are excluded because they were referred by customer `id = 2`.

---

## Solution

To solve this, select all customers whose `referee_id` is either not 2 or is `null` (not referred by anyone):

```sql
SELECT 
    name
FROM Customer
WHERE
    referee_id IS NULL OR referee_id != 2
```

Alternatively, you can use the `IFNULL` function to handle `null` values:

```sql
SELECT 
    name
FROM Customer
WHERE
    IFNULL(referee_id, 0) != 2
```

Both queries will return the required list of customer names not referred by customer with `id = 2`.

## How It Works:
IFNULL(referee_id, 0): replaces NULL with 0, so all NULL values are treated as "not 2".

Safe single condition to exclude those with referee_id = 2.
---

## Explanation

- `referee_id IS NULL`: Selects customers who were not referred by anyone.
- `referee_id != 2`: Selects customers whose referrer is not customer 2.
- Combining these with `OR` ensures we exclude only those referred by customer 2.
