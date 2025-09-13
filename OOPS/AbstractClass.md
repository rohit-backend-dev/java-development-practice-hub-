
# 📘 Abstract Classes & Methods in Java (Easy → Advanced)
### Abstract Methods and Abstract Classes

An **abstract method** is a method that's declared without an implementation. You can only declare an abstract method inside an **abstract class**, which is a class that cannot be instantiated on its own. The purpose of this design is to create a contract for subclasses, forcing them to provide their own specific implementation of the abstract methods. This is a core concept of **abstraction** and **polymorphism** in object-oriented programming.

---

### Basic Concepts

* **Abstract Class:** Declared using the `abstract` keyword. You can't create an object of an abstract class. It can have abstract methods, concrete (regular) methods, constructors, and instance variables. It can also be a parent class for other abstract classes.
    
* **Abstract Method:** Declared with the `abstract` keyword and ends with a semicolon. It has no method body. A class containing an abstract method must be declared as abstract.
    
* **Subclass Responsibility:** Any non-abstract (concrete) class that extends an abstract class **must** provide an implementation for all of its inherited abstract methods. If it doesn't, it must also be declared abstract.

### Advanced Concepts

* **Constructors in Abstract Classes:** Abstract classes can have constructors. They are called by the constructor of the subclass using `super()`. This is useful for initializing instance variables shared by all subclasses.
    
* **Final Methods:** An abstract class can have `final` methods, which cannot be overridden by subclasses. This can be used to define a behavior that's common to all subclasses and shouldn't be changed.
    
* **Static Methods:** An abstract class can contain `static` methods. These methods are associated with the class itself, not an instance, so they can be called directly using the class name.
    
* **Interface vs. Abstract Class:**
    * **Interface:** A blueprint for a class. All methods are implicitly public and abstract (unless they have a default or static modifier). A class can implement multiple interfaces.
    * **Abstract Class:** A class that can't be instantiated. It can have both abstract and concrete methods, and instance variables. A class can only extend one abstract class. 

---

### Adapter Classes

An **adapter class** is a concrete class that provides a default, often empty, implementation for all methods in an interface. It's used when an interface has multiple methods, but a class only needs to use one or two of them. Instead of implementing the interface directly and being forced to provide empty implementations for all methods, a developer can extend the adapter class and only override the methods they need.

* **Example:** In Java's AWT and Swing, `MouseListener` is an interface with five methods. Implementing this interface requires you to write code for all five methods, even if you only care about `mouseClicked()`. The **`MouseAdapter`** class provides empty implementations for all these methods. You can simply extend `MouseAdapter` and override `mouseClicked()` without worrying about the others.

---

### HttpServlet

`HttpServlet` is an abstract class in the Java Servlet API. It's the most commonly used class for creating web servlets. It provides the framework for handling HTTP requests.

* **How it works:**
    * `HttpServlet` is an abstract class because it provides a template for handling different types of HTTP requests (GET, POST, PUT, DELETE, etc.) through methods like `doGet()`, `doPost()`, `doPut()`, and `doDelete()`.
    * These `doXxx()` methods are **concrete** (not abstract) in `HttpServlet`, but they typically return an error or a "method not supported" response. You, as the developer, are expected to override the specific methods you need to handle.
    * When an HTTP request arrives, the servlet container (e.g., Tomcat) calls `service()` on your servlet. `HttpServlet`'s `service()` method examines the request type (e.g., GET) and internally calls the appropriate `doGet()`, `doPost()`, or other `doXxx()` method.
    * This design is a perfect example of the **Template Method Design Pattern**, where the `service()` method defines a general algorithm, and the `doXxx()` methods provide the specific steps that are customized by subclasses.
---

## 1. 🔑 What is an Abstract Class?

* An **abstract class** is a class declared with the `abstract` keyword.
* It **cannot be instantiated** (you cannot create objects directly).
* It can have:

  * **Abstract methods** (no body, must be implemented by subclasses).
  * **Concrete methods** (normal methods with body).
  * **Constructors** (for initializing common data).
  * **Instance variables** (fields).
  * **Static methods** (common utility methods).
  * **Final methods** (cannot be overridden).

