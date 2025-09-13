## 1. What is OOP?
**Object-Oriented Programming (OOP)** is a paradigm that organizes code into objects, each containing data (fields) and actions (methods).

**Interview Tip:** Explain using real-world analogies (car, person, animal).

---

## 2. What are the four pillars of OOP?
- **Encapsulation:** Data hiding inside classes; access via methods.
- **Inheritance:** Child classes reuse parent class code.
- **Polymorphism:** One interface, many implementations.
- **Abstraction:** Hiding details, exposing essentials.

---

## 3. What is Encapsulation?
Encapsulation restricts direct access to fields and allows manipulation only via methods.

**Code Example:**
```java
class Person {
    private String name; // Hidden data

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```
**Interview Tip:** Emphasize why direct access (public fields) is bad.

---

## 4. How do you achieve Encapsulation in Java?
- Mark fields `private`.
- Provide public getter/setter methods.

**Code Example:**
```java
class Account {
    private double balance;

    public double getBalance() { return balance; }
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}
```
**Interview Tip:** Mention validation in setters for safety.

---

## 5. What is Inheritance?
Inheritance allows a class (child) to reuse and extend the behavior of another class (parent).

**Code Example:**
```java
class Animal { void eat() { System.out.println("Eating"); } }
class Dog extends Animal { void bark() { System.out.println("Bark!"); } }
```
**Interview Tip:** Use "is-a" relationship (Dog is an Animal).

---

## 6. What is the main advantage of Inheritance?
**Code reuse and hierarchical structure.**

**Interview Tip:** Stress avoiding duplicate code.

---

## 7. How do you implement Inheritance?
Use the `extends` keyword.

**Code Example:**
```java
class Car extends Vehicle {}
```
**Interview Tip:** Only one class can be extended (Java does not support multiple inheritance).

---

## 8. What is Polymorphism?
Polymorphism means "many forms". Methods behave differently depending on object type.

**Code Example:**
```java
Animal a = new Dog();
a.eat(); // Calls Dog's eat() if overridden
```
**Interview Tip:** Show runtime method choosing.

---

## 9. Types of Polymorphism in Java
- **Compile-time (Overloading):** Same method name, different parameters.
- **Run-time (Overriding):** Subclass redefines parent’s method.

**Interview Tip:** Mention "method signature" for overloading.

---

## 10. What is Abstraction?
Abstraction exposes only essential features, hiding implementation details.

**Code Example:**
```java
abstract class Shape {
    abstract void draw(); // Declaration only
}
```
**Interview Tip:** Focus on "what" not "how".

---

## 11. How is Abstraction achieved in Java?
- Using `abstract` classes and methods.
- Using `interfaces`.

---

## 12. What is a Class?
A class is a blueprint defining properties and behavior.

**Interview Tip:** Use "blueprint/recipe" analogy.

---

## 13. What is an Object?
An object is an instance of a class, occupying memory.

**Interview Tip:** "Baking a cake from a recipe".

---

## 14. Class vs Object
| Class      | Object             |
|------------|-------------------|
| Blueprint  | Instance          |
| No memory  | Uses memory       |
| Compile-time| Runtime          |

---

## 15. How do you create an Object in Java?
Use `new` keyword.

**Code Example:**
```java
Car myCar = new Car();
```

---

## 16. What is a method?
A block of code defining actions for objects.

**Interview Tip:** Methods = verbs of a class.

---

## 17. What are instance variables?
Non-static variables unique to each object.

---

## 18. What are local variables?
Variables inside methods, existing only during method execution.

---

## 19. What is a constructor?
Special method initializing object fields.

**Code Example:**
```java
class Person {
    String name;
    Person(String name) { this.name = name; }
}
```

---

## 20. Constructor rules
- Name = class name.
- No return type.

---

## 21. Default constructor
Provided by compiler if no constructor is defined.

**Code Example:**
```java
class Foo {}
// Compiler adds Foo() {}
```

---

## 22. Can constructors be overloaded?
Yes, with different parameter lists.

**Code Example:**
```java
class Point {
    int x, y;
    Point() { x = 0; y = 0; }
    Point(int x, int y) { this.x = x; this.y = y; }
}
```

---

## 23. What is a parameterized constructor?
Constructor accepting parameters.

---

## 24. Can a constructor be private?
Yes. Used in Singleton pattern.

**Code Example:**
```java
class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() { return instance; }
}
```

---

## 25. What is Singleton pattern?
Ensures only one instance of a class.  
**Interview Tip:** Mention thread safety for advanced interviews.

