# JWT (JSON Web Token) and Spring Boot Authentication — Full Step-by-Step Guide

---

## 📌 What is JWT?

JWT (JSON Web Token) is a compact, self-contained, and digitally signed token format used to securely transmit information between parties — commonly a client (such as a web or mobile app) and a server.  
It enables stateless, scalable user authentication and authorization, especially in RESTful APIs.

**Key properties:**
1. **Compact** — Small enough to be sent in a URL, POST parameter, or HTTP header.
2. **Self-contained** — Carries all user information required for authentication, so the server doesn’t need to store session state.
3. **Secure** — Digitally signed (using HMAC or RSA) to prevent tampering.
4. **Use case:** Used widely in web apps, APIs, and microservices to protect against unauthorized access.

---

### 🔄 Real-World Analogy

*Think of a JWT like a boarding pass:*
- It contains your name, seat number, and flight details.
- It is issued and signed by the airline.
- The airport staff (server) scans and verifies it — they don’t store your boarding info separately.

---

## 🧬 JWT Structure

A JWT consists of three parts, separated by dots (`.`):

`xxxxx.yyyyy.zzzzz`  
**(Header).(Payload).(Signature)**

### 1️⃣ Header

Describes the token:
- The type (`JWT`)
- The signing algorithm (e.g., `HS256`)

**Example:**
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```
Think of this as the label on a sealed envelope:  
“Sealed using HS256.”

---

### 2️⃣ Payload (Claims)

Contains the actual data (called "claims"):
- Standard claims like `sub` (subject, e.g., user ID), `exp` (expiration), `iat` (issued at)
- Custom claims such as `role`, `permissions`

**Example:**
```json
{
  "sub": "rohit123",
  "role": "ROLE_USER",
  "exp": 1717999999
}
```
Think of this as the letter inside the envelope, e.g.,  
"This token belongs to rohit123 who is a user, and it will expire at a certain time."

---

### 3️⃣ Signature

A secure hash created as:
- base64UrlEncode(header) + "." + base64UrlEncode(payload), signed with a secret

**Formula:**
```
Signature = HMACSHA256(
  base64UrlEncode(header) + "." + base64UrlEncode(payload),
  secret
)
```
Think of the signature as a tamper-proof seal on the envelope.  
If the seal is broken, you know someone tampered with it.

---

### ✅ Example JWT (Raw Token)

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJzdWIiOiJyb2hpdDEyMyIsInJvbGUiOiJST0xFX1VTRVIiLCJleHAiOjE3MTc5OTk5OTl9.
dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk
```

- First part: Header (base64-encoded)
- Second part: Payload (base64-encoded)
- Third part: Signature

---

## 🔐 How JWT Works — Simplified

### 🧾 Step-by-Step JWT Flow

1. **User Logs In**  
   Client sends username and password to the server via an authentication endpoint.

2. **Server Generates JWT**  
   If credentials are valid, the server creates a JWT containing user info and signs it using a secret key.

3. **Token Sent to Client**  
   The JWT is returned to the client (stored in `localStorage`, `sessionStorage`, or a secure cookie).

4. **Client Sends Token in Requests**  
   For every protected route, the client sends the JWT in the HTTP `Authorization` header:  
   ```
   Authorization: Bearer <jwt-token>
   ```

5. **Server Verifies and Responds**  
   - Server checks the token’s signature and expiration.
   - If valid, it extracts user data and processes the request.
   - If invalid/expired, it returns `401 Unauthorized`.

---

# ✨ Spring Boot JWT Authentication — Complete Example

**This section walks you through setting up JWT authentication in a Spring Boot project step-by-step.**

---
## project structure
practicefile/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── practice/
│       │           ├── practicefile/
│       │           │   ├── GreetingsController.java      # REST API endpoints
│       │           │   ├── SecurityConfig.java           # Spring Security configuration
│       │           │   └── jwt/
│       │           │       ├── JwtUtils.java             # JWT utility methods
│       │           │       ├── AuthTokenFilter.java      # JWT authentication filter
│       │           │       ├── AuthEntryPointJwt.java    # Handles unauthorized errors
│       │           │       ├── LoginRequest.java         # Login request model
│       │           │       └── LoginResponse.java        # Login response model
│       └── resources/
│           ├── application.properties                    # App config, DB, JWT secret, etc.
│           └── ...
├── pom.xml                                               # Maven dependencies
└── ...

---

## 🌐 API Endpoints

| Endpoint        | Method | Access  | Purpose        | Description                                |
|-----------------|--------|---------|----------------|--------------------------------------------|
| `/hello`        | GET    | Public  | Test endpoint  | Returns `"Hello"`                          |
| `/api/signin`   | POST   | Public  | Login          | Authenticates user and returns JWT + info  |
| `/user`         | GET    | USER    | User-only      | Protected route, requires role `USER`      |
| `/admin`        | GET    | ADMIN   | Admin-only     | Protected route, requires role `ADMIN`     |


🔑 Endpoint Details
/hello:
Public, no authentication needed. Returns a simple string.

/api/signin:
Accepts JSON login ({"username":"user1","password":"password1"}), returns JWT token and roles if successful.

/user:
Requires a valid JWT for a user with ROLE_USER.

/admin:
Requires a valid JWT for a user with ROLE_ADMIN.

---
## 1. `pom.xml` — Add All Required Dependencies

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ... >
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.0</version>
    </parent>
    <groupId>com.practice</groupId>
    <artifactId>practicefile</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>practicefile</name>
    <description>springboot practice</description>
    <properties>
        <java.version>22</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.6</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## 2. `application.properties` — Configuration

