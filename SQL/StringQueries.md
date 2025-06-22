# 📚 SQL String Functions Guide


## Sample Table: `Customers`

Let's use the following table for all examples:

```sql
CREATE TABLE Customers (
  customer_id INT,
  name VARCHAR(50),
  city VARCHAR(50)
);

INSERT INTO Customers VALUES
(1, 'Alice', 'Delhi'),
(2, 'Bob', 'Mumbai'),
(3, 'Charlie', 'Bangalore'),
(4, 'David', 'Kolkata');

SELECT * FROM Customers;
```

---

## Function Reference

### 1️⃣ `LEFT(string, n)`

- **Definition:** Returns the first `n` characters from the string.

#### 🔍 SQL Syntax

```sql
LEFT(string, n)
```

#### 🧾 Example

```sql
SELECT name, LEFT(name, 3) AS first_3_chars
FROM Customers;
```

#### 📋 Output

| name    | first_3_chars |
| ------- | ------------- |
| Alice   | Ali           |
| Bob     | Bob           |
| Charlie | Cha           |
| David   | Dav           |

#### 💡 Use Cases

- Extracting area codes from phone numbers.
- Displaying only the initials/first few characters of a name.

---

### 2️⃣ `RIGHT(string, n)`

- **Definition:** Returns the last `n` characters from the string.

#### 🔍 SQL Syntax

```sql
RIGHT(string, n)
```

#### 🧾 Example

```sql
SELECT name, RIGHT(name, 2) AS last_2_chars
FROM Customers;
```

#### 📋 Output

| name    | last_2_chars |
| ------- | ------------ |
| Alice   | ce           |
| Bob     | ob           |
| Charlie | ie           |
| David   | id           |

#### 💡 Use Cases

- Getting file extensions from filenames.
- Showing the last digits of an account number for privacy.

---

### 3️⃣ `REVERSE(string)`

- **Definition:** Reverses the entire string.

#### 🔍 SQL Syntax

```sql
REVERSE(string)
```

> **Note:** Supported in PostgreSQL, SQL Server, and MySQL (with slight syntax differences).

#### 🧾 Example

```sql
SELECT name, REVERSE(name) AS reversed_name
FROM Customers;
```

#### 📋 Output

| name    | reversed_name |
| ------- | ------------- |
| Alice   | ecilA         |
| Bob     | boB           |
| Charlie | eilrahC       |
| David   | divaD         |

#### 💡 Use Cases

- Checking for palindromes.
- Obfuscating text for puzzles or basic security.

---

### 4️⃣ `REPLACE(string, from_substring, to_substring)`

- **Definition:** Replaces **all** occurrences of a substring with another substring.

#### 🔍 SQL Syntax

```sql
REPLACE(string, from_substring, to_substring)
```

#### 🧾 Example

```sql
SELECT name, REPLACE(name, 'a', '@') AS modified_name
FROM Customers;
```

#### 📋 Output

| name    | modified_name |
| ------- | ------------- |
| Alice   | Alice         |
| Bob     | Bob           |
| Charlie | Ch@rlie       |
| David   | D@vid         |

#### 💡 Use Cases

- Masking sensitive information.
- Formatting data (e.g., replacing spaces with underscores).

---

### 5️⃣ `SPLIT_PART(string, delimiter, position)`

- **Definition:** Splits `string` by `delimiter` and returns the `position`-th part (1-based index).

> **Note:** Available in PostgreSQL. In SQL Server, use `STRING_SPLIT`, and in MySQL, use `SUBSTRING_INDEX`.

#### 🔍 SQL Syntax

```sql
SPLIT_PART(string, delimiter, position)
```

#### 🧾 Example

First, add a new column for demonstration:

```sql
ALTER TABLE Customers ADD COLUMN full_name TEXT;
UPDATE Customers
SET full_name = CONCAT(name, '-', city);
```

##### Example Query

```sql
SELECT 
  full_name,
  SPLIT_PART(full_name, '-', 1) AS first_part,
  SPLIT_PART(full_name, '-', 2) AS second_part
FROM Customers;
```

#### 📋 Output

| full_name         | first_part | second_part |
| ----------------- | ---------- | ----------- |
| Alice-Delhi       | Alice      | Delhi       |
| Bob-Mumbai        | Bob        | Mumbai      |
| Charlie-Bangalore | Charlie    | Bangalore   |
| David-Kolkata     | David      | Kolkata     |

#### 💡 Use Cases

- Extracting first/last names or city from a concatenated field.
- Parsing data from delimited logs.

---

## Summary Table of Functions

| Function                      | Description                  | Example                            | Output     |
|-------------------------------|------------------------------|------------------------------------|------------|
| `LEFT(str, n)`                | First n characters           | `LEFT('Charlie', 3)`               | `'Cha'`    |
| `RIGHT(str, n)`               | Last n characters            | `RIGHT('Charlie', 3)`              | `'lie'`    |
| `REVERSE(str)`                | Reverses the string          | `REVERSE('David')`                 | `'divaD'`  |
| `REPLACE(str, 'a', '@')`      | Replace 'a' with '@'         | `REPLACE('David', 'a', '@')`       | `'D@vid'`  |
| `SPLIT_PART(str, d, pos)`     | Get part by delimiter        | `SPLIT_PART('Bob-Mumbai', '-', 2)` | `'Mumbai'` |

---

## Best Practices & Notes

- **Performance:** Functions like `REPLACE` and `SPLIT_PART` can impact performance on large datasets.
- **Compatibility:** Not all functions are supported in every SQL flavor (e.g., `SPLIT_PART` is PostgreSQL-specific).
- **Index Usage:** Applying these functions to indexed columns may prevent the use of indexes, affecting query performance.
- **Case Sensitivity:** String functions are often case-sensitive, depending on collation settings.

---

## Practice Exercises

1. **Extract Initials:**  
   Write a query to display the first letter of each customer's name and city.

2. **Mask Names:**  
   Replace all vowels in `name` with asterisks (`*`).

3. **Reverse Cities:**  
   Show the reversed version of each city name.

4. **Get City Codes:**  
   Display the last three letters of each city.

5. **Split and Recombine:**  
   Using `full_name`, extract the name and city and recombine as `city, name`.

---

## Further Exploration

Would you like to learn about more advanced string functions?

- `OVERLAY()`: Replace a substring at a specific position.
- `LPAD()` / `RPAD()`: Pad text to a specified length.
- `FORMAT()`: Format numbers or strings for display.

Let me know your interests or ask for **solutions** to the exercises above!

---