👉 Think of it as a **blueprint for subclasses**.

### Example:

```java
// Abstract class
abstract class Animal {
    String name;

    // Constructor
    Animal(String name) {
        this.name = name;
    }

    // Abstract method (no body)
    abstract void makeSound();

    // Concrete method
    void sleep() {
        System.out.println(name + " is sleeping...");
    }

    // Final method (cannot be overridden)
    final void eat() {
        System.out.println(name + " is eating...");
    }

    // Static method
    static void info() {
        System.out.println("All animals need food and sleep.");
    }
}

// Concrete subclass
class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " says Woof!");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Animal.info();  // Static method call

        Animal dog = new Dog("Tommy");
        dog.makeSound();  // Abstract method implementation
        dog.sleep();      // Inherited concrete method
        dog.eat();        // Final method
    }
}
```

✅ Output:

```
All animals need food and sleep.
Tommy says Woof!
Tommy is sleeping...
Tommy is eating...
```

---

## 2. 🔑 Abstract Methods

* Declared using `abstract` keyword.
* No body (implementation comes from subclass).
* Subclass **must override** them, unless subclass is also declared abstract.

```java
abstract class Shape {
    abstract double area();  // abstract method
}

class Circle extends Shape {
    double r;
    Circle(double r) {
        this.r = r;
    }
    @Override
    double area() {
        return Math.PI * r * r;
    }
}
```

---

## 3. 🔑 Rules to Remember (Interview Friendly)

* You **cannot create objects** of abstract class.
* Abstract class can have **0 or more abstract methods**.
* If a subclass does **not** implement all abstract methods, it must also be abstract.
* **Constructors** are allowed and invoked via `super()`.
* Abstract class **can extend another abstract class**.
* A class can **only extend one abstract class** (Java has single inheritance).
* But a class can **implement multiple interfaces**.

---

## 4. 🔑 Abstract Class vs Interface

| Feature      | Abstract Class                                                | Interface                                                 |
| ------------ | ------------------------------------------------------------- | --------------------------------------------------------- |
| Keywords     | `abstract`                                                    | `interface`                                               |
| Methods      | Both abstract & concrete                                      | By default abstract (Java 8+ allows `default` & `static`) |
| Variables    | Instance & static variables allowed                           | Only `public static final` constants                      |
| Inheritance  | One abstract class only                                       | Multiple interfaces allowed                               |
| When to use? | When you need **partial implementation** and **shared state** | When you only need a **contract/blueprint**               |

👉 **Interview tip:**

* **Interface** = capability (`Flyable`, `Runnable`).
* **Abstract class** = "is-a" relationship with shared code (`Animal`, `Vehicle`).

---

## 5. 🔑 Adapter Classes

* Some interfaces have **too many methods**.
* If you implement directly, you must override all (even unwanted).
* **Adapter class** provides **empty implementations**, so you can override only the methods you need.

### Example: MouseListener vs MouseAdapter

```java
import java.awt.event.*;

class MyMouse extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at " + e.getPoint());
    }
}
```

👉 Instead of implementing **all 5 methods** of `MouseListener`, you just extend `MouseAdapter`.

---

## 6. 🔑 Real-World Example: HttpServlet

* `HttpServlet` is an **abstract class** in Java Servlet API.
* Why abstract?

  * It provides a **template** for handling HTTP requests.
  * It has methods like `doGet()`, `doPost()`, `doPut()`, etc.
  * These methods are concrete but default to **"method not supported"** response.
  * You override only what you need.

### Example:

```java
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello from GET request!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello from POST request!");
    }
}
```

👉 The `service()` method in `HttpServlet` internally decides:

* If request = GET → calls `doGet()`.
* If request = POST → calls `doPost()`.

This is **Template Method Design Pattern**:

* **service()** defines the algorithm.
* **doGet/doPost** are the customizable steps.

---


# 7. 🔑 Advanced Interview Questions with Code

---

### 1. **Can abstract class have a constructor?**

✅ Yes. Used to initialize common fields.

