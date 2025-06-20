# SQL Table Constraints 

**Table constraints** are rules that protect your data’s accuracy and relationships in SQL databases. Think of them as the “rules” that keep your tables organized and meaningful.

---

## 1. PRIMARY KEY

- **What it does:** Uniquely identifies each row in a table.
- **How it works:**
  - Only ONE primary key allowed per table (can be one or more columns, but ONE key).
  - Cannot be NULL (must have a value).
  - Must be unique (no two rows have the same primary key value).
- **Why important?**  
  Helps you find, update, or delete specific rows easily. Prevents duplicate or missing records.

**Example:**
```sql
CREATE TABLE Users (
  UserID INT PRIMARY KEY,
  Name VARCHAR(50)
);
```
| UserID | Name   |
|--------|--------|
| 1      | Tom    |
| 2      | Anna   |

- `UserID` is the PRIMARY KEY, so there can’t be two users with the same UserID, and every user must have a UserID.

---

## 2. FOREIGN KEY

- **What it does:** Links two tables together by connecting a column from one table to the PRIMARY KEY in another.
- **How it works:**
  - Points from one table (child) to another table’s primary key (parent).
  - Ensures values in the child table exist in the parent table.
  - The FOREIGN KEY constraint prevents invalid data from being inserted into the foreign key column , because it has to be one of the values contained in the parent table.
- **Why important?**  
  Keeps data consistent across tables (e.g., every order must be for a real customer).

**Example:**
```sql
CREATE TABLE Customers (
  CustomerID INT PRIMARY KEY,
  Name VARCHAR(50)
);

CREATE TABLE Orders (
  OrderID INT PRIMARY KEY,
  CustomerID INT,
  OrderDate DATE,
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
```
| Customers         | Orders                        |
|-------------------|------------------------------|
| CustomerID | Name| OrderID | CustomerID | Date   |
| 1          | Tom | 1001    |     1      | ...    |
| 2        | Anna | 1002    |     2      | ...    |

- `Orders.CustomerID` is a **foreign key**: it must match a `CustomerID` from `Customers`.
- If you try to create an order for a non-existent CustomerID, it will fail.

**Why FOREIGN KEY?**  
- Maintains relationships (e.g., an order cannot exist without a valid customer).
- Helps prevent orphaned or invalid data.

---

## 3. UNIQUE

- **What it does:** Makes sure all values in a column (or columns) are different.
- **How it works:**
  - Allows NULL by default (typically 1 NULL per unique column, but check your DBMS).
  - Can be used on more than one column in a table.
- **Why important?**  
  Prevents duplicate entries for things like emails or usernames.

**Example:**
```sql
CREATE TABLE Employees (
  EmpID INT PRIMARY KEY,
  Email VARCHAR(100) UNIQUE
);
```
| EmpID | Email         |
|-------|--------------|
| 1     | tom@email.com|
| 2     | anna@email.com|

- Two employees cannot have the same email.

---

## 4. NOT NULL

- **What it does:** Makes sure a column always has a value.
- **How it works:**
  - Prevents inserting or updating a row without a value in this column.
- **Why important?**  
  Ensures essential information is never missing.

**Example:**
```sql
CREATE TABLE Products (
  ProductID INT PRIMARY KEY,
  Name VARCHAR(50) NOT NULL,
  Price DECIMAL(10,2) NOT NULL
);
```
| ProductID | Name     | Price |
|-----------|----------|-------|
| 1         | Laptop   | 799.99|
| 2         | Keyboard | 49.99 |

- You MUST provide a name and price for every product.

---

## 5. CHECK

- **What it does:** Validates that column values meet a condition.
- **How it works:**
  - Only allows data that passes the test (e.g., age > 18).
- **Why important?**  
  Keeps data reasonable and within acceptable bounds.

**Example:**
```sql
CREATE TABLE Students (
  StudentID INT PRIMARY KEY,
  Age INT CHECK (Age >= 18)
);
```
| StudentID | Age |
|-----------|-----|
| 1         | 20  |
| 2         | 17  | ❌ (would fail)

