# World Before Microservices

Before microservices, most applications were built using a **monolithic architecture**. In this setup, all parts of the application—like the user interface, business logic, and database access—are connected and depend on each other. Everything is built and run as one big unit.

## What is a Monolithic Application?

A **monolithic application** is a single, unified software program where all the features and functions are developed, managed, and deployed together. All parts of the app (such as the UI, backend logic, and database access) live in one codebase and are packaged as a single unit (for example, a single .jar, .war, or .exe file).

### Example

If you build an online shopping website as a monolithic application, the login system, product catalog, payment processing, and order management are all coded and deployed together in one project.

### How it Works

- All modules and features are tightly linked and run as one process.
- To update or fix one part, you have to redeploy the entire application.
- All developers work in the same codebase and use the same technology stack.

Monolithic applications are simple to start and deploy, but as they grow, they can become harder to manage, scale, and update.

## Key Characteristics

- **Single Codebase:** The whole app is in one place.
- **Tightly Coupled:** All parts work closely together.
- **Scaling is Hard:** To handle more users, you must run more copies of the entire app.
- **Maintenance is Tough:** Changing one part can affect the whole app.
- **Single Language:** Usually, the whole application is written in one programming language. For example, if you start with Java, you must use Java for the entire process.

## Typical Workflow

1. All features are built and released together.
2. Any update means you redeploy the whole app.
3. If one part fails, the entire app can stop working.
4. You are limited to a single technology stack.

## Demerits of Monolithic Architecture

- **Scaling is Difficult:** If your app needs to handle more users, you must make copies of the whole app, even if only one part is busy. You can't just scale the part that needs more resources.
- **Development is Slow:** As the code gets bigger, it becomes hard for many developers to work at the same time without causing problems. Everyone is working in the same big codebase, which can lead to more mistakes and conflicts.
- **Updating is Hard:** Even a small change in one part of the app means you have to test and redeploy the whole app. This takes more time and increases the risk of something going wrong.
- **Limited Technology:** You are usually stuck with one programming language or framework for the entire app, even if some parts would work better with another technology.
- **Downtime Risk:** If one part of the app fails, it can cause the entire app to stop working. There is no way to isolate the problem to just one part.
- **Maintenance is Challenging:** As the app grows, it becomes harder to understand, fix, or improve. Finding and solving bugs can take a lot of time because everything is connected.

In summary, monolithic apps are simple to start with but become harder to manage, update, and scale as they get bigger.
