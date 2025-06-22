# SQL Data Cleaning Project: Layoffs Dataset (PostgreSQL/pgAdmin Version)
---

## 1. Database & Schema Setup

```sql
-- Create Database (if not exists, else connect to it)
CREATE DATABASE "World_layoff_data_analysing"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en-US'
    LC_CTYPE = 'en-US'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Connect to the database, then:

-- Create schema if not exists
CREATE SCHEMA IF NOT EXISTS world_layoffs;
```

---

## 2. Main Table Creation

```sql
-- Drop table if it exists
DROP TABLE IF EXISTS world_layoffs.layoffs;

-- Create table
CREATE TABLE world_layoffs.layoffs (
  company TEXT,
  location TEXT,
  industry TEXT,
  total_laid_off INTEGER,
  percentage_laid_off TEXT,
  "date" TEXT,
  stage TEXT,
  country TEXT,
  funds_raised_millions INTEGER
);
```

---

## 3. Insert Data

```sql
-- Simulated Layoffs Dataset (Excerpt)
INSERT INTO world_layoffs.layoffs VALUES
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('AirBnB', 'New York', 'Travel', 2000, '10%', '03/11/2024', 'Post-IPO', 'United States', 3000),
('Binance', 'Singapore', 'CryptoCurrency', 900, '15%', '02/13/2025', 'Series C', 'Singapore', 500),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Grab', 'Singapore', '', 1500, '8%', '05/22/2024', 'Series E', 'Singapore', 2500),
('Bytedance', 'Beijing', 'Technology', 1800, '7%', '07/10/2024', 'Private', 'China', 55000),
('Tesla', 'Palo Alto', 'Automotive', 5000, '12%', '03/15/2025', 'Post-IPO', 'United States', 1000),
('Spotify', 'Stockholm', 'Music', 1200, '', '01/18/2024', 'Post-IPO', 'Sweden.', 2500),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Amazon', 'Seattle', 'E-Commerce', 9000, '9%', '02/11/2024', 'Post-IPO', 'United States', 3200),
('Zomato', 'Gurgaon', 'Food Delivery', 700, '', '10/07/2024', 'Post-IPO', 'India', 2100),
('Coinbase', 'San Francisco', 'Crypto Currency', 1100, '15%', '04/06/2025', 'Post-IPO', 'United States', 540),
('Ola', 'Bangalore', NULL, NULL, NULL, '11/22/2024', 'Series D', 'India', 2200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Paytm', 'Noida', 'FinTech', 2000, '14%', '06/02/2025', 'Post-IPO', 'India', 4200),
('Twitter', 'San Francisco', 'Social Media', 4000, '11%', '12/01/2024', 'Post-IPO', 'United States', 3500),
('Stripe', 'San Francisco', 'Fintech', 800, '10%', '03/21/2024', 'Private', 'United States.', 2200),
('Swiggy', 'Bangalore', '', 600, '', '04/14/2025', 'Series F', 'India', 2100),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Flipkart', 'Bangalore', 'E-Commerce', 1200, '8%', '09/18/2024', 'Private', 'India', 3800),
('Oyo', 'Gurgaon', 'Hospitality', 900, '6%', '08/25/2024', 'Private', 'India', 1700),
('LinkedIn', 'Sunnyvale', 'Social Media', 700, '5%', '07/07/2024', 'Microsoft', 'United States', 1500),
('PayPal', 'San Jose', 'FinTech', 1300, '9%', '12/14/2024', 'Post-IPO', 'United States', 900),
('Meta', 'San Francisco', NULL, NULL, NULL, '01/20/2024', 'Post-IPO', 'United States', 2300),
('Netflix', 'Los Gatos', 'Streaming', 800, '6%', '05/22/2025', 'Post-IPO', 'United States', 1400),
('Uber', 'San Francisco', 'Ride Sharing', 1700, '7%', '03/20/2024', 'Post-IPO', 'United States', 2500),
('Lyft', 'San Francisco', 'Ride Sharing', 950, '8%', '04/19/2024', 'Post-IPO', 'United States', 900),
('Shopify', 'Ottawa', 'E-Commerce', 600, '4%', '02/22/2025', 'Post-IPO', 'Canada', 1800),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Reddit', 'San Francisco', 'Social Media', 500, '9%', '03/10/2025', 'Pre-IPO', 'United States.', 700),
('Snap', 'Los Angeles', 'Social Media', 800, '', '09/17/2024', 'Post-IPO', 'United States', 1300),
('SpaceX', 'Hawthorne', 'Aerospace', 1200, '7%', '08/28/2024', 'Private', 'United States', 6200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Gojek', 'Jakarta', 'Technology', 900, '6%', '10/31/2024', 'Series F', 'Indonesia', 3100),
('Sea Group', 'Singapore', 'E-Commerce', 1400, '11%', '06/13/2025', 'Post-IPO', 'Singapore', 5400),
('Coupang', 'Seoul', '', 1200, '8%', '11/24/2024', 'Post-IPO', 'South Korea', 3000),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Myntra', 'Bangalore', 'E-Commerce', 700, '5%', '12/18/2024', 'Private', 'India', 2100),
('Rivian', 'Irvine', 'Automotive', 600, '', '07/12/2025', 'Post-IPO', 'United States', 800),
('Robinhood', 'Menlo Park', 'Fintech', 850, '10%', '02/15/2024', 'Post-IPO', 'United States', 540),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Zillow', 'Seattle', 'Real Estate', 400, '', '10/15/2024', 'Post-IPO', 'United States', 2000),
('Baidu', 'Beijing', 'Technology', 1200, '6%', '05/16/2024', 'Public', 'China', 3500),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Xiaomi', 'Beijing', 'Technology', 850, '', '09/21/2024', 'Public', 'China', 4100),
('Meituan', 'Beijing', 'Technology', 950, '', '08/24/2024', 'Public', 'China', 4200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Razorpay', 'Bangalore', 'FinTech', 900, '8%', '06/12/2025', 'Series D', 'India', 1600),
('Freshworks', 'Chennai', 'Software', 600, '5%', '01/09/2025', 'Post-IPO', 'India', 1200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('ByteDance', 'Beijing', '', 1200, '7%', '07/10/2024', 'Private', 'China', 55000),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),

('DoorDash', 'San Francisco', 'Food Delivery', 700, '7%', '03/11/2025', 'Post-IPO', 'United States', 1800),
('Klarna', 'Stockholm', 'FinTech', 600, '', '01/16/2025', 'Private', 'Sweden', 1200),
('Slack', 'San Francisco', 'Software', 500, '5%', '08/13/2024', 'Post-IPO', 'United States', 1100),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Instacart', 'San Francisco', 'E-Commerce', 400, '4%', '12/23/2024', 'Pre-IPO', 'United States', 950),
('Glovo', 'Barcelona', 'Food Delivery', 300, '4%', '04/17/2025', 'Private', 'Spain', 800),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Doordash', 'San Francisco', 'Food Delivery', 700, '', '03-11-2025', 'Post-IPO', 'United States', 1800),
('Grab', '', '', NULL, '', '05/22/2024', 'Series E', 'Singapore', 2500),
('Paytm', 'Noida', 'FinTech', 2000, '', '06/02/2025', 'Post-IPO', 'India', 4200),

('Gopuff', 'Philadelphia', 'Delivery', 400, '6%', '03/19/2024', 'Series G', 'United States', 850),
('Nubank', 'Sao Paulo', 'FinTech', 1200, '8%', '11/30/2024', 'Post-IPO', 'Brazil', 2200),
('Bolt', 'Tallinn', 'Transportation', 900, '', '02/26/2025', 'Private', 'Estonia', 1050),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Pinduoduo', 'Shanghai', 'E-Commerce', 600, '5%', '09/23/2024', 'Public', 'China', 4000),
('Yandex', 'Moscow', 'Technology', 800, '', '06/30/2024', 'Public', 'Russia', 3100),
('OYO', 'Gurgaon', 'Hospitality', 900, '6%', '08/25/2024', 'Private', 'India', 1700),
('Oyo', 'Gurgaon', 'Hospitality', 900, '6%', '25/08/2024', 'Private', 'India', 1700),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Glovo', 'Barcelona', 'Food Delivery', 300, '4%', '04/17/2025', 'Private', 'Spain', 800),

('Carousell', 'Singapore', 'E-Commerce', 200, '3%', '07/30/2024', 'Series D', 'Singapore', 800),
('Coupang', 'Seoul', 'E-commerce', 1200, '8%', '11/24/2024', 'Post-IPO', 'South Korea', 3000),
('Bukalapak', 'Jakarta', 'E-commerce', 400, '', '08/13/2024', 'Public', 'Indonesia', 2000),
('Shopee', 'Singapore', 'E-Commerce', 1100, '9%', '10/22/2024', 'Post-IPO', 'Singapore', 4200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('JD.com', 'Beijing', 'E-Commerce', 900, '7%', '06/11/2025', 'Public', 'China', 6300),
('Lazada', 'Singapore', 'E-Commerce', 850, '', '03/28/2025', 'Private', 'Singapore', 3200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('CRED', 'Bangalore', 'FinTech', 500, '9%', '10/10/2024', 'Series E', 'India', 1600),
('Rappi', 'Bogota', 'Delivery', 650, '7%', '05/12/2024', 'Series F', 'Colombia', 900),

('Delivery Hero', 'Berlin', 'Food Delivery', 1100, '8%', '01/19/2025', 'Public', 'Germany.', 3600),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('FlipKart', 'Bangalore', 'E-Commerce', 1200, '8%', '18/09/2024', 'Private', 'India.', 3800),
('Paytm', 'Noida', 'Fintech', 1950, '13%', '02/06/2025', 'Post-IPO', 'India', 4200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Paytm', 'Noida', 'FinTech', 2000, '14%', '06/02/2025', 'Post-IPO', 'India.', 4200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Stripe', 'San Francisco', '', 800, '', '21/03/2024', 'Private', 'United States', 2200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Swiggy', 'Bangalore', 'Food Delivery', 600, '', '04/14/2025', 'Series F', 'India', 2100),

('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Meesho', 'Bangalore', 'E-Commerce', 750, '7%', '03/18/2025', 'Series F', 'India', 1500),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('MakeMyTrip', 'Gurgaon', 'Travel', 600, '5%', '12/01/2024', 'Public', 'India', 1100),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('PhonePe', 'Bangalore', 'FinTech', 900, '7%', '02/09/2025', 'Private', 'India', 1800),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('CRED', 'Bangalore', 'FinTech', 500, '', '10/10/2024', 'Series E', 'India.', 1600),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),

('Hike', 'Delhi', 'Messaging', 400, '', '03/07/2024', 'Series D', 'India.', 900),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Yelp', 'San Francisco', 'Reviews', 300, '5%', '08/30/2024', 'Post-IPO', 'United States', 1300),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Snapdeal', 'Gurgaon', 'E-Commerce', 500, '6%', '09/11/2024', 'Private', 'India.', 800),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Telegram', 'Dubai', 'Messaging', 600, '8%', '11/14/2024', 'Private', 'UAE.', 900),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Paytm', 'Noida', '', 2000, '', '6/2/2025', 'Post-IPO', 'India', 4200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),

('Ola', 'Bangalore', 'Ride Sharing', 1500, '12%', '22/11/2024', 'Series D', 'India', 2200),
('Stripe', 'San Francisco', 'FinTech', 900, '11%', '21/03/2024', 'Private', 'United States', 2300),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Gojek', 'Jakarta', 'Tech', 800, '7%', '31/10/2024', 'Series F', 'Indonesia', 3000),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('PhonePe', 'Bangalore', 'FinTech', 800, '', '09/02/2025', 'Private', 'India', 1600),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Swiggy', 'Bangalore', 'Food Delivery', 650, '10%', '14/04/2025', 'Series F', 'India.', 2100),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),

('Uber', 'San Francisco', 'Ride Sharing', 1200, '8%', '20/03/2024', 'Post-IPO', 'United States', 2500),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Snap', 'Los Angeles', 'Social Media', 900, '9%', '17/09/2024', 'Post-IPO', 'United States', 1400),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States.', 2300),
('Tesla', 'Palo Alto', 'Automotive', 5100, '13%', '15/03/2025', 'Post-IPO', 'United States', 1100),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('SpaceX', 'Hawthorne', 'Aerospace', 1300, '8%', '28/08/2024', 'Private', 'United States', 6200),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300),
('Amazon', 'Seattle', 'E-Commerce', 9500, '10%', '11/02/2024', 'Post-IPO', 'United States', 3400),
('Meta', 'San Francisco', 'Social Media', 10000, '13%', '01/20/2024', 'Post-IPO', 'United States', 2300);

```

