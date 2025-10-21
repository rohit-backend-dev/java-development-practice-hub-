# **Step 1: Prepare and Run Your Backend (Spring Boot)**

### 1.1 Navigate to Your Project Folder

Open PowerShell or Terminal and go to your backend project folder:

```powershell
cd "C:\Users\rohit\Desktop (1)\demo\demo"
```

Check your current folder:

```powershell
dir
```

You should see `pom.xml` and `src` folder.

---

### 1.2 Build the JAR File

Use the Maven wrapper to build your backend:

```powershell
.\mvnw clean package
```

* Output JAR file will be located in `target/demo-0.0.1-SNAPSHOT.jar`.

---

### 1.3 Run Backend Locally

Run the Spring Boot application:

```powershell
java -jar ".\target\demo-0.0.1-SNAPSHOT.jar"
```

* By default, it runs on:

```
http://localhost:8081/api/users
```

* You can change the port in `src/main/resources/application.properties`:

```properties
server.port=8081
```

---

### 1.4 Enable CORS (Cross-Origin Requests)

To allow your React frontend to communicate with the backend:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000"); // Replace with frontend URL in production
    }
}
```

---

# **Step 2: Prepare and Run Your Frontend (React)**

### 2.1 Verify React App

Make sure `App.js` is configured to fetch data from backend:

```javascript
const backendURL = "http://localhost:8081/api/users";
```

* Test forms and tables locally to ensure proper backend communication.

---

### 2.2 Run React Locally

```bash
npm install
npm start
```

* Opens app at:

```
http://localhost:3000
```

---

### 2.3 Build React for Production

```bash
npm run build
```

* Generates a `build/` folder containing static files.

---

# **Step 3: Host Backend (Spring Boot)**

### 3.1 Choose a Hosting Platform

Free cloud hosting options:

* [Railway](https://railway.app/)
* [Render](https://render.com/)
* [Fly.io](https://fly.io/)

---

### 3.2 Deploy Backend on Railway (Example)

1. Push your backend code to **GitHub**.
2. Log in to Railway → Create new project → Deploy from GitHub.
3. Railway automatically builds the JAR.
4. After deployment, you’ll get a public HTTPS URL, e.g.:

```
https://your-backend.up.railway.app/api/users
```

---

### 3.3 Update Backend URL in React

Change the URL in your React app:

```javascript
const backendURL = "https://your-backend.up.railway.app/api/users";
```

* Ensure CORS is updated on the backend to allow your frontend domain.

---

# **Step 4: Host Frontend (React)**

### 4.1 Deploy on Netlify

1. Go to [Netlify](https://www.netlify.com/) → Create new site → Deploy from GitHub → Select repo.
2. Set **Build Command**:

```bash
npm run build
```

3. Set **Publish Directory**:

```
build
```

4. Click Deploy → Netlify provides HTTPS URL:

```
https://your-frontend.netlify.app
```

---

### 4.2 Deploy on Vercel (Alternative)

1. Go to [Vercel](https://vercel.com/) → Import GitHub repo.
2. Vercel automatically runs `npm run build` and deploys the app.
3. You get HTTPS URL like:

```
https://your-frontend.vercel.app
```

---

# **Step 5: Test Full Stack**

1. Open your frontend URL (Netlify/Vercel).
2. Fill out forms → Submit.
3. Check backend logs on Railway/Render → Data should be saved.
4. If using H2 database, open `/h2-console` to verify records, or connect to production DB.

---

# **Step 6: Optional – Serve Frontend from Backend**

1. Copy the React `build/` folder into Spring Boot:

```
src/main/resources/static
```

2. Run backend:

```powershell
java -jar ".\target\demo-0.0.1-SNAPSHOT.jar"
```

3. React app is now served via backend at:

```
http://localhost:8081/
```

* API endpoints remain at `/api/users`.

---

✅ With this setup, your **Spring Boot backend** and **React frontend** are production-ready and can be hosted securely on the cloud.

