# All Possible Actions You Can Perform on a Table in SQL 

## 1. Create Operations

- **Create Table**
  - _Example: Create the `directors` table:_
  ```sql
  CREATE TABLE directors (
      director_id SERIAL PRIMARY KEY,
      first_name VARCHAR(30),
      last_name VARCHAR(30) NOT NULL,
      date_of_birth DATE,
      nationality VARCHAR(20)
  );
  ```

- **Create Table As (Copy Table Structure/Data)**
  - _Example: Create a copy of the `directors` table with data:_
  ```sql
  CREATE TABLE directors_copy AS SELECT * FROM directors;
  ```
  - _Structure only:_
  ```sql
  CREATE TABLE directors_empty AS SELECT * FROM directors WHERE 1=0;
  ```

---

## 2. Delete Operations

- **Drop Table** (Delete entire table and data)
  - _Example:_
  ```sql
  DROP TABLE actors;
  ```

- **Truncate Table** (Delete all data, keep structure)
  - _Example:_
  ```sql
  TRUNCATE TABLE directors;
  ```

- **Delete Rows** (Remove specific records)
  - _Example: Delete all American directors_
  ```sql
  DELETE FROM directors WHERE nationality = 'American';
  ```

---

## 3. Modify Table Structure

- **Rename Table**
  - _Example:_
  ```sql
  ALTER TABLE directors RENAME TO film_directors;
  ```

- **Add Column**
  - _Example: Add "awards" column to `directors`_
  ```sql
  ALTER TABLE directors ADD COLUMN awards INT;
  ```

- **Drop Column**
  - _Example: Remove "awards" column from `directors`_
  ```sql
  ALTER TABLE directors DROP COLUMN awards;
  ```

- **Rename Column**
  - _Example: Change "first_name" to "fname"_
  ```sql
  ALTER TABLE directors RENAME COLUMN first_name TO fname;
  ```

- **Change Column Type**
  - _Example: Change "movie_length" in `movies` to BIGINT_
  ```sql
  ALTER TABLE movies ALTER COLUMN movie_length TYPE BIGINT;
  ```

- **Set/Drop Default Value**
  - _Example: Set default nationality to 'American'_
  ```sql
  ALTER TABLE directors ALTER COLUMN nationality SET DEFAULT 'American';
  ```
  - _Drop default:_
  ```sql
  ALTER TABLE directors ALTER COLUMN nationality DROP DEFAULT;
  ```

- **Set/Drop Not Null**
  - _Example: Make "date_of_birth" required in `directors`_
  ```sql
  ALTER TABLE directors ALTER COLUMN date_of_birth SET NOT NULL;
  ```
  - _Allow NULL:_
  ```sql
  ALTER TABLE directors ALTER COLUMN date_of_birth DROP NOT NULL;
  ```

---

## 4. Data Manipulation

- **Insert Data**
  - _Example:_
  ```sql
  INSERT INTO actors (first_name, last_name, gender, marital_status, wife_name, date_of_birth, nationality)
  VALUES ('Robert', 'Downey', 'M', 'M', 'Susan', '1965-04-04', 'American');
  ```

- **Bulk Insert**
  - _Example:_
  ```sql
  INSERT INTO directors (first_name, last_name, date_of_birth, nationality)
  VALUES 
    ('Christopher', 'Nolan', '1970-07-30', 'British-American'),
    ('Steven', 'Spielberg', '1946-12-18', 'American');
  ```

- **Update Data**
  - _Example:_
  ```sql
  UPDATE actors SET nationality = 'British' WHERE last_name = 'Downey';
  ```

---

## 5. Querying Data

- **Select Data**
  - _Example:_
  ```sql
  SELECT * FROM directors;
  SELECT first_name, last_name FROM actors WHERE nationality = 'American';
  ```

- **Select with Join**
  - _Example: Get all movies and their director's name_
  ```sql
  SELECT m.movie_name, d.first_name, d.last_name
  FROM movies m
  JOIN directors d ON m.director_id = d.director_id;
  ```

---

## 6. Constraints & Keys

- **Add Primary Key**
  - _Example: (if not already set)_
  ```sql
  ALTER TABLE actors ADD PRIMARY KEY (actor_id);
  ```

- **Drop Primary Key**
  - _Example:_
  ```sql
  ALTER TABLE actors DROP CONSTRAINT actors_pkey;
  ```

- **Add Foreign Key**
  - _Example: Add actor reference to `movie_actors`_
  ```sql
  ALTER TABLE movie_actors
      ADD CONSTRAINT fk_actor FOREIGN KEY (actor_id)
      REFERENCES actors (actor_id);
  ```

- **Drop Foreign Key**
  - _Example:_
  ```sql
  ALTER TABLE movie_actors DROP CONSTRAINT fk_actor;
  ```

- **Add Unique Constraint**
  - _Example: Make movie names unique in `movies`_
  ```sql
  ALTER TABLE movies ADD CONSTRAINT unique_movie_name UNIQUE (movie_name);
  ```

- **Drop Unique Constraint**
  - _Example:_
  ```sql
  ALTER TABLE movies DROP CONSTRAINT unique_movie_name;
  ```

