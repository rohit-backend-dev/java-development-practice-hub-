# SQL `CREATE TABLE` Statement –

The `CREATE TABLE` statement in SQL is used to define a new table in your database. It specifies the table name, its columns, data types, and constraints (rules for the data).

---

## **Basic Syntax**

```sql
CREATE TABLE table_name (
  column1 datatype [constraint],
  column2 datatype [constraint],
  ...
);
```
- **table_name:** Name of the table you want to create.
- **column1, column2, ...:** Names of the columns.
- **datatype:** The kind of data the column will store (e.g., `INT`, `VARCHAR(50)`, `DATE`).
- **constraint:** Rules like `PRIMARY KEY`, `NOT NULL`, `UNIQUE`, etc. (optional, but recommended for data integrity).

---

## **Example 1: Creating a `directors` Table**

```sql
CREATE TABLE directors (
  director_id SERIAL PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30) NOT NULL,
  date_of_birth DATE,
  nationality VARCHAR(20)
);
```
**Explanation:**
- `director_id SERIAL PRIMARY KEY`: Auto-incrementing unique identifier for each director.
- `first_name VARCHAR(30)`: Director’s first name, up to 30 characters (can be NULL).
- `last_name VARCHAR(30) NOT NULL`: Last name is required.
- `date_of_birth DATE`: Stores date of birth.
- `nationality VARCHAR(20)`: Nationality (optional).

**Query to see all directors:**
```sql
SELECT * FROM directors;
```

---

## **Example 2: Creating a `movies` Table with Constraints**

```sql
CREATE TABLE movies (
  movie_id SERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  release_year INT CHECK (release_year >= 1900),
  genre VARCHAR(20),
  director_id INT NOT NULL,
  FOREIGN KEY (director_id) REFERENCES directors(director_id)
);
```
**Explanation:**
- `movie_id SERIAL PRIMARY KEY`: Unique identifier for each movie.
- `title VARCHAR(100) NOT NULL`: Movie title, required.
- `release_year INT CHECK (release_year >= 1900)`: Only allows years 1900 or later.
- `genre VARCHAR(20)`: Movie genre (optional).
- `director_id INT NOT NULL`: Links each movie to a director (must match a director in the `directors` table).
- `FOREIGN KEY (director_id) REFERENCES directors(director_id)`: Ensures the referenced director exists.


