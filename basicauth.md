# 🔐 What is Basic Authentication?

**Basic Authentication** is a simple mechanism where the client sends the `username` and `password` in every request header — **Base64 encoded**.

---

### ✅ Key Points:
- No login form or UI is shown
- Commonly used in:
  - REST APIs
  - CLI tools
  - Postman / API testing
- Lightweight and quick to implement, but **not secure without HTTPS**

---

### 🔧 Example: HTTP Basic Login Configuration

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(request -> request.anyRequest().authenticated())
        .httpBasic(withDefaults());  // Enables Basic Auth
    return http.build();
}

### 🔄 How It Works

✅ Client tries to access a protected resource.

✅ Server responds with:

401 Unauthorized  
WWW-Authenticate: Basic realm="Realm"
Client resends the request with credentials encoded in Base64:

Authorization: Basic base64(username:password)

**🔐 Example Authorization Header**

Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
This is Base64 encoding for username:password.

**Important Notes**

Basic Auth credentials can be easily decoded because Base64 is not encryption.

Always use HTTPS to protect credentials in transit.

Basic Auth is useful for quick prototyping and API testing but not recommended for production-level security.

For more secure authentication, consider token-based methods like JWT or OAuth2.
