# SQL Data Types: Simple Guide

SQL databases use data types to define what kind of data each column can store. Here’s a quick, easy-to-understand overview:

---

## 1. Numeric Data Types

| Data Type            | Description                                   | Example      |
|----------------------|-----------------------------------------------|--------------|
| INT / INTEGER        | Whole numbers                                 | 42           |
| SMALLINT             | Smaller whole numbers                         | 123          |
| BIGINT               | Large whole numbers                           | 9000000000   |
| DECIMAL(p, s)        | Exact numbers with decimals (precision, scale)| 12.34        |
| NUMERIC(p, s)        | Same as DECIMAL                               | 99.99        |
| FLOAT / REAL         | Approximate numbers (floating point)          | 3.14159      |
| DOUBLE PRECISION     | Double-precision floating point               | 3.1415926535 |

---

## 2. String (Text) Data Types

| Data Type   | Description                    | Example              |
|-------------|-------------------------------|----------------------|
| CHAR(n)     | Fixed-length text             | 'A', 'Hello     '    |
| VARCHAR(n)  | Variable-length text          | 'Hello'              |
| TEXT        | Large variable-length text    | 'Long description'   |

---

## 3. Date & Time Data Types

| Data Type   | Description                    | Example                |
|-------------|-------------------------------|------------------------|
| DATE        | Date only                     | 2025-06-20             |
| TIME        | Time only                     | 18:53:07               |
| DATETIME    | Date and time                 | 2025-06-20 18:53:07    |
| TIMESTAMP   | Date/time (with or w/o TZ)    | 2025-06-20 18:53:07    |

---

## 4. Boolean Data Type

| Data Type   | Description               | Example    |
|-------------|--------------------------|------------|
| BOOLEAN     | True or False value       | TRUE       |

---

## 5. Binary Data Types

| Data Type       | Description                         | Example                |
|-----------------|------------------------------------|------------------------|
| BINARY(n)       | Fixed-length binary data            | 0xA1B2C3              |
| VARBINARY(n)    | Variable-length binary data         | 0xAABBCC              |
| BLOB            | Large binary object (e.g., files)   | (image, file, etc.)    |

---

## 6. Other Data Types

| Data Type   | Description                        | Example                |
|-------------|------------------------------------|------------------------|
| ENUM        | One of a list of values (MySQL)    | 'small', 'medium'      |
| JSON        | Stores JSON data                   | '{"key": "value"}'     |
| UUID        | Universally unique identifier      | '550e8400-e29b-41d4...'|

---

## 7. Special and Database-specific Data Types

| Data Type   | Description                                | Example               |
|-------------|--------------------------------------------|-----------------------|
| SERIAL      | Auto-increment integer (PostgreSQL)        | 1, 2, 3...            |
| MONEY       | Currency values (various DBs)              | $100.00               |
| XML         | Stores XML data (various DBs)              | `<note>hi</note>`     |
| GEOMETRY    | Spatial data (MySQL, PostGIS etc.)         | POINT(1 1)            |
| ARRAY       | Array of values (PostgreSQL)               | {'a','b','c'}         |
| INTERVAL    | Time interval (PostgreSQL)                 | '1 year 2 days'       |

---

## Additional Tips

- **NULL Values:** Any column can be set to allow or disallow NULL (no data) values.
- **Default Values:** You can specify default values for columns.
- **Primary & Foreign Keys:** Use appropriate data types for keys to ensure data integrity.
- **Collation & Character Sets:** For strings, you can specify the collation (sorting rules) and character set (encoding), important for internationalization.
- **Performance:** Using the most appropriate data type reduces storage requirements and can improve performance.
- **Compatibility:** Not all types are available in every SQL database system. Always check your DBMS documentation for supported types.

---
