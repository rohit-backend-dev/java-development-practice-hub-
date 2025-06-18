# 🔐 Password Hashing and Encoding in Spring Boot - A Complete Guide

Password hashing is a critical part of securing your Spring Boot application. 
This document simplifies all concepts around password encoding, storage, and matching,
and explains various algorithms supported by Spring Security in a clean and structured way.

---

## 🔍 What is Password Hashing?

Hashing is the process of converting a password (plain text) into an irreversible fixed-length string.
It protects stored passwords from being revealed even if the data is compromised. Unlike encryption, hashing cannot be reversed.


### 🔐 Here's how it works:

1.You type your password (like mySecret123) while signing up.
2.The system runs it through a hashing algorithm (like bcrypt).
3.The algorithm converts it into a long, random-looking string like:**$2a$10$EIBb38kXG7fZ8KjOS32gUOo2BfGJ8Fnk54gA1eIoT/BRszP3BLPIW**
4.This hashed string gets saved in the database, not your actual password.

---

### How Hashed Passwords are Confirmed

A hashed password will be confirmed like so:
1.When you create an account, your password is turned into a hash and saved in the database.
2.When you log in, the password you type is hashed again.
3.The system compares the new hash with the saved one.
4.If both hashes are the same, it means your password is correct.
5.Since passwords are stored as hashes (not plain text), even if someone hacks the database, they can’t see your real password.
6.This is safer than encryption because hashes can’t be reversed back to the original password.


---

## 🔐 DelegatingPasswordEncoder 
"DelegatingPasswordEncoder is a flexible password encoder provided by Spring Security that supports multiple encoding algorithms like bcrypt, scrypt, pbkdf2, and others. 
It stores the algorithm id as a prefix in the hash (e.g., {bcrypt}) to identify which encoder to use when verifying passwords."

**Simple Explanation**
Think of DelegatingPasswordEncoder as a password manager that:
Knows many encoding algorithms.
Uses a default algorithm (like bcrypt,scrypt..) to encode new passwords.
Can decode/check passwords encoded with different algorithms, thanks to the prefix stored in the hash.

### ✅ Example:

{bcrypt}$2a$10$...hashedPassword...

### How It Works:

* **Encoding:** Uses the encoder specified by `idForEncode`, e.g., `bcrypt`.
* **Matching:** Finds the matching encoder using the prefix (e.g., `{bcrypt}`) and verifies the password.

### Fallback:

If no prefix is found or the encoder is missing, it throws:
IllegalArgumentException: No PasswordEncoder mapped for id "null"


**You can fix this by:**
* Adding a proper prefix.
* Registering the correct encoder.
* Using `setDefaultPasswordEncoderForMatches().

---

## 🔨 Creating Users with Encoded Passwords

### For Demos:

```java
UserDetails user = User.withDefaultPasswordEncoder()
  .username("user")
  .password("password")
  .roles("USER")
  .build(); // -> Not secure for production – exposes password in memory and code.
```
---

## 💡 Using Spring Boot CLI to Encode a Password
You can quickly generate a hashed (encoded) password using Spring Boot CLI with the following command:

**🔧 Command:** spring encodepassword yourPasswordHere
For example:
spring encodepassword mySecret123

📤 Output:{bcrypt}$2a$10$eJ9v2sKpZAvx9F2V1wItieSRfUO2jB3XzT0BcWxyx.g/6iRlZXOoG

This is the easiest way to generate production-grade hashes.

---

## 🔧 Custom PasswordEncoder Configuration

You can define your own bean:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}
```
Or revert for legacy systems:

```java
@Bean
public static NoOpPasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
} // NoOpPasswordEncoder.getInstance() -> Stores plain text — NEVER use in production!
```

---

## 💪 Supported Algorithms in Spring Security

### 1. **BCryptPasswordEncoder** (Most Common)
BCryptPasswordEncoder is a secure and widely used hashing algorithm provided by Spring Security. 
It is designed to be slow and adaptive, making it highly resistant to brute-force and rainbow table attacks.

### Usage
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
String hash = encoder.encode("myPassword");
encoder.matches("myPassword", hash);

**🛠️ Spring Boot Bean for BCryptPasswordEncoder** :

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

**📌 Features:**
1.Adjustable strength (log rounds), default is 10.
2.Built-in random salt to ensure unique hashes.
3.One-way hashing – irreversible.
4.Secure by design – mitigates rainbow table and brute-force attacks.

