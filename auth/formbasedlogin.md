## 🔒 1. Form-Based Login
**public SecurityFilterChain filterChain(HttpSecurity http) {
	http
		.formLogin(withDefaults());
	// ...
}**


## 👨‍💻 Customization (optional):

**public SecurityFilterChain filterChain(HttpSecurity http) {
	http
		.formLogin(form -> form
			.loginPage("/login")
			.permitAll()
		);
	// ...
}**

## 👨‍💻 Customization (optional):

**.formLogin(form -> form
    .loginPage("/my-login") // custom login page
    .defaultSuccessUrl("/dashboard", true)
    .failureUrl("/my-login?error=true")
)**

## ✅ How it works:

✅When a user tries to access a protected resource:

✅They are redirected to a login page (/login).

✅After entering username and password:

✅Spring Security validates the credentials.

✅If valid → redirect to the originally requested page.

✅If invalid → redirect to /login?error.