- **Add Check Constraint**
  - _Example: Only allow positive movie lengths_
  ```sql
  ALTER TABLE movies ADD CONSTRAINT chk_movie_length CHECK (movie_length > 0);
  ```

- **Drop Check Constraint**
  - _Example:_
  ```sql
  ALTER TABLE movies DROP CONSTRAINT chk_movie_length;
  ```

---

## 7. Indexes

- **Create Index**
  - _Example:_
  ```sql
  CREATE INDEX idx_director_last_name ON directors (last_name);
  ```

- **Drop Index**
  - _Example:_
  ```sql
  DROP INDEX idx_director_last_name;
  ```

---

## 8. Views

- **Create View**
  - _Example: View of movies with revenue_
  ```sql
  CREATE VIEW movie_revenue_view AS
  SELECT m.movie_name, r.domestic_revenue, r.international_revenue
  FROM movies m
  JOIN movie_revenue r ON m.movie_id = r.movie_id;
  ```

- **Drop View**
  - _Example:_
  ```sql
  DROP VIEW movie_revenue_view;
  ```

---

## 9. Permissions

- **Grant Permission**
  - _Example:_
  ```sql
  GRANT SELECT, INSERT ON actors TO johndoe;
  ```

- **Revoke Permission**
  - _Example:_
  ```sql
  REVOKE UPDATE ON actors FROM johndoe;
  ```

---

## 10. Table Structure Information

- **Describe Table / List Columns**
  - _Example:_
  ```sql
  -- PostgreSQL:
  \d actors

  -- MySQL:
  DESCRIBE actors;

  -- Standard SQL:
  SELECT column_name, data_type FROM information_schema.columns WHERE table_name = 'actors';
  ```

- **Show All Tables**
  - _Example:_
  ```sql
  -- PostgreSQL:
  \dt

  -- MySQL:
  SHOW TABLES;
  ```

---

## 11. Table Alterations (Advanced)

- **Change Column Order** (Not supported in PostgreSQL, supported in MySQL)
  - _Example (MySQL):_
  ```sql
  ALTER TABLE actors MODIFY COLUMN first_name VARCHAR(30) AFTER last_name;
  ```

- **Copy Table Structure Only**
  - _Example:_
  ```sql
  CREATE TABLE actors_clone AS SELECT * FROM actors WHERE 1=0;
  ```

---

## 12. Table Inheritance (PostgreSQL)

- **Create Table that Inherits Columns**
  - _Example:_
  ```sql
  CREATE TABLE special_directors (
      award_won VARCHAR(50)
  ) INHERITS (directors);
  ```

---

## 13. Partitioning (Advanced)

- **Create Partitioned Table**
  - _Example: Partition `movie_revenue` by year (PostgreSQL 10+):_
  ```sql
  CREATE TABLE movie_revenue_by_year (
      revenue_id SERIAL PRIMARY KEY,
      movie_id INT,
      domestic_revenue NUMERIC(6, 2),
      international_revenue NUMERIC(6, 2),
      revenue_year INT
  ) PARTITION BY RANGE (revenue_year);
  ```

---

## 14. Commenting

- **Add Comment to Table or Column**
  - _Example:_
  ```sql
  COMMENT ON TABLE actors IS 'Table of movie actors and their info';
  COMMENT ON COLUMN actors.wife_name IS 'Name of spouse if married';
  ```

---

## 15. Temporary Tables

- **Create Temporary Table**
  - _Example:_
  ```sql
  CREATE TEMPORARY TABLE temp_actors AS SELECT * FROM actors WHERE nationality = 'American';
  ```

---

## Summary Table

| Action Category            | SQL Command Example/Note                                   |
|---------------------------|------------------------------------------------------------|
| Create Table               | `CREATE TABLE directors (...);`                            |
| Drop Table                 | `DROP TABLE actors;`                                      |
| Truncate Table             | `TRUNCATE TABLE directors;`                               |
| Rename Table               | `ALTER TABLE directors RENAME TO film_directors;`          |
| Add/Drop Column            | `ALTER TABLE directors ADD/DROP COLUMN awards;`            |
| Rename Column              | `ALTER TABLE directors RENAME COLUMN first_name TO fname;` |
| Change Column Type/Default | `ALTER TABLE movies ALTER COLUMN movie_length TYPE BIGINT;`|
| Insert/Update/Delete Data  | `INSERT/UPDATE/DELETE FROM actors ...`                    |
| Select Data                | `SELECT * FROM directors;`                                |
| Add/Drop Primary/Foreign/Unique/Check | See constraint examples above                  |
| Create/Drop Index          | `CREATE/DROP INDEX idx_director_last_name ...`            |
| Grant/Revoke Permissions   | `GRANT/REVOKE ... ON actors TO johndoe;`                  |
| Describe Table             | `\d actors`                                               |
| Copy Table                 | `CREATE TABLE directors_copy AS SELECT * FROM directors;`  |
| Partitioning/Inherit       | See advanced examples above                               |
| Comment Table/Column       | `COMMENT ON ...`                                          |
| Temporary Table            | `CREATE TEMPORARY TABLE temp_actors ...`                  |
| Views                      | `CREATE/DROP VIEW movie_revenue_view ...`                 |

---

> ⚡ **Pro Tip:**  
> Before dropping or truncating tables, always back up your data!