---

## 26. What is `this()`?
Calls another constructor in the same class.

**Code Example:**
```java
class Box {
    int w, h, l;
    Box(int side) { this(side, side, side); }
    Box(int w, int h, int l) { this.w = w; this.h = h; this.l = l; }
}
```

---

## 27. What is `this` keyword?
Refers to the current object instance.

**Code Example:**
```java
class Student {
    String name;
    Student(String name) { this.name = name; }
}
```
**Interview Tip:** Disambiguates local and instance variables.

---

## 28. What is `super` keyword?
Refers to parent class; used to access parent’s fields, methods, and constructors.

**Code Example:**
```java
class Parent { Parent(String s) {} }
class Child extends Parent {
    Child() { super("hello"); }
}
```

---

## 29. What is a static variable?
Class-level variable shared by all objects.

**Code Example:**
```java
class Counter { static int count = 0; }
```
**Interview Tip:** Use for counters, configurations.

---

## 30. What is a static method?
Method belonging to the class, not objects.

**Code Example:**
```java
class Utils {
    static int add(int a, int b) { return a + b; }
}
```
**Interview Tip:** Cannot access instance variables directly.

---

## 31. Can static methods use non-static members?
No. Static methods can only access static members.

---

## 32. What is a static block?
Initializes static data when class loads.

**Code Example:**
```java
class Demo {
    static { System.out.println("Loaded Demo class!"); }
}
```

---

## 33. What is a final variable?
Constant, value cannot change.

**Code Example:**
```java
final int MAX = 100;
```

---

## 34. What is a final method?
Cannot be overridden in subclass.

**Code Example:**
```java
class A {
    final void foo() {}
}
class B extends A {
    // void foo() {} // Compile error
}
```

---

## 35. What is a final class?
Cannot be subclassed.

**Code Example:**
```java
final class MyClass {}
```

---

## 36. Is String class final?
Yes, so it cannot be extended; ensures immutability.

---

## 37. Difference between final, finally, finalize
- `final`: Restricts inheritance/overriding.
- `finally`: Always runs after try-catch blocks.
- `finalize()`: (Deprecated) called by GC before object destruction.

**Code Example for finally:**
```java
try { int x = 1/0; } catch (Exception e) {} finally { System.out.println("Always runs."); }
```

---

## 38. What is Method Overloading?
Same method name, different parameters.

**Code Example:**
```java
class Math {
    int add(int a, int b) { return a+b; }
    double add(double a, double b) { return a+b; }
}
```

---

## 39. Is method overloading compile-time or runtime?
Compile-time.

---

## 40. Can you overload by changing only return type?
No.

---

## 41. Can you overload main()?
Yes, but JVM only runs the standard signature.

**Code Example:**
```java
public static void main(String[] args) {}
public static void main(int x) {}
```

---

## 42. What is Method Overriding?
Subclass provides its own implementation for a parent’s method.

**Code Example:**
```java
class Animal { void eat() { System.out.println("Animal eats"); } }
class Dog extends Animal { @Override void eat() { System.out.println("Dog eats"); } }
```

---

## 43. Is method overriding compile-time or runtime?
Runtime.

---

## 44. Rules for overriding
- Same method name and parameters.
- Same or covariant return type.
- Access level not more restrictive.

**Code Example:**
```java
class Base { protected void show() {} }
class Derived extends Base { public void show() {} }
```

---

## 45. Can you override private methods?
No; they are not visible to subclasses.

---

## 46. Can you override static methods?
No; static methods belong to class, not object (method hiding occurs).

**Code Example:**
```java
class Base { static void foo() {} }
class Derived extends Base { static void foo() {} }
```

---

## 47. Can you override final methods?
No.

---

## 48. What is covariant return type?
Subclass’s method can return a subtype of parent’s method return type.

**Code Example:**
```java
class Animal { Animal get() { return this; } }
class Dog extends Animal { Dog get() { return this; } }
```

---

## 49. What is an abstract method?
Method declared without implementation.

**Code Example:**
```java
abstract class Shape { abstract void draw(); }
```

---

## 50. What is an abstract class?
Class that cannot be instantiated; may have abstract and concrete methods.

---

## 51. Can abstract class have a constructor?
Yes, for initializing fields used in subclasses.

---

## 52. Can abstract class have concrete methods?
Yes.

---

## 53. What is an interface?
A contract; classes must implement all its methods.

**Code Example:**
```java
interface Drawable { void draw(); }
class Circle implements Drawable { public void draw() { System.out.println("Circle"); } }
```

