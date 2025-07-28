**Keycloak** is an **open-source Identity and Access Management (IAM)** solution developed by Red Hat. It helps you **secure applications and services** with **modern authentication and authorization** mechanisms like:

* **Single Sign-On (SSO)**
* **OAuth 2.0**
* **OpenID Connect**
* **SAML 2.0**

---

### 🔐 **What Can Keycloak Do?**

1. **Authentication**

   * Login with username/password
   * Social logins (Google, Facebook, etc.)
   * Two-Factor Authentication (2FA)
   * Identity brokering and user federation

2. **Authorization**

   * Role-based access control (RBAC)
   * Fine-grained permissions using policies

3. **SSO (Single Sign-On)**

   * Users log in once to access multiple apps

4. **User Management**

   * Admin UI to create/manage users, roles, groups
   * REST API for automation

5. **Multi-tenancy**

   * Realms: Isolated environments within Keycloak (e.g., dev, test, prod or per client)

6. **Protocol Support**

   * OAuth 2.0
   * OpenID Connect (OIDC)
   * SAML 2.0

---

### 🏗️ **Where is Keycloak Used?**

* Web applications (Java, React, Angular, etc.)
* Mobile apps
* Microservices
* APIs

---

### 🏋️‍♂️ Deployment & Operational Guides
Installation: Available via binaries, Docker, Podman, Kubernetes and OpenShift 
Keycloak

Configuration: Multi-level configuration support (CLI args → env → config file → keystore), TLS setup, database backends, cache clustering, and reverse proxy setups 
Keycloak

High Availability & Scaling: Full support for clustering, observability (metrics, tracing, dashboards), and Kubernetes operator usage 
Keycloak

---

### 🛠️ **Common Use Cases**

* Centralized login/authentication for all applications
* Secure REST APIs with token-based access (JWT)
* Federating users from LDAP or Active Directory
* Using social login (e.g., Google, GitHub)
* Adding OAuth/OIDC support without coding authentication logic

---

### ⚙️ **Tech Stack Integration**

Keycloak works with:

* Spring Boot (via Keycloak adapters or Spring Security OIDC)
* Node.js
* React, Angular (via JS adapters)
* Docker/Kubernetes deployments

---

### ✅ **Why Use Keycloak?**

* Production-ready
* Active community and regular updates
* Easy to integrate with standard protocols
* Reduces the need to write custom auth logic

---
Here's a complete, **beginner-friendly guide** to installing and running **Keycloak using Docker**, suitable for **local development** and testing.

---

## 🐋 Keycloak Installation using Docker

### ✅ **Prerequisites**

* [Docker](https://www.docker.com/products/docker-desktop/) installed on your system
* Port `8080` should be available (or use another port)

---

## 🔧 Step-by-Step Installation

### 📦 Step 1: **Pull Keycloak Docker Image**

Open your terminal and run:

```bash
docker pull quay.io/keycloak/keycloak:26.1.1
```

> ✅ Replace `26.1.1` with the latest stable version if needed.

---

### 🚀 Step 2: **Run Keycloak in Development Mode**

To start Keycloak in development mode:

```bash
docker run -p 8080:8080 \
  -e KC_DB=dev-file \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:26.1.1 start-dev
```

#### 🔑 Environment Variables:

| Variable                  | Description                   |
| ------------------------- | ----------------------------- |
| `KEYCLOAK_ADMIN`          | Username for admin login      |
| `KEYCLOAK_ADMIN_PASSWORD` | Password for admin login      |
| `KC_DB=dev-file`          | Uses embedded DB for dev mode |

---

### 🌐 Step 3: **Access Keycloak Dashboard**

* Open your browser
* Go to: [http://localhost:8080](http://localhost:8080)
* Login using:

  * **Username**: `admin`
  * **Password**: `admin`

---

## 🏗️ Optional: Persistent Storage & Production Database

To run with **PostgreSQL** and persist data:

```bash
docker run -p 8080:8080 \
  -e KC_DB=postgres \
  -e KC_DB_URL=jdbc:postgresql://<DB_HOST>:5432/keycloak \
  -e KC_DB_USERNAME=your_db_user \
  -e KC_DB_PASSWORD=your_db_password \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:26.1.1 start
```

You must first have a PostgreSQL container or server running.

---

## 🗃️ Docker Compose Setup (Recommended for Teams)

Create a `docker-compose.yml`:

```yaml
version: '3.8'

services:
  keycloak:
    image: quay.io/keycloak/keycloak:26.1.1
    container_name: keycloak
    ports:
      - 8080:8080
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
      KC_DB: dev-file
    command: start-dev
```

Then run:

```bash
docker-compose up
```

---

## 🔒 For Production Use

* Use `start` instead of `start-dev`
* Connect to a real database (PostgreSQL/MariaDB)
* Enable HTTPS (via reverse proxy like Nginx)
* Manage users/clients/realms using Admin CLI or realm JSON import

---



