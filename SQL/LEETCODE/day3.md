# 1148. Article Views I: Find Authors Who Viewed Their Own Articles

## Problem Description

Given a table `Views` with the following columns:

| Column Name | Type |
|-------------|------|
| article_id  | int  |
| author_id   | int  |
| viewer_id   | int  |
| view_date   | date |

Each row indicates an article (by `article_id`), the author of the article (`author_id`), a viewer of the article (`viewer_id`), and the date the view occurred (`view_date`).  
There is no primary key; duplicates may exist.  
A person can be both the author and the viewer (`author_id = viewer_id`).  

**Goal:**  
Find all authors who have viewed at least one of their own articles.  
Return the result as a table with a single column `id`, sorted in ascending order.

---

## Sample Input

| article_id | author_id | viewer_id | view_date   |
|------------|-----------|-----------|-------------|
| 1          | 3         | 5         | 2019-08-01  |
| 1          | 3         | 6         | 2019-08-02  |
| 2          | 7         | 7         | 2019-08-01  |
| 2          | 7         | 6         | 2019-08-02  |
| 4          | 7         | 1         | 2019-07-22  |
| 3          | 4         | 4         | 2019-07-21  |
| 3          | 4         | 4         | 2019-07-21  |

---

## Expected Output

| id  |
|-----|
| 4   |
| 7   |

---

## Solution Approach

### Step-by-Step Reasoning

1. **Identify the Condition:**  
   An author has viewed their own article if there is a row where `author_id = viewer_id`.
   
2. **Select Only Unique Authors:**  
   The same author might view their article multiple times, so we need each author only once.

3. **Match Output Format:**  
   The column should be named `id` and results should be sorted in ascending order.

---

## Final Query

```sql
SELECT DISTINCT author_id AS id
FROM Views
WHERE author_id = viewer_id
ORDER BY id;
```

---

### Explanation of Each Part

| Clause                           | Purpose                                                                                  |
|-----------------------------------|------------------------------------------------------------------------------------------|
| `SELECT DISTINCT author_id AS id` | Picks the author id, removes duplicates, and renames column as `id`                      |
| `FROM Views`                     | Specifies the source table                                                                |
| `WHERE author_id = viewer_id`     | Filters for cases where the author viewed their own article                               |
| `ORDER BY id`                    | Sorts the output as required                                                             |

---

### Visual Example

Suppose you have:

| author_id | viewer_id |
|-----------|-----------|
| 4         | 4         | ✅ Author 4 viewed their own article
| 7         | 7         | ✅ Author 7 viewed their own article
| 4         | 4         | (duplicate - should only show 4 once)

After applying the query, the output is:

| id  |
|-----|
| 4   |
| 7   |

---

## Why Use Each Keyword?

- **DISTINCT:** Prevents duplicate authors in the result if they've viewed their own articles multiple times.
- **AS id:** Renames the output column to match the required format.
- **WHERE author_id = viewer_id:** Core logic to detect self-views.
- **ORDER BY id:** Ensures output is sorted as required by the problem.

---

## Alternatives

You could also use `GROUP BY author_id` instead of `DISTINCT`, but `DISTINCT` is more direct here.

```sql
SELECT author_id AS id
FROM Views
WHERE author_id = viewer_id
GROUP BY author_id
ORDER BY id;
```

---

## Summary Table

| Step          | Action                                                   |
|---------------|---------------------------------------------------------|
| 1             | Filter rows where `author_id = viewer_id`               |
| 2             | Select only `author_id`                                 |
| 3             | Remove duplicates (`DISTINCT`)                          |
| 4             | Rename column to `id`                                   |
| 5             | Sort by `id`                                            |
