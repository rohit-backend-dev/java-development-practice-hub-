# 🔐 Role-Based Authentication in Spring Boot

**Implement secure and maintainable role-based access control in your Spring Boot application using Spring Security.**

## 1️⃣ What is Role-Based Authentication?

Role-Based Authentication (or Authorization) means users are assigned roles, and access to different parts of the application is controlled based on these roles.

**Example Roles:**
- `USER`
- `ADMIN`
- `MODERATOR`

🔹 A user with role `ADMIN` can access admin pages.  
🔹 A user with role `USER` can access only normal user pages.

---

## 2️⃣ Why Use Role-Based Authentication?

- ✅ Restrict access to sensitive parts of the app  
- ✅ Ensure users perform only allowed actions  
- ✅ Implement security policies easily by assigning roles
- ✅ Make the application Secure
- ✅ Ensures privacy
- ✅ Prevents fraud
- ✅ Building user trust


---

## 3️⃣ How Does Spring Security Handle Roles?

- Spring Security treats roles as authorities with the prefix `ROLE_`.
- Role `"ADMIN"` is treated as `"ROLE_ADMIN"` internally.
- Endpoint access is controlled using `.hasRole("ADMIN")` or `.hasAnyRole("USER", "ADMIN")`.

---

## 4️⃣ Key Concepts and Classes

| Concept              | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| `UserDetails`        | Represents a user with username, password, and roles (authorities).         |
| `GrantedAuthority`   | Represents a role or permission granted to a user.                          |
| `UserDetailsService` | Loads user details (username, password, roles) from DB or memory.           |
| `SecurityFilterChain`| Configures HTTP security rules (who can access which URL).                  |
| `@EnableWebSecurity` | Enables Spring Security in your application.                                |

---

## 5️⃣ How to Implement Role-Based Authentication?

---

### ✅ Step 1: Define Users with Roles (In-Memory)

```
@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("user")
                           .password("{noop}userpass")
                           .roles("USER")
                           .build();

    UserDetails admin = User.withUsername("admin")
                            .password("{noop}adminpass")
                            .roles("ADMIN")
                            .build();

    return new InMemoryUserDetailsManager(user, admin);
}
```

### ✅ Step 2: Configure URL Access Based on Roles
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")               // Only ADMIN can access
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")     // USER or ADMIN can access
            .anyRequest().authenticated()                                // All others require auth
        )
        .formLogin(Customizer.withDefaults())  // Enable default login form
        .httpBasic(Customizer.withDefaults()); // Enable basic auth (optional)

    return http.build();
}


### ✅ Step 3: Create Controller Endpoints Based on Roles

@RestController
public class MyController {

    @GetMapping("/user/home")
    public String userHome() {
        return "Welcome User!";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin!";
    }
}

## 🧭 Summary — Role-Based Authentication Flow
User logs in with username/password.

Spring Security loads user details including roles.

When accessing a URL or method:

Spring checks if the user has the required role.

✅ Access is granted if authorized.

❌ If unauthorized, access is denied (403) or redirected to login.


## 📚 References

- [🔗 Spring Security In-Memory Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html)  
- [🔗 Spring Authorization Docs](https://docs.spring.io/spring-security/reference/servlet/authorization/index.html)