- You can’t insert a student with Age < 18.

---

## 6. DEFAULT

- **What it does:** Sets a default value if none is provided.
- **How it works:**
  - If you insert a row and omit this column, SQL uses the default.
- **Why important?**  
  Simplifies data entry and ensures reasonable values.

**Example:**
```sql
CREATE TABLE Orders (
  OrderID INT PRIMARY KEY,
  Status VARCHAR(20) DEFAULT 'Pending'
);
```
- If you don’t specify `Status`, it will be `'Pending'`.

---

## 7. AUTO_INCREMENT (or SERIAL/IDENTITY)

- **What it does:** Automatically increases a numeric value for each new row (commonly used for keys).
- **Why important?**  
  Saves you from manually tracking and inputting unique IDs.

**Example:**
```sql
CREATE TABLE Tickets (
  TicketID INT AUTO_INCREMENT PRIMARY KEY,
  Issue VARCHAR(255)
);
```
- Each new ticket gets a unique TicketID automatically.

---

## Quick Reference Table

| Constraint     | What it Ensures                       | NULL Allowed? | Multiple per Table? | Typical Use                         |
|----------------|--------------------------------------|---------------|---------------------|-------------------------------------|
| PRIMARY KEY    | Unique row identification             | ❌            | ❌                  | Row ID, User ID                     |
| FOREIGN KEY    | Value exists in another table         | ✅/❌         | ✅                  | Linking orders to customers         |
| UNIQUE         | No duplicate values                   | ✅ (usually)  | ✅                  | Email, username                     |
| NOT NULL       | No empty values                       | ❌            | ✅                  | Must-have fields                    |
| CHECK          | Value meets a rule                    | ✅/❌         | ✅                  | Age ≥ 18, salary > 0                |
| DEFAULT        | Value if none is supplied             | ✅            | ✅                  | Status, creation date               |
| AUTO_INCREMENT | Auto-numbering                        | ❌            | ✅                  | Primary key columns                 |

---

## How to Decide Which Constraint to Use

- **PRIMARY KEY:** For a column/columns that must identify each row uniquely.
- **FOREIGN KEY:** When you want to link rows in this table to rows in another (enforcing relationships).
- **UNIQUE:** When a value must not be repeated (like emails, phone numbers).
- **NOT NULL:** When a column is always required.
- **CHECK:** When a column must obey a rule (like positive numbers only).
- **DEFAULT:** To provide a fallback value if none is entered.
- **AUTO_INCREMENT:** When you want automatic numbering for each row.

---

## Real-Life Example: Student Enrollment

**Tables:**

```sql
CREATE TABLE Departments (
  DeptID INT PRIMARY KEY,
  DeptName VARCHAR(50) NOT NULL
);

CREATE TABLE Students (
  StudentID INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(50) NOT NULL,
  Email VARCHAR(100) UNIQUE NOT NULL,
  DeptID INT,
  Age INT CHECK (Age >= 18),
  FOREIGN KEY (DeptID) REFERENCES Departments(DeptID)
);
```

- **PRIMARY KEY:** Each table has its own unique ID.
- **FOREIGN KEY:** `Students.DeptID` links to `Departments.DeptID` (cannot assign a student to a non-existent department).
- **UNIQUE:** No two students can share an email.
- **NOT NULL:** Name and Email must be filled.
- **CHECK:** Age must be at least 18.
- **AUTO_INCREMENT:** StudentID is assigned automatically.

---

## Summary

- **Constraints** keep your data safe and meaningful.
- **Always** use PRIMARY KEYs for unique identification.
- **Use FOREIGN KEYs** to connect related tables.
- **UNIQUE** for columns where repeats are not allowed.
- **NOT NULL** to require a value.
- **CHECK** for rules like minimum age or positive numbers.
- **DEFAULT** saves time on repetitive values.
- **AUTO_INCREMENT** for easy unique numbering.
