# Table Definition with Data Type Explanation

```sql
CREATE TABLE actors (
    actor_id SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30) NOT NULL,
    gender CHAR(1),
    marital_status CHAR(1),
    wife_name VARCHAR(50),
    date_of_birth DATE,
    nationality VARCHAR(20)
);
```

---

## 📘 Column-by-Column Breakdown

**1. actor_id SERIAL PRIMARY KEY**  
*Data Type: SERIAL*  
- **Why?**  
  - `SERIAL` auto-generates unique IDs (like 1, 2, 3…).
  - Acts as a primary key, ensuring each actor has a unique identifier.
  - Shortcut for `INTEGER + AUTO_INCREMENT` in PostgreSQL.

---

**2. first_name VARCHAR(30)**  
*Data Type: VARCHAR(30)*  
- **Why?**  
  - First names vary in length, so `VARCHAR` saves space vs `CHAR`.
  - 30 characters is enough for most names (e.g., “Christopher”).

---

**3. last_name VARCHAR(30) NOT NULL**  
- **Why NOT NULL?**  
  - We assume every actor must have a last name.
- **Why VARCHAR(30)?**  
  - Flexible for longer last names like “McConaughey”.

---

**4. gender CHAR(1)**  
*Data Type: CHAR(1)*  
- **Why?**  
  - Only stores one character like 'M', 'F', 'O' (Other).
  - `CHAR` is fixed-length, efficient when the size is predictable.

---

**5. marital_status CHAR(1)**  
- **Why CHAR(1)?**  
  - Ideal for codes like 'S' (Single), 'M' (Married), 'D' (Divorced).
  - Since only 1 character is needed, `CHAR(1)` is more efficient than `VARCHAR`.

---

**6. wife_name VARCHAR(50)**  
- **Why VARCHAR(50)?**  
  - Optional column to store the spouse's name (if any).
  - Names vary in length; 50 gives enough space without wasting memory.

---

**7. date_of_birth DATE**  
- **Why DATE?**  
  - Stores full date (YYYY-MM-DD).
  - Allows use of date functions for age calculations, filtering, etc.

---

**8. nationality VARCHAR(20)**  
- **Why VARCHAR(20)?**  
  - Stores country/nationality name (e.g., "Indian", "British").
  - Flexible and efficient for small text values.


 
**Insert a new actor:**
```sql
INSERT INTO actors (first_name, last_name, gender, marital_status, wife_name, date_of_birth, nationality)
VALUES ('Robert', 'Downey', 'M', 'M', 'Susan', '1965-04-04', 'American');
```

**Query all actors:**
```sql
SELECT * FROM actors;
```

---

## Best Practices

- Always use a `PRIMARY KEY` for unique identification.
- Use `NOT NULL` for fields that must have data.
- Pick the smallest data type that fits your values (saves space and increases performance).
- Use clear, consistent coding for fields like `gender` and `marital_status` (e.g., 'M', 'F', 'O'; 'S', 'M', 'D').