---

## 4. **Data Cleaning Steps**

### **A. Create a Staging Table**

This protects your original data.

```sql
CREATE TABLE world_layoffs.layoffs_staging 
LIKE world_layoffs.layoffs;

INSERT INTO world_layoffs.layoffs_staging
SELECT * FROM world_layoffs.layoffs;
```

---

### **B. Remove Duplicates**

#### **Step 1: Add Row Number**

```sql
CREATE TABLE world_layoffs.layoffs_staging2 AS
SELECT *,
ROW_NUMBER() OVER (
    PARTITION BY company, location, industry, total_laid_off, percentage_laid_off, "date", stage, country, funds_raised_millions
) AS row_num
FROM world_layoffs.layoffs_staging;
```

#### **Step 2: Delete Duplicates**

```sql
DELETE FROM world_layoffs.layoffs_staging2
WHERE row_num > 1;
```

#### **Step 3: Drop the Helper Column**

```sql
ALTER TABLE world_layoffs.layoffs_staging2
DROP COLUMN row_num;
```

---

### **C. Standardize Data**

#### **Industry: Set Blank to Null, Fill Nulls from Other Rows**

```sql
UPDATE world_layoffs.layoffs_staging2
SET industry = NULL
WHERE industry = '';

UPDATE world_layoffs.layoffs_staging2 t1
SET industry = t2.industry
FROM world_layoffs.layoffs_staging2 t2
WHERE t1.company = t2.company
  AND t1.industry IS NULL
  AND t2.industry IS NOT NULL;

```

