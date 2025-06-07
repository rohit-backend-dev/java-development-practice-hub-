# 🔐 Role-Based Authentication with Spring Boot + Spring Security

This project demonstrates **Role-Based Access Control (RBAC)** using **Spring Security** with **In-Memory User Storage** and **Basic Authentication**.

---

## ✅ Features

- 📦 Spring Boot + Spring Security
- 🔑 In-Memory User Storage
- 👥 Role-Based Endpoint Access
- 🛡️ Basic Authentication (No UI form)
- 🚫 CSRF Disabled for APIs
- ☁️ Stateless Sessions (JWT-ready)

---

## 🧩 Project Structure

📁 com.practice.practicefile
│
├── SecurityConfig.java # Security filter chain + in-memory users
├── GreetingsController.java # Role-protected REST endpoints

---

# ⚙️ SecurityConfig.java


package com.practice.practicefile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                .csrf(csrf -> csrf.disable()) // Disable CSRF (for APIs)
                .authorizeHttpRequests(request -> request.anyRequest().authenticated()) // All requests need authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .httpBasic(withDefaults()); // Basic authentication
        return http.build();
    }

   @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user1")
                .password("{noop}password1")  // No encoding (testing only)
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}



# 🌐 GreetingsController.java

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

**🧪 Run the App**
./mvnw spring-boot:run
Test the endpoints via Postman or any HTTP client:

**Endpoint	Role Required	Description**

/hello	Any Auth User	Open to both USER and ADMIN
/user	USER	Only accessible by USER
/admin	ADMIN	Only accessible by ADMIN