```java
abstract class Animal {
    String name;

    // Constructor in abstract class
    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called for " + name);
    }

    abstract void sound();
}

class Dog extends Animal {
    Dog(String name) {
        super(name); // calling abstract class constructor
    }

    @Override
    void sound() {
        System.out.println(name + " barks");
    }
}

public class Test1 {
    public static void main(String[] args) {
        Dog d = new Dog("Buddy");
        d.sound();
    }
}
```

---

### 2. **Can abstract class have final methods?**

✅ Yes. Subclasses cannot override them.

```java
abstract class Vehicle {
    public final void fuelType() {
        System.out.println("Uses petrol or diesel");
    }
    abstract void start();
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car is starting...");
    }
}
```

---

### 3. **Can abstract class have static methods?**

✅ Yes. Belongs to the class, not the instance.

```java
abstract class MathUtils {
    public static int square(int n) {
        return n * n;
    }
    abstract void calculate();
}
```

---

### 4. **Can abstract class have main method?**

✅ Yes. You can run it like a normal class.

```java
abstract class App {
    public static void main(String[] args) {
        System.out.println("Abstract class main method is running!");
    }
}
```

---

### 5. **Can abstract class extend a concrete class?**

✅ Yes. It inherits normal behavior, and can add abstract methods.

```java
class Person {
    void eat() {
        System.out.println("Eating food...");
    }
}

abstract class Employee extends Person {
    abstract void work();
}
```

---

### 6. **When to choose abstract class over interface?**

👉 Use when you want **shared state + partial implementation**.

```java
abstract class Shape {
    String color = "Red";
    void displayColor() {
        System.out.println("Color: " + color);
    }
    abstract double area();
}
```

---

## 🔥 Extra Advanced Questions

---

### 7. **Can abstract class have non-abstract methods?**

✅ Yes. They can provide **partial implementation**.

```java
abstract class Bank {
    void welcome() {
        System.out.println("Welcome to the bank!");
    }
    abstract void loanInterest();
}

class SBI extends Bank {
    @Override
    void loanInterest() {
        System.out.println("SBI loan interest = 8%");
    }
}
```

---

### 8. **Can abstract class implement an interface?**

✅ Yes. But it doesn’t need to implement all methods.
The subclass must finish the implementation.

```java
interface Printable {
    void print();
}

abstract class Document implements Printable {
    // not implementing print() here
}

class Report extends Document {
    @Override
    public void print() {
        System.out.println("Printing report...");
    }
}
```

---

### 9. **Can abstract class have private methods?**

✅ Yes, since Java 9.
They are useful for **helper methods** inside the class.

```java
abstract class Logger {
    public void log(String msg) {
        format(msg);
        System.out.println("Log: " + msg);
    }

    private void format(String msg) {
        System.out.println("Formatted: " + msg.toUpperCase());
    }
}
```

---

### 10. **Can abstract class be final?**

❌ No.
Because **final class cannot be extended**, but abstract class is meant to be extended.
This is a contradiction → compiler error.

```java
// final abstract class Test {}  // ❌ Compilation Error
```

---

### 11. **Can abstract class extend another abstract class?**

✅ Yes. A subclass can extend an abstract parent and may or may not provide full implementation.

```java
abstract class A {
    abstract void methodA();
}

abstract class B extends A {
    // Still abstract because methodA not implemented
}

class C extends B {
    @Override
    void methodA() {
        System.out.println("Implemented in C");
    }
}
```

---

### 12. **Can abstract class have overloaded methods?**

✅ Yes. Abstract methods can be overloaded by having different parameter lists.

```java
abstract class Calculator {
    abstract int add(int a, int b);
    abstract int add(int a, int b, int c);
}

class MyCalc extends Calculator {
    int add(int a, int b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
```

---

✅ **Final Summary for Interviews**

* Constructor → Yes, runs during subclass instantiation.
* Final methods → Yes, not overridable.
* Static methods → Yes, belong to class.
* Main method → Yes, executable.
* Extends concrete class → Yes.
* Abstract + non-abstract methods → Yes (partial impl).
* Implements interface → Yes.
* Private methods → Yes (since Java 9).
* Abstract + final → ❌ Not allowed.
* Extends another abstract class → Yes.

  
* Method overloading in abstract class → Yes.