### 2. **Argon2PasswordEncoder** (Advanced, Memory-hard)

Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
Argon2PasswordEncoder is a modern, memory-hard password hashing algorithm introduced in Spring Security 5.0+,
and updated in v5.8+. It is highly resistant to GPU and ASIC-based brute-force attacks.

### Usage
Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
String hash = encoder.encode("myPassword");
boolean match = encoder.matches("myPassword", hash);


**🛠️ Spring Boot Bean for Argon2PasswordEncoder** :
@Bean
public PasswordEncoder passwordEncoder() {
    return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
}

**📌 Features:**
1. Winner of Password Hashing Competition
2. Requires BouncyCastle
3. Good against GPU attacks


### 3. **Pbkdf2PasswordEncoder** (FIPS-compliant)

Pbkdf2PasswordEncoder implements the PBKDF2 (Password-Based Key Derivation Function 2) algorithm. It is FIPS 140-2 compliant, making it ideal for government and regulated environments.

### Usage

Pbkdf2PasswordEncoder encoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
String hash = encoder.encode("myPassword");
boolean match = encoder.matches("myPassword", hash);

**custom salt generation:(OPTIONAL)**

SecureRandom random = new SecureRandom();
byte[] salt = new byte[16];
random.nextBytes(salt);

**🛠️Spring Boot Bean for Pbkdf2PasswordEncoder** :
@Bean
public PasswordEncoder passwordEncoder() {
    return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
}


📌 Features:
1. FIPS-compliant (uses standard cryptographic primitives)
2. Based on HMAC + iteration count
3. Slower hashing deters brute-force attacks
4. Supports configurable secret keys, iteration count, and hash width


### 4. **SCryptPasswordEncoder**

SCryptPasswordEncoder implements the scrypt hashing algorithm, designed to be computationally
and memory expensive, offering strong defense against brute-force and hardware attacks.

### Usage:

SCryptPasswordEncoder encoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();

String hash = encoder.encode("myPassword");
boolean match = encoder.matches("myPassword", hash);

**🛠️Spring Boot Bean for SCryptPasswordEncoder** :
@Bean
public PasswordEncoder passwordEncoder() {
    return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
}

📌 Features:
1. Memory-hard and CPU-intensive — strong resistance to parallel attacks
2. Effective against GPU/ASIC brute-force cracking
3. Parameters include CPU cost, memory cost, and parallelization
4.Ideal for apps requiring extra protection beyond bcrypt

---

## 🛠 Changing Passwords

Spring supports discovery endpoints for password managers:

http.passwordManagement(Customizer.withDefaults());

Redirects `/.well-known/change-password` to `/change-password`.

Customize:

http.passwordManagement(management -> management.changePasswordPage("/update-password"));


---

## 🧠 Compromised Password Checker

Spring can integrate with [HaveIBeenPwned](https://haveibeenpwned.com) to check for leaked passwords.

### Configuration:

```java
   // Main security filter chain configuration
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Require authentication for all requests
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                // Use custom failure handler during login
                .failureHandler(new CompromisedPasswordAuthenticationFailureHandler())
            );
        return http.build();
    }


  // Bean to check if a password is compromised using HaveIBeenPwned API
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
```

### Handling Failure:


```java
.formLogin(login -> login
    .failureHandler(new CompromisedPasswordAuthenticationFailureHandler())
)
```

### Custom Handler:

 **Custom AuthenticationFailureHandler to detect if login failed due to a compromised password.**

```java
static class CompromisedPasswordAuthenticationFailureHandler implements AuthenticationFailureHandler {

    // Default failure handler to redirect users to /login?error for normal authentication failures
    private final SimpleUrlAuthenticationFailureHandler defaultFailureHandler =
            new SimpleUrlAuthenticationFailureHandler("/login?error");

    // Redirect strategy used to manually redirect users in case of compromised passwords
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        org.springframework.security.core.AuthenticationException exception)
            throws IOException, ServletException {

        // Check if the authentication failed due to a compromised password
        if (exception instanceof CompromisedPasswordException) {
            redirectStrategy.sendRedirect(request, response, "/reset-password");
            return;
        }

        // For all other types of authentication failures, fall back to default behavior
        defaultFailureHandler.onAuthenticationFailure(request, response, exception);
    }
}
```

---

## ✅ Best Practices

* Always use adaptive hashing algorithms (bcrypt, argon2, etc.)
* Never store plain text passwords.
* Use `DelegatingPasswordEncoder` to support multiple formats.
* Prefix all hashed passwords (e.g., `{bcrypt}`...)
* Use CLI or services to pre-hash passwords securely.
* Integrate compromise-checking in sensitive systems.

