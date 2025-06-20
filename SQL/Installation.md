# SQL (Structured Query Language) Documentation

## What is SQL?
- **Structured Query Language (SQL)** is the standard language for interacting with relational databases.
- It is used to:
  - Create tables and database schemas.
  - Insert, update, and delete data.
  - Retrieve and query data.
  - Manage database security and permissions.
- SQL syntax is similar across most database systems (MySQL, PostgreSQL, SQLite, SQL Server, Oracle, etc.), with only minor differences.

---

## Common SQL Commands

- **CREATE TABLE**: Define a new table.
  ```sql
  CREATE TABLE users (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100)
  );
  ```

---

## Download & Installation Guide

### 1. MySQL

#### macOS
1. Download MySQL DMG Installer from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/).
2. Open the downloaded .dmg file and follow the installation steps.
3. Use **System Preferences** to start/stop MySQL server.
4. Open Terminal and run:
   ```sh
   mysql -u root -p
   ```

#### Windows
1. Go to [MySQL Downloads](https://dev.mysql.com/downloads/installer/).
2. Download the **MySQL Installer for Windows**.
3. Run the installer and follow GUI prompts (choose "Developer Default" for a full install).
4. Set a root password when prompted.
5. Launch `MySQL Command Line Client` from Start Menu.

#### Linux (Ubuntu/Debian)
1. Open Terminal.
2. Update package index:
   ```sh
   sudo apt update
   ```
3. Install MySQL server:
   ```sh
   sudo apt install mysql-server
   ```
4. Secure your installation:
   ```sh
   sudo mysql_secure_installation
   ```
5. Start MySQL:
   ```sh
   sudo systemctl start mysql
   ```
6. Login:
   ```sh
   mysql -u root -p
   ```

---

### 2. PostgreSQL

#### macOS
1. Install using Homebrew:
   ```sh
   brew install postgresql
   brew services start postgresql
   ```
2. Or download [Postgres.app](https://postgresapp.com/) and follow on-screen instructions.

#### Windows
1. Download the installer from [PostgreSQL Downloads](https://www.postgresql.org/download/windows/).
2. Run the installer and follow the setup wizard.
3. Use `pgAdmin` or command line utilities.

#### Linux (Ubuntu/Debian)
1. Update package index:
   ```sh
   sudo apt update
   ```
2. Install PostgreSQL:
   ```sh
   sudo apt install postgresql postgresql-contrib
   ```
3. Start service:
   ```sh
   sudo systemctl start postgresql
   ```
4. Switch to the `postgres` user:
   ```sh
   sudo -i -u postgres
   ```
5. Start:
   ```sh
   psql
   ```

---

### 3. SQLite

#### macOS
- Pre-installed on most macOS systems.
- To check, run:
  ```sh
  sqlite3 --version
  ```
- If not installed, use Homebrew:
  ```sh
  brew install sqlite
  ```

#### Windows
1. Download the precompiled binaries from [SQLite Downloads](https://www.sqlite.org/download.html).
2. Extract the files and add the folder to your system PATH.
3. Open `cmd` and type:
   ```sh
   sqlite3
   ```

#### Linux (Ubuntu/Debian)
1. Install via terminal:
   ```sh
   sudo apt update
   sudo apt install sqlite3
   ```
2. Check installation:
   ```sh
   sqlite3 --version
   ```

---

## Additional Resources

- [SQL Tutorial (W3Schools)](https://www.w3schools.com/sql/)
- [SQLBolt Interactive Lessons](https://sqlbolt.com/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [SQLite Documentation](https://www.sqlite.org/docs.html)

---