#### **Industry: Standardize Crypto Variations**
-- STEP 1:
```sql
UPDATE world_layoffs.layoffs_staging2
SET industry = 'Crypto'
WHERE industry IN ('Crypto Currency', 'CryptoCurrency');
```

#### **Country: Remove Trailing Periods**
```sql
UPDATE world_layoffs.layoffs_staging2
SET "date" = REPLACE("date", '-', '/')
WHERE "date" LIKE '%-%';
```

#### **Date: Convert to DATE type**

```sql
ALTER TABLE world_layoffs.layoffs_staging2 
ADD COLUMN date_converted DATE;

UPDATE world_layoffs.layoffs_staging2
SET country = TRIM(TRAILING '.' FROM country)
WHERE country LIKE '%.';

--  Convert MM/DD/YYYY
UPDATE world_layoffs.layoffs_staging2
SET date_converted = TO_DATE("date", 'MM/DD/YYYY')
WHERE "date" ~ '^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$'
  AND date_converted IS NULL;

--  Convert DD/MM/YYYY fallback
UPDATE world_layoffs.layoffs_staging2
SET date_converted = TO_DATE("date", 'DD/MM/YYYY')
WHERE "date" ~ '^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$'
  AND date_converted IS NULL;


--  Replace old column
ALTER TABLE world_layoffs.layoffs_staging2 
DROP COLUMN "date";

ALTER TABLE world_layoffs.layoffs_staging2 
RENAME COLUMN date_converted TO "date";

```

