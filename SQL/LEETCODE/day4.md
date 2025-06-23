# 683. Invalid Tweets — Solution Docs

## Problem
Write a solution to find the IDs of the invalid tweets. The tweet is invalid if the number of characters used in the content of the tweet is strictly greater than 15.
Return the result table in any order. 
OR,
Find the IDs of tweets whose content is longer than 15 characters.

## Table Structure

| Column    | Type    | Description                   |
|-----------|---------|-------------------------------|
| tweet_id  | int     | Unique ID for each tweet      |
| content   | varchar | Text content of the tweet     |

## Definition of Invalid
A tweet is **invalid** if `LENGTH(content) > 15`.

## Example

**Input:**

| tweet_id | content                           |
|----------|-----------------------------------|
| 1        | Let us Code                       |
| 2        | More than fifteen chars are here! |

**Output:**

| tweet_id |
|----------|
| 2        |

## SQL Query

```sql
SELECT tweet_id
FROM Tweets
WHERE LENGTH(content) > 15;
```

## Notes
- Returns tweet IDs of all tweets with content longer than 15 characters.
- The result can be in any order.