---

## 54. Can you instantiate an interface?
No.

---

## 55. How do you use an interface in a class?
With `implements` keyword.

---

## 56. Can you implement multiple interfaces?
Yes.

**Code Example:**
```java
class Multi implements I1, I2 {}
```

---

## 57. Can a class extend multiple classes?
No.

---

## 58. Abstract class vs Interface

| Abstract Class | Interface           |
|--|--|
| Can have code, fields, constructors | Only method signatures (until Java 8) |
| Extend one | Implement many         |

---

## 59. Can interface have concrete methods?
Yes, since Java 8 (`default`/`static` methods).

**Code Example:**
```java
interface Test {
    default void foo() { System.out.println("Default"); }
}
```

---

## 60. When use abstract class vs interface?
**Abstract class:** Related hierarchy, shared code.  
**Interface:** Unrelated classes, contract.

---

## 61. What are the main class relationships?
- **Association (uses-a)**
- **Aggregation (has-a, weak)**
- **Composition (part-of, strong)**

---

## 62. What is Association?
Two independent classes interact, but can exist separately.

---

## 63. What is Aggregation?
One class contains another; contained object can exist independently.

**Code Example:**
```java
class Engine {}
class Car { Engine engine; }
```

---

## 64. Example of Aggregation
If car is destroyed, engine can be reused elsewhere.

---

## 65. What is Composition?
Contained object cannot exist without container.

**Code Example:**
```java
class Heart {}
class Human { private final Heart heart = new Heart(); }
```

---

## 66. Example of Composition
If human is destroyed, heart is destroyed too.

---

## 67. What is Coupling?
Degree of dependency between classes.  
**Interview Tip:** Low coupling is better.

---

## 68. What is Cohesion?
How focused a class is on a single task.  
**Interview Tip:** High cohesion is better.

---

## 69. What is the root class in Java?
`java.lang.Object`

---

## 70. Important Object class methods
`equals()`, `hashCode()`, `toString()`, `clone()`, `getClass()`

---

## 71. Why override toString()?
For meaningful output when printing objects.

**Code Example:**
```java
class Book {
    String title;
    public String toString() { return "Book: " + title; }
}
```

---

## 72. equals() and hashCode() contract
If two objects are equal by equals(), their hashCode() must be the same.

---

## 73. == vs .equals()
- `==`: Compares references.
- `.equals()`: Compares contents.

**Code Example:**
```java
String a = new String("hi");
String b = new String("hi");
System.out.println(a == b); // false
System.out.println(a.equals(b)); // true
```

---

## 74. What is an immutable object?
Object whose state cannot change after creation (e.g., `String`).

---

## 75. How do you create an immutable class?
- Make class `final`
- Fields `private final`
- No setters

**Code Example:**
```java
final class Immutable {
    private final int value;
    Immutable(int value) { this.value = value; }
    public int getValue() { return value; }
}
```

---

## 76. What is Singleton?
Class with only one instance.  
See answer 24.

---

## 77. What is Garbage Collection?
Automatic memory management by JVM.

---

## 78. Can you force garbage collection?
No; can only suggest via `System.gc()`.

---

## 79. What is an anonymous inner class?
Class without a name, declared and instantiated at the same time.

**Code Example:**
```java
Runnable r = new Runnable() {
    public void run() { System.out.println("Running!"); }
};
new Thread(r).start();
```

---

## 80. What is dynamic binding?
Method calls are resolved at runtime based on actual object.

---

## 81. Can a file have more than one public class?
No.

---

## 82. What is a marker interface?
Interface with no methods, marking a capability (e.g., `Serializable`).

---

## 83. What is super() for?
Calls parent’s constructor.

---

## 84. What’s the first line in a constructor?
`this()` or `super()`, or compiler inserts `super()`.

---

## 85. Can interfaces extend other interfaces?
Yes.

---

## 86. What is instanceof operator?
Checks object’s type.

**Code Example:**
```java
if (obj instanceof Animal) { ... }
```

---

## 87. What is a package?
Namespace for organizing classes.

---

## 88. What are access modifiers?
`public`, `protected`, default (package-private), `private`

---

## 89. Difference between protected and default?
Protected accessible in subclasses from any package; default only within same package.

---

## 90. Can a top-level class be private/protected?
No; only public or default.

---

## 91. What if you don’t provide any constructor?
Compiler adds a default constructor.

---

## 92. Can a constructor be inherited?
No; subclasses must call parent’s constructor.

---

## 93. What is method hiding?
Subclass’s static method hides parent’s static method.