**C.1. Standardize Capitalization and Duplicates**
```sql
-- Industry & Stage Title Case Formatting
UPDATE world_layoffs.layoffs_staging2
SET industry = INITCAP(industry);

UPDATE world_layoffs.layoffs_staging2
SET stage = INITCAP(stage);

```
**🔹 C.2. Fix Common Company Name Variants**
```sql
-- Standardize Flipkart
UPDATE world_layoffs.layoffs_staging2
SET company = 'Flipkart'
WHERE LOWER(company) = 'flipkart';

-- Standardize Oyo
UPDATE world_layoffs.layoffs_staging2
SET company = 'Oyo'
WHERE LOWER(company) IN ('oyo', 'oyo.');
```
---

### **D. Handle Null Values**

- Nulls in columns like `total_laid_off` and `funds_raised_millions` are kept for analysis.

---

### **E. Remove Unnecessary Data**

#### **Delete Rows with Insufficient Data**

```sql
DELETE FROM world_layoffs.layoffs_staging2
WHERE total_laid_off IS NULL
  AND percentage_laid_off IS NULL;
```

---

## 5. **Final Data Check**

```sql
SELECT * FROM world_layoffs.layoffs_staging2;
```

---

# 📊 STEP-BY-STEP EDA

### 📊 What is EDA?
EDA is the process of analyzing datasets to summarize their main characteristics, often using statistical graphics, data visualization, and simple queries — before applying any formal modeling or machine learning.