```properties
spring.application.name=practicefile
spring.security.user.name=admin
spring.security.user.password=demo1234

spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb

# H2 Console: http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:mem:testdb

spring.app.jwtSecret=ifnujvnifjieiq11134551223444t5tfyftyftfigo8y76668t77yhiohioohuj
spring.app.jwtExpirationMs=30000000
```

---

## 3. JWT Utility & Security Classes

### `JwtUtils.java`

```java
package com.practice.practicefile.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization Header: {}", bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String generateTokenFromUsername(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
```

---

### `AuthTokenFilter.java`

```java
package com.practice.practicefile.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.debug("AuthTokenFilter called for URI: {}", request.getRequestURI());
        try {
            String jwt = jwtUtils.getJwtFromHeader(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                logger.debug("Roles from JWT: {}", userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}
```

---

### `AuthEntryPointJwt.java`

```java
package com.practice.practicefile.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
```

---

### `LoginRequest.java`

```java
package com.practice.practicefile.jwt;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
```

---

### `LoginResponse.java`

```java
package com.practice.practicefile.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String jwtToken;
    private String username;
    private List<String> roles;
}
```

---

## 4. REST Controller Example

### `GreetingsController.java`

```java
package com.practice.practicefile;

import com.practice.practicefile.jwt.JwtUtils;
import com.practice.practicefile.jwt.LoginRequest;
import com.practice.practicefile.jwt.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GreetingsController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userEndPoint() {
        return "Hello, from user!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndPoints() {
        return "Hello, from admin!";
    }

    @PostMapping("/api/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response = new LoginResponse(jwtToken, userDetails.getUsername(), roles);

        return ResponseEntity.ok(response);
    }
}
```

---

## 5. Security Configuration

### `SecurityConfig.java`

```java
package com.practice.practicefile;

import com.practice.practicefile.jwt.AuthEntryPointJwt;
import com.practice.practicefile.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/signin").permitAll()
                        .anyRequest().authenticated());
        http.sessionManagement(
                session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
        );
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                        .sameOrigin()
                )
        );
        http.csrf(csrf -> csrf.disable());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public CommandLineRunner initData(UserDetailsService userDetailsService) {
        return args -> {
            JdbcUserDetailsManager manager = (JdbcUserDetailsManager) userDetailsService;
            if (!manager.userExists("user1")) {
                UserDetails user1 = User.withUsername("user1")
                        .password(passwordEncoder().encode("password1"))
                        .roles("USER")
                        .build();
                manager.createUser(user1);
            }
            if (!manager.userExists("admin")) {
                UserDetails admin = User.withUsername("admin")
                        .password(passwordEncoder().encode("adminPass"))
                        .roles("ADMIN")
                        .build();
                manager.createUser(admin);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
```

---



## 6. How to Test Your Spring Boot JWT API

### 1️⃣ Start the Application
- Run the Spring Boot project:
  ```bash
  mvn spring-boot:run
  ```
- The app runs on port **8080** by default.

---

### 2️⃣ Access the H2 Console (Optional, for debugging)
- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`

---

### 3️⃣ Test the API Endpoints

#### a. Public Endpoint (No Authentication Required)

- **Request:**
  ```
  GET http://localhost:8080/hello
  ```
- **Response:**
  ```
  Hello
  ```

---

#### b. Authenticate (Login) and Receive a JWT Token

- **Request:**
  ```
  POST http://localhost:8080/api/signin
  Content-Type: application/json
  ```
- **Body (for USER):**
  ```json
  {
    "username": "user1",
    "password": "password1"
  }
  ```
- **Body (for ADMIN):**
  ```json
  {
    "username": "admin",
    "password": "adminPass"
  }
  ```

- **Response (example):**
  ```json
  {
    "jwtToken": "eyJhbGciOiJIUzI1NiIs...",
    "username": "user1",
    "roles": ["ROLE_USER"]
  }
  ```

---

#### c. Access Protected Endpoints

- **Add Header**  
  `Authorization: Bearer <jwtToken>`  
  *(Replace `<jwtToken>` with the value you got from login response)*

- **User Endpoint**
  ```
  GET http://localhost:8080/user
  ```
  - Requires `ROLE_USER`
  - **Response:**  
    ```
    Hello, from user!
    ```

- **Admin Endpoint**
  ```
  GET http://localhost:8080/admin
  ```
  - Requires `ROLE_ADMIN`
  - **Response:**  
    ```
    Hello, from admin!
    ```

- **If the JWT is invalid/missing or the user lacks the required role:**
  - Response:  
    ```
    HTTP/1.1 401 Unauthorized
    ```

---

### 🔬 Example Using `curl`

**Authenticate (Login):**
```bash
curl -X POST http://localhost:8080/api/signin \
  -H "Content-Type: application/json" \
  -d '{"username":"user1","password":"password1"}'
```

**Use JWT to Access `/user`:**
```bash
curl http://localhost:8080/user \
  -H "Authorization: Bearer <jwtToken>"
```
*(Replace `<jwtToken>` with the actual token from the login response)*

**Use JWT to Access `/admin`:**
```bash
curl http://localhost:8080/admin \
  -H "Authorization: Bearer <jwtToken>"
```
---

## 🛠️ Best Practices & Tips

- **Never store JWT secrets in source code.** Use environment variables.
- **Use HTTPS** to prevent tokens from being intercepted.
- **Set reasonable expiration** (`exp`) for JWTs.
- For production, consider using **refresh tokens** for longer sessions.
- **Store tokens securely** — avoid localStorage for highly sensitive apps.

---

## Reference
- [Spring Boot JWT Authentication — YouTube Guide](https://youtu.be/Uc2LZFVxHoM?si=uSgehf_yCZ9vKWbJ)
- [Spring Security Docs](https://docs.spring.io/spring-security/reference/index.html)
- [Official JSON Web Token (JWT) Introduction](https://jwt.io/introduction)

**Happy Coding! 🚀**