**Code Example:**
```java
class Base { static void show() {} }
class Derived extends Base { static void show() {} }
```

---

## 94. Object vs Reference
Object = real entity in memory; Reference = variable pointing to object.

---

## 95. What is pass-by-value?
Java passes a copy of variables to methods. For objects, the reference is copied.

**Code Example:**
```java
class MyNumber { int value; }
void change(MyNumber num) { num.value = 100; }
```

---

## 96. Can main method be final?
Yes.

---

## 97. Can abstract class be final?
No; contradiction.

---

## 98. What is Diamond Problem?
Ambiguity in multiple inheritance; Java avoids it by not allowing multiple class inheritance.

---

## 99. What is a functional interface?
Interface with one abstract method, usable with lambdas.

**Code Example:**
```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}
Calculator add = (a, b) -> a + b;
System.out.println(add.operate(2, 3)); // Outputs 5
```

---

## 100. Why are strings immutable in Java?
- Security
- String pool
- Thread safety
- Hash code caching

---

## 101. What is constructor chaining?
Calling another constructor in the same class using `this()`.

**Code Example:**
```java
class Demo {
    int x, y;
    Demo() { this(0); }
    Demo(int x) { this(x, 0); }
    Demo(int x, int y) { this.x = x; this.y = y; }
}
```

---

## 102. Can you call a constructor from another constructor?
Yes, using `this()`.

---

## 103. What is an overloaded constructor?
Multiple constructors with different parameter lists.

---

## 104. Can abstract classes have static methods?
Yes.

---

## 105. Can interfaces have static methods?
Yes, since Java 8.

---

## 106. Can you have private methods in interfaces?
Yes, since Java 9.

---

## 107. Can abstract classes have final methods?
Yes.

---

## 108. Can interfaces have final methods?
No.

---

## 109. What is a nested class?
Class declared inside another class.

---

## 110. Types of nested classes
- Static nested class
- Inner class
- Local inner class
- Anonymous inner class

---

## 111. What is a static nested class?
A static class inside another class, can access only static members.

**Code Example:**
```java
class Outer {
    static class Inner { }
}
```

---

## 112. What is an inner class?
Non-static class inside another class, can access outer class's members.

---

## 113. Can you create objects inside methods?
Yes, via local inner classes.

---

## 114. What is method reference?
Syntax to refer to methods directly: `ClassName::methodName`

**Code Example:**
```java
List<String> names = Arrays.asList("A", "B");
names.forEach(System.out::println);
```

---

## 115. What is a default constructor?
No-argument constructor supplied by compiler if none exists.

---

## 116. Can you throw exceptions from constructors?
Yes.

**Code Example:**
```java
class FileReader {
    FileReader(String path) throws IOException { ... }
}
```

---

## 117. Can abstract class have main()?
Yes.

---

## 118. Can you instantiate an abstract class with anonymous inner class?
Yes.

**Code Example:**
```java
abstract class Animal { abstract void sound(); }
Animal a = new Animal() { void sound() { System.out.println("Meow"); } };
```

---

## 119. What is the difference between overloading and overriding?
- **Overloading:** Same method name, different parameters, same class.
- **Overriding:** Same method signature, subclass.

---

## 120. What is the benefit of OOP?
- Code reuse
- Modularity
- Maintainability
- Scalability

---

## More Easy Code Examples

### Constructor Chaining
```java
class Demo {
    int x, y;
    Demo() { this(0); }
    Demo(int x) { this(x, 0); }
    Demo(int x, int y) { this.x = x; this.y = y; }
}
```

### Anonymous Inner Class
```java
Runnable r = new Runnable() {
    public void run() { System.out.println("Running!"); }
};
new Thread(r).start();
```

---

## OOP Cheat Sheet

- **Encapsulation:** Hide data, use getter/setter.
- **Inheritance:** Use “extends”.
- **Polymorphism:** Use overridden methods, interfaces.
- **Abstraction:** Use abstract classes/interfaces.
- **Composition:** Contain other objects, manage lifecycle.
- **Aggregation:** Reference other objects, do not manage lifecycle.

---

## Quick Reference Table

| Keyword   | Purpose                 | Example Usage           |
|-----------|-------------------------|-------------------------|
| `this`    | Current object          | `this.name = name;`     |
| `super`   | Parent object           | `super();`              |
| `static`  | Class-level             | `static int count;`     |
| `final`   | No change/inheritance   | `final int MAX;`        |
| `abstract`| No implementation       | `abstract void run();`  |

---