🎯 Goals of EDA
✅ Understand data distributions (e.g., how many layoffs per year)

✅ Spot trends, patterns, or outliers

✅ Detect missing or inconsistent values

✅ Validate assumptions

✅ Form hypotheses for further analysis



## 🔹 Step 1: Year-wise Layoffs Trend

**1.1. Extract Year and Group**
```sql
SELECT 
  EXTRACT(YEAR FROM "date") AS year,
  SUM(total_laid_off) AS total_layoffs
FROM world_layoffs.layoffs_staging2
GROUP BY year
ORDER BY year;
```

**1.2. Optional: With Count of Companies**
```sql
SELECT 
  EXTRACT(YEAR FROM "date") AS year,
  COUNT(DISTINCT company) AS companies_affected,
  SUM(total_laid_off) AS total_layoffs
FROM world_layoffs.layoffs_staging2
GROUP BY year
ORDER BY year;
```

## 🔹 Step 2: Most Impacted Industries

**2.1. Total Layoffs per Industry**
```sql
SELECT 
  industry,
  SUM(total_laid_off) AS total_layoffs
FROM world_layoffs.layoffs_staging2
WHERE industry IS NOT NULL
GROUP BY industry
ORDER BY total_layoffs DESC;
```

**2.2. Top 5 Industries**
```sql
SELECT 
  industry,
  SUM(total_laid_off) AS total_layoffs
FROM world_layoffs.layoffs_staging2
WHERE industry IS NOT NULL
GROUP BY industry
ORDER BY total_layoffs DESC
LIMIT 5;
```

## 🔹 Step 3: Country-Level Layoffs

**3.1. Total Layoffs by Country**
```sql
SELECT 
  country,
  SUM(total_laid_off) AS total_layoffs
FROM world_layoffs.layoffs_staging2
GROUP BY country
ORDER BY total_layoffs DESC;
```

**3.2. Top Countries by Number of Companies Impacted**
```sql
SELECT 
  country,
  COUNT(DISTINCT company) AS affected_companies
FROM world_layoffs.layoffs_staging2
GROUP BY country
ORDER BY affected_companies DESC;
```

## 🔹 Step 4: Company-wise Layoff Trends

**4.1. Companies with Highest Layoffs**

```sql
SELECT 
  company,
  SUM(total_laid_off) AS total_layoffs
FROM world_layoffs.layoffs_staging2
GROUP BY company
ORDER BY total_layoffs DESC;
```

**4.2. Year-wise Layoffs for Each Top Company (e.g., Meta)**

```sql
SELECT 
  company,
  EXTRACT(YEAR FROM "date") AS year,
  SUM(total_laid_off) AS layoffs
FROM world_layoffs.layoffs_staging2
WHERE company = 'Meta'
GROUP BY company, year
ORDER BY year;
```
**You can also generalize for all companies:**

```sql
SELECT 
  company,
  EXTRACT(YEAR FROM "date") AS year,
  SUM(total_laid_off) AS layoffs
FROM world_layoffs.layoffs_staging2
GROUP BY company, year
ORDER BY layoffs DESC;

```

## **Summary**

- Created a staging table for safe data cleaning.
- Removed strict duplicates based on all relevant columns.
- Standardized text values for industry and country.
- Normalized date formats and column types.
- Handled missing values appropriately.
- Removed rows with insufficient data for meaningful analysis.

You can now proceed to EDA or further transformation using the cleaned `layoffs_staging2` table.

---
