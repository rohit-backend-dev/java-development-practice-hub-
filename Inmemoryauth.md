# 🔹 In-Memory Authentication in Spring Boot

## What is In-Memory Authentication?

In-Memory Authentication means storing user credentials (username/password) and roles **directly inside the application configuration**, without connecting to any external system like a database.

---

## When to Use?

- For development, testing, or interview demos.
- When you don’t need dynamic user management.
- Quick setup without database dependency.

---

## Example Configuration

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("raju")
                .password("{noop}1234")  // {noop} means plain text password (no encryption)
                .roles("USER")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Enables Basic Auth
        return http.build();
    }
}


## 🔐 Password Encoding Notes

{noop} means no encryption – the password is stored as plain text (use only for testing or demo).

For production, always use password encoding like bcrypt.


**You can define users with bcrypt encoded passwords as follows:**

@Bean
public UserDetailsService users() {
    UserDetails user = User.builder()
        .username("user")
        .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")  // bcrypt encoded password
        .roles("USER")
        .build();

    UserDetails admin = User.builder()
        .username("admin")
        .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")  // bcrypt encoded password
        .roles("USER", "ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user, admin);
}


### ⚠️ Important:

Never use {noop} in production.

Always store passwords securely using encoders like bcrypt.

Use PasswordEncoder beans to handle encoding and matching securely.

### Summary
In-Memory Authentication is fast and simple for testing but not scalable.

Suitable for demos, learning, and small apps without user management needs.

For production, use database-backed authentication with encrypted passwords.
