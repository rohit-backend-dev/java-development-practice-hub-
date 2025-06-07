# 🚀 Spring Boot Practice Project

A basic Spring Boot project demonstrating:

- ✅ H2 in-memory database integration  
- ✅ Spring Security with `USER` and `ADMIN` roles  
- ✅ Role-based REST endpoints  
- ✅ H2 Console access enabled  
- ✅ Stateless authentication using HTTP Basic

---

## 📦 Technologies Used

- Java 22  
- Spring Boot 3.5.0  
- Spring Web  
- Spring Security  
- Spring Data JPA  
- H2 Database  
- Lombok (optional)

---

## 🗂 Project Structure
src/
├── main/
│ ├── java/com/practice/practicefile/
│ │ ├── SecurityConfig.java
│ │ └── GreetingsController.java
│ └── resources/
│ └── application.properties


---

## 🔐 SecurityConfig.java

package com.practice.practicefile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // ✅ Allow H2 console
            .authorizeHttpRequests(request -> request
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user1")
            .password("{noop}password1")
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password("{noop}admin123")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}


## 🌐 GreetingsController.java

package com.practice.practicefile;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

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
}


## pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.0</version>
        <relativePath/>
    </parent>

    <groupId>com.practice</groupId>
    <artifactId>practicefile</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>practicefile</name>
    <description>Spring Boot Practice Project</description>

    <properties>
        <java.version>22</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
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
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
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



# ⚙️ application.properties

### Application name
spring.application.name=practicefile

### Default Spring Security user credentials (only used if no custom userDetailsService is provided)
spring.security.user.name=admin
spring.security.user.password=demo1234

### Enable H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

### H2 Database Configuration (In-Memory)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

### JPA Configuration (optional, only if you're using JPA)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

## ✅ Notes:

✅jdbc:h2:mem:testdb: Uses an in-memory H2 database named testdb (data resets on restart).

✅spring.jpa.hibernate.ddl-auto=update: Automatically creates/updates schema based on entities.

✅spring.h2.console.path=/h2-console: H2 console will be available at http://localhost:8080/h2-console.



## 🧪 Accessing H2 Console

**Run the app using:
./mvnw spring-boot:run
Open browser:
http://localhost:8080/h2-console
Use this config:
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave blank)**



## ✅ http.headers(...) frameOptions(...) in Spring Security

**Spring Security applies the X-Frame-Options HTTP header by default to protect against Clickjacking attacks. However, when working with tools like the H2 Database Console, you may need to relax or disable this header.**

### 🔹 Option 1: disable() — Completely Disable Frame Options

http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
📌 Use Case:
Allows your app to be rendered in an <iframe> from any domain.

Necessary when you embed the app in a frame or access tools like the H2 Console.

⚠️ Caution:
Less secure, opens potential for clickjacking unless you trust the context.

### 🔹 Option 2: sameOrigin() — Allow Frames from Same Origin Only

http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
📌 Use Case:
Allows framing only from the same origin (e.g., your own domain or localhost).

This is the recommended option for enabling the H2 console or internal iframes.

✅ Safer Alternative:
Prevents external sites from embedding your app in a frame, protecting against clickjacking.
