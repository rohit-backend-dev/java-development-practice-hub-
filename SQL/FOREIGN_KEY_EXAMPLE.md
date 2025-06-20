# Understanding FOREIGN KEY in SQL Table Creation 

A **FOREIGN KEY** is a rule in SQL that connects two tables. It makes sure that a value in one table must also exist in another, keeping your data correct and linked.

---

## Imagine You Have Two Tables

### 1. directors table

| director_id | first_name | last_name |
|-------------|------------|----------|
| 1           | Steven     | Spielberg|
| 2           | Greta      | Gerwig   |

### 2. movies table

| movie_id | movie_name   | director_id |
|----------|-------------|-------------|
| 1        | Jaws        | 1           |
| 2        | Barbie      | 2           |

---

## Example: Creating These Tables with FOREIGN KEY

```sql
CREATE TABLE directors (
    director_id SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30) NOT NULL,
    date_of_birth DATE,
    nationality VARCHAR(20)
);

CREATE TABLE movies (
    movie_id SERIAL PRIMARY KEY,
    movie_name VARCHAR(50) NOT NULL,
    actor_name VARCHAR(30),
    movie_length INT,
    movie_lang VARCHAR(20),
    release_date DATE,
    age_certificate VARCHAR(5),
    director_id INT REFERENCES directors (director_id)
);
```

---

## 📘 What’s the FOREIGN KEY Doing?

- `director_id` in the `movies` table is a **FOREIGN KEY**.
- It **points to** the `director_id` in the `directors` table.
- This means: **Every movie must have a director that exists in the `directors` table**.

---

## 🛡️ Why Use a FOREIGN KEY?

- **Data Consistency:** Stops you from adding a movie with a director who isn’t in the directors table.
- **Referential Integrity:** Makes sure every movie’s director really exists (no “orphan” movies).
- **Automatic Error-Checking:** SQL won’t let you add or update a movie with a non-existent director.

---

## 📝 What Happens with a FOREIGN KEY?

- **Insert:**  
  - Trying to add a movie with a director who doesn’t exist? SQL will block it.
- **Delete/Update:**  
  - Trying to delete a director who’s still connected to movies? SQL will block it (unless you use options like `ON DELETE CASCADE`).
- **Update:**  
  - Trying to change a director’s ID? All connected movies must update too, or SQL will block it (unless you use `ON UPDATE CASCADE`).

---

## ✅ What Works and What Doesn’t

**Allowed:**
```sql
INSERT INTO directors (first_name, last_name) VALUES ('Steven', 'Spielberg');
INSERT INTO movies (movie_name, director_id) VALUES ('Jaws', 1); -- works if director 1 exists
```

**Blocked:**
```sql
INSERT INTO movies (movie_name, director_id) VALUES ('Unknown Movie', 99); -- fails if director 99 doesn’t exist
```

---

## Simple Rules

- You **can** add a movie with `director_id = 1` if director 1 is in `directors`.
- You **cannot** add a movie with `director_id = 99` if there’s no director 99. SQL will stop you!

---

## Think of it Like...

**A FOREIGN KEY is a safety rope between tables. If you try to tie it to something that’s not there, SQL won’t let you!**

---

##  Summary Table

| Table      | Column        | Constraint Type | References                 | Purpose                                  |
|------------|--------------|-----------------|----------------------------|------------------------------------------|
| movies     | director_id   | FOREIGN KEY     | directors(director_id)     | Makes sure each movie has a real director|
| directors  | director_id   | PRIMARY KEY     | —                          | Unique ID for each director              |

---

## Tips

- Always use FOREIGN KEYs to keep your tables connected in a reliable way.
- They help your database stay clean, correct, and organized.

