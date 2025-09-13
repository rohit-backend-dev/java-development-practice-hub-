
### \#\# Core OOP Principles 🏛️

#### 1\. What is OOP?

**Answer:** OOP, or Object-Oriented Programming, is a programming paradigm that revolves around the concept of "objects." Instead of viewing a program as a sequence of procedural commands, OOP organizes software design around data, or objects, which can contain both data fields (attributes) and code in the form of procedures (methods). This approach makes code more modular, reusable, and easier to maintain.

#### 2\. What are the four main principles of OOP?

**Answer:** The four foundational principles of Object-Oriented Programming are:

  * **Encapsulation:** Bundling data and methods that work on that data within one unit.
  * **Inheritance:** Enabling a new class to take on the properties and methods of an existing class.
  * **Polymorphism:** Allowing a single interface (like a method) to be used for different underlying forms (data types).
  * **Abstraction:** Hiding the complex reality while exposing only the essential parts.

-----

#### 3\. What is Encapsulation?

**Answer:** Encapsulation is the practice of bundling an object's data (instance variables) and the methods that operate on that data into a single unit (a class). It also involves restricting direct access to some of an object's components, which is known as **data hiding**. By making data fields `private`, we protect an object's internal state from outside interference.

#### 4\. How do you achieve Encapsulation in Java?

**Answer:** Encapsulation is achieved in Java by:

1.  Declaring the instance variables of a class as `private`.
2.  Providing public `setter` and `getter` methods to view and modify the variable values.

**Code Example for Encapsulation:**

```java
// A class demonstrating Encapsulation
class BankAccount {
    // This data is private and hidden from outside classes.
    private double balance;

    // Public getter method to safely access the balance.
    public double getBalance() {
        return balance;
    }

    // Public setter method to deposit money.
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }
    
    // Public method to withdraw, containing validation logic.
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        } else {
            System.out.println("Withdrawal failed.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount();
        // You cannot do this due to encapsulation:
        // myAccount.balance = 10000; // Compile Error!

        // You must use the public methods.
        myAccount.deposit(500.0);
        myAccount.withdraw(200.0);
        System.out.println("Current Balance: " + myAccount.getBalance());
    }
}
```

-----

#### 5\. What is Inheritance?

**Answer:** Inheritance is a mechanism where one class (known as a subclass or child class) can inherit fields and methods from another class (known as a superclass or parent class). The subclass can use the inherited members as-is and can also add new fields and methods or override existing methods.

#### 6\. What is the main advantage of Inheritance?

**Answer:** The primary advantage is **code reusability**. You can create a new class that builds upon the logic of an existing class without rewriting the code. It also promotes a hierarchical classification (an "is-a" relationship).

#### 7\. Which keyword is used for inheritance?

**Answer:** The `extends` keyword is used to create a subclass.

**Code Example for Inheritance:**

```java
// Superclass (Parent)
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}

// Subclass (Child) inherits from Animal
class Dog extends Animal {
    // The Dog class now has the eat() method.
    void bark() {
        System.out.println("The dog barks.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat();  // Method inherited from Animal
        myDog.bark(); // Method defined in Dog
    }
}
```

-----

#### 8\. What is Polymorphism?

**Answer:** Polymorphism (from Greek, meaning "many forms") is the ability of a method or an object to take on many forms. In Java, it means a single action can be performed in different ways. For example, you can have a single method name that behaves differently for different objects.

#### 9\. What are the two types of Polymorphism in Java?

**Answer:**

1.  **Compile-time Polymorphism (Static Binding):** Achieved through **method overloading**. The compiler knows which method to call at compile time.
2.  **Runtime Polymorphism (Dynamic Binding):** Achieved through **method overriding**. The JVM determines which method to call at runtime based on the actual object type.

*(See questions 38-48 for detailed code examples on Overloading and Overriding).*

-----

#### 10\. What is Abstraction?

**Answer:** Abstraction is the concept of hiding the internal implementation details of an object and only exposing the necessary functionalities to the user. It focuses on *what* an object does rather than *how* it does it.

#### 11\. How is Abstraction achieved in Java?

**Answer:** Abstraction is primarily achieved using `abstract` classes and `interfaces`. An `abstract` class can have both abstract (unimplemented) and concrete methods, while an interface (before Java 8) could only have abstract methods.

*(See questions 49-60 for detailed code examples on Abstract Classes and Interfaces).*

-----

### \#\# Classes and Objects 🧱

#### 12\. What is a Class?

**Answer:** A class is a blueprint, template, or prototype that defines the variables and methods common to all objects of a certain kind. It's a logical entity that doesn't occupy any memory space itself.

#### 13\. What is an Object?

**Answer:** An object is a real-world entity and an instance of a class. When a class is defined, no memory is allocated, but when an object is created (instantiated), memory is allocated in the heap. An object has a state (defined by its variables) and behavior (defined by its methods).

#### 14\. What is the difference between a class and an object?

**Answer:**
| Feature | Class | Object |
| :--- | :--- | :--- |
| **Entity Type** | Logical | Physical |
| **Memory** | No memory allocated | Allocated in the heap |
| **Existence** | Exists at compile-time | Created at runtime |
| **Analogy**| Blueprint of a house | An actual house built from the blueprint|

#### 15\. How do you create an object in Java?

**Answer:** You create an object using the `new` keyword followed by a call to the class's constructor.

**Code Example for Class and Object:**

```java
// This is the Class (the blueprint)
class Car {
    // State (instance variables)
    String color;
    String model;

    // Behavior (method)
    void drive() {
        System.out.println("The " + color + " " + model + " is driving.");
    }
}

public class Main {
    public static void main(String[] args) {
        // This is the Object (an instance of the Car class)
        Car myCar = new Car(); // Object creation
        
        // Assigning state to the object
        myCar.color = "Red";
        myCar.model = "Tesla Model S";

        // Calling the object's behavior
        myCar.drive();
    }
}
```

-----

#### 16\. What is a method?

**Answer:** A method is a block of code within a class that is associated with an object or the class itself. It defines the behavior of an object and performs a specific task. A method is executed when it is called.

#### 17\. What are instance variables?

**Answer:** Instance variables are non-static variables declared inside a class but outside any method, constructor, or block. A separate copy of each instance variable exists for every object created from that class.

#### 18\. What are local variables?

**Answer:** Local variables are declared inside a method, constructor, or a block. Their scope is confined to that block, and they must be initialized before use. They are created when the block is entered and destroyed upon exit.

**Code Example for Method, Instance, and Local Variables:**

```java
class Dog {
    // Instance variable - each Dog object has its own 'age'.
    int age;

    public void celebrateBirthday() {
        // Local variable - 'message' only exists within this method.
        String message = "Happy Birthday!";
        System.out.println(message);
        
        age = age + 1; // Accessing the instance variable
    }
}
```

-----

### \#\# Constructors 🏗️

#### 19\. What is a constructor?

**Answer:** A constructor is a special type of method that is automatically called when an object of a class is created. Its primary purpose is to initialize the newly created object's instance variables.

#### 20\. What is the primary rule for a constructor's name?

**Answer:** A constructor must have the exact same name as the class it belongs to.

#### 21\. Does a constructor have a return type?

**Answer:** No, a constructor does not have a return type, not even `void`. If you provide a return type, the compiler will treat it as a regular method.

#### 22\. What is a default constructor?

**Answer:** A default constructor is a no-argument constructor that the Java compiler automatically provides if no other constructor is explicitly defined in the class. It initializes instance variables to their default values (e.g., `0` for numbers, `null` for objects).

#### 23\. Can a constructor be overloaded?

**Answer:** Yes, like methods, constructors can be overloaded. This means a class can have multiple constructors, as long as each has a different parameter list (different number or types of parameters).

#### 24\. What is a parameterized constructor?

**Answer:** A parameterized constructor is a constructor that accepts one or more parameters. It's used to provide specific initial values to an object's instance variables at the time of creation.

**Code Example for Constructors:**

```java
class Person {
    String name;
    int age;

    // 1. No-argument constructor
    Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    // 2. Parameterized constructor (Constructor Overloading)
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person(); // Calls the no-argument constructor
        p1.display();

        Person p2 = new Person("Alice", 30); // Calls the parameterized constructor
        p2.display();
    }
}
```

-----

#### 25\. Can a constructor be `private`?

**Answer:** Yes. A `private` constructor prevents the class from being instantiated from outside the class. It is commonly used in the **Singleton design pattern**, where only one instance of a class is allowed.

**Code Example for Private Constructor (Singleton):**

```java
class Logger {
    // The single instance of the class
    private static final Logger instance = new Logger();

    // Private constructor prevents instantiation from other classes
    private Logger() {}

    // Public method to provide access to the single instance
    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        // Logger log = new Logger(); // Compile Error! Constructor is private.
        
        Logger logger1 = Logger.getInstance();
        logger1.log("First message.");

        Logger logger2 = Logger.getInstance();
        logger2.log("Second message.");

        // Both logger1 and logger2 refer to the exact same object.
        System.out.println(logger1 == logger2); // true
    }
}
```

-----

#### 26\. What is the purpose of `this()`?

**Answer:** The `this()` call is used to invoke another constructor from within the same class. This is known as **constructor chaining**. It must be the very first statement in the constructor.

**Code Example for `this()`:**

```java
class Box {
    int length, width, height;

    // Constructor with one parameter
    Box(int side) {
        // Calls the three-parameter constructor from this one
        this(side, side, side);
    }

    // Constructor with three parameters
    Box(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    void displayVolume() {
        System.out.println("Volume: " + (length * width * height));
    }
}

public class Main {
    public static void main(String[] args) {
        Box cube = new Box(10); // Creates a cube using the first constructor
        cube.displayVolume();
    }
}
```

-----

### \#\# Important Keywords (`this`, `super`, `static`, `final`) 🔑

#### 27\. What is the `this` keyword?

**Answer:** `this` is a reference variable that refers to the current object instance. It is used to:

1.  Disambiguate between instance variables and local parameters when they have the same name.
2.  Invoke a constructor of the current class using `this()`.
3.  Be passed as an argument in a method call to refer to the current object.

**Code Example for `this` keyword:**

```java
class Student {
    String name;

    Student(String name) {
        // 'this.name' refers to the instance variable.
        // 'name' refers to the local parameter.
        this.name = name;
    }
    
    void print() {
        // Here, 'this' refers to the current Student object.
        System.out.println(this.name);
    }
}
```

-----

#### 28\. What is the `super` keyword?

**Answer:** `super` is a reference variable used to refer to the immediate parent class object. It is used to:

1.  Access methods of the parent class that have been overridden in the child class.
2.  Invoke the parent class constructor using `super()`.

**Code Example for `super` keyword:**

```java
class Parent {
    String message = "Hello from Parent";

    Parent() {
        System.out.println("Parent constructor called.");
    }
    
    void display() {
        System.out.println("Parent display method.");
    }
}

class Child extends Parent {
    String message = "Hello from Child";

    Child() {
        super(); // Calls the Parent constructor. This is often implicit.
        System.out.println("Child constructor called.");
    }
    
    @Override
    void display() {
        System.out.println("Child display method.");
        // Use 'super' to access the parent's overridden method
        super.display();
        // Use 'super' to access the parent's hidden variable
        System.out.println(super.message);
    }
}

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.display();
    }
}
```

-----

#### 29\. What is a `static` variable?

**Answer:** A `static` variable, also known as a class variable, belongs to the class itself, not to any individual object instance. There is only one copy of a static variable, shared among all instances of the class.

#### 30\. What is a `static` method?

**Answer:** A `static` method belongs to the class rather than an object. It can be called directly on the class name without creating an instance.

#### 31\. Can a `static` method use non-static members?

**Answer:** No. A `static` method cannot directly access non-static (instance) variables or methods because it is not associated with any specific object instance. It can only access other `static` members.

#### 32\. What is a `static` block?

**Answer:** A `static` block is a block of code prefixed with the `static` keyword. It is executed only once, when the class is first loaded into memory by the JVM, typically used for initializing static variables.

**Code Example for `static` members:**

```java
class Counter {
    // Static variable - shared by all objects of Counter
    static int count = 0;
    
    // Instance variable - each object gets its own copy
    int instanceId;

    Counter() {
        count++; // Increment the shared static variable
        instanceId = count;
        System.out.println("Object created with ID: " + instanceId);
    }

    // Static method
    public static void showCount() {
        // Cannot access instanceId here: System.out.println(instanceId); // Error!
        System.out.println("Total objects created: " + count);
    }
    
    // Static block
    static {
        System.out.println("Counter class is being loaded...");
        count = 0; // Initialize static variable
    }
}

public class Main {
    public static void main(String[] args) {
        // Calling static method without creating an object
        Counter.showCount();

        Counter c1 = new Counter();
        Counter c2 = new Counter();
        
        // Calling static method using class name is preferred
        Counter.showCount();
    }
}
```

-----

#### 33\. What is a `final` variable?

**Answer:** A `final` variable is a constant. Its value cannot be changed once it has been initialized. If it's a reference variable, the reference cannot be changed to point to another object, but the internal state of the referenced object can be changed.

#### 34\. What is a `final` method?

**Answer:** A `final` method cannot be overridden by any subclass. This is used to ensure that a method's implementation remains unchanged in the inheritance hierarchy.

#### 35\. What is a `final` class?

**Answer:** A `final` class cannot be extended or inherited. This is done for security or to ensure the immutability of a class, like the `String` class.

**Code Example for `final` keyword:**

```java
// A final class cannot be extended
final class Immutable {
    // A final variable (a constant)
    private final String message = "This cannot be changed.";

    // A final method cannot be overridden
    public final void showMessage() {
        System.out.println(message);
    }
}

// class SubImmutable extends Immutable {} // Compile Error! Cannot inherit from final class.

public class Main {
    public static void main(String[] args) {
        final int MAX_ATTEMPTS = 3;
        // MAX_ATTEMPTS = 4; // Compile Error! Cannot assign a value to a final variable.
        System.out.println("Max attempts: " + MAX_ATTEMPTS);
    }
}
```

-----

#### 36\. Is the `String` class `final`?

**Answer:** Yes, the `java.lang.String` class is declared as `final` in Java. This means you cannot extend it, which is crucial for its immutability and reliability in the String pool.

#### 37\. Difference between `final`, `finally`, and `finalize`?

**Answer:**

  * **`final`**: A keyword used to apply restrictions. It can be used with variables (to make them constant), methods (to prevent overriding), and classes (to prevent inheritance).
  * **`finally`**: An optional block in a `try-catch` statement. The code inside the `finally` block is always executed, regardless of whether an exception is thrown or not. It's typically used for cleanup code like closing files or database connections.
  * **`finalize()`**: A method from the `Object` class. It was intended to be called by the Garbage Collector just before an object is destroyed. However, its use is strongly discouraged and it has been **deprecated** since Java 9 because its execution is not guaranteed.

**Code Example for `finally`:**

```java
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Inside try block.");
            int result = 10 / 0; // Throws an exception
        } catch (ArithmeticException e) {
            System.out.println("Caught an exception.");
        } finally {
            System.out.println("This finally block is always executed.");
        }
    }
}
```

-----

### \#\# Polymorphism: Overloading vs. Overriding 🎭

#### 38\. What is Method Overloading?

**Answer:** Method Overloading is a feature that allows a class to have more than one method with the same name, as long as their parameter lists are different (either in the number of parameters or the type of parameters). It is an example of compile-time polymorphism.

#### 39\. Is method overloading resolved at compile-time or runtime?

**Answer:** It is resolved at **compile-time**. The compiler determines which version of the method to call based on the method signature (name and arguments) at the time of compilation.

#### 40\. Can we overload a method by only changing its return type?

**Answer:** No. The method signature in Java does not include the return type. To overload a method, you must change the number or type of its parameters.

#### 41\. Can we overload the `main()` method?

**Answer:** Yes, you can overload the `main()` method. However, the JVM will only recognize and call the `main` method with the specific signature `public static void main(String[] args)`.

**Code Example for Method Overloading:**

```java
class Calculator {
    // Method with two int parameters
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with three int parameters
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method with two double parameters
    public double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(10, 20));       // Calls the first method
        System.out.println(calc.add(10, 20, 30));    // Calls the second method
        System.out.println(calc.add(10.5, 20.5));   // Calls the third method
    }
}
```

-----

#### 42\. What is Method Overriding?

**Answer:** Method Overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass. It is a key part of runtime polymorphism.

#### 43\. Is method overriding resolved at compile-time or runtime?

**Answer:** It is resolved at **runtime**. The JVM determines which method implementation to execute based on the type of the object at runtime, not the type of the reference variable.

#### 44\. What are the rules for method overriding?

**Answer:**

1.  The method name must be the same in both the parent and child class.
2.  The parameter list must be the same.
3.  The return type must be the same or a subtype (this is called a covariant return type).
4.  The access level cannot be more restrictive than the overridden method (e.g., you cannot override a `public` method with a `private` one).

#### 45\. Can we override a `private` method?

**Answer:** No. `private` methods are not visible outside the class, so they cannot be accessed or overridden by a subclass.

#### 46\. Can we override a `static` method?

**Answer:** No. A `static` method belongs to the class, not an object. If a subclass defines a static method with the same signature as one in its superclass, it is known as **method hiding**, not overriding.

#### 47\. Can we override a `final` method?

**Answer:** No. A `final` method cannot be overridden.

#### 48\. What is covariant return type?

**Answer:** A covariant return type allows a subclass to change the return type of an overridden method to a subtype of the original return type. This was introduced in Java 5.

**Code Example for Method Overriding:**

```java
class Shape {
    void draw() {
        System.out.println("Drawing a generic shape.");
    }
}

class Circle extends Shape {
    // Overriding the draw method of the parent class
    @Override
    void draw() {
        System.out.println("Drawing a circle.");
    }
}

class Square extends Shape {
    // Overriding the draw method of the parent class
    @Override
    void draw() {
        System.out.println("Drawing a square.");
    }
}

public class Main {
    public static void main(String[] args) {
        // A reference of type Shape pointing to a Circle object
        Shape myShape = new Circle(); 
        // At runtime, the JVM calls the Circle's draw() method.
        myShape.draw();

        // The same reference now points to a Square object
        myShape = new Square();
        // At runtime, the JVM calls the Square's draw() method.
        myShape.draw(); 
    }
}
```

-----

### \#\# Abstraction: `abstract` Class vs. `interface` 👻

#### 49\. What is an abstract method?

**Answer:** An abstract method is a method that is declared with the `abstract` keyword but has no implementation or body. Its implementation must be provided by a concrete subclass.

#### 50\. What is an abstract class?

**Answer:** An abstract class is a class that is declared with the `abstract` keyword. It cannot be instantiated (you cannot create an object of it). It can contain both abstract methods and concrete (implemented) methods.

#### 51\. Can an abstract class have a constructor?

**Answer:** Yes. Although you cannot create an object of an abstract class directly, its constructor is called by the constructors of its subclasses (implicitly via the `super()` call). It's used to initialize the fields defined in the abstract class.

#### 52\. Can an abstract class have non-abstract (concrete) methods?

**Answer:** Yes, this is a key feature. Abstract classes can provide a base implementation that subclasses can inherit and use, while forcing them to implement the abstract methods.

**Code Example for Abstract Class:**

```java
// Abstract class
abstract class Vehicle {
    private String brand;

    // Constructor for the abstract class
    public Vehicle(String brand) {
        this.brand = brand;
    }

    // Abstract method - has no body, must be implemented by subclasses
    public abstract void startEngine();

    // Concrete method - has a body and is inherited
    public void honk() {
        System.out.println("Honk! Honk!");
    }
    
    public String getBrand() {
        return brand;
    }
}

// Concrete subclass
class Car extends Vehicle {
    public Car(String brand) {
        super(brand); // Call the superclass constructor
    }

    // Providing implementation for the abstract method
    @Override
    public void startEngine() {
        System.out.println(getBrand() + " car engine started.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Vehicle v = new Vehicle("Generic"); // Error! Cannot instantiate abstract class.
        
        Car myCar = new Car("Toyota");
        myCar.startEngine(); // Calls the overridden method
        myCar.honk();        // Calls the inherited concrete method
    }
}
```

-----

#### 53\. What is an interface?

**Answer:** An interface in Java is a reference type, similar to a class, that acts as a contract for classes. It can contain only constants, method signatures, `default` methods, `static` methods, and nested types. It cannot contain instance fields or constructors. A class that implements an interface must provide an implementation for all its abstract methods.

#### 54\. Can we create an instance of an interface?

**Answer:** No, you cannot instantiate an interface directly. However, you can create a reference variable of an interface type that points to an object of a class that implements that interface.

#### 55\. Which keyword is used by a class to use an interface?

**Answer:** The `implements` keyword.

#### 56\. Can a class implement multiple interfaces?

**Answer:** Yes. This is how Java supports a form of "multiple inheritance." A class can inherit behaviors from multiple, unrelated interfaces.

#### 57\. Can a class extend multiple classes?

**Answer:** No, Java does not support multiple inheritance with classes. A class can only `extend` one superclass. This avoids the "Diamond Problem."

#### 58\. Key difference between an `abstract` class and an `interface`?

**Answer:**

  * **Inheritance:** A class can `extend` only one abstract class but can `implement` multiple interfaces.
  * **Members:** An abstract class can have instance variables, constructors, and both abstract and concrete methods. An interface can only have `public static final` variables and abstract methods (plus `default` and `static` methods since Java 8).
  * **Purpose:** Use an abstract class when you have a common base with some shared code for related classes ("is-a" relationship). Use an interface when you want to define a capability or contract that unrelated classes can implement ("can-do" relationship).

#### 59\. Can an interface have concrete methods?

**Answer:** Yes. Since Java 8, interfaces can have concrete methods using the `default` and `static` keywords.

  * **`default` methods:** Provide a default implementation that implementing classes can use or override.
  * **`static` methods:** Belong to the interface itself and are called on the interface name.

#### 60\. When would you use an abstract class over an interface?

**Answer:** You should use an abstract class when you want to create a base class that provides some default implementation and requires subclasses to provide others. It's suitable for objects that are closely related in a hierarchy.

**Code Example for Interface:**

```java
// Interface defining a contract
interface Drawable {
    void draw(); // This is public and abstract by default
    
    // Default method (since Java 8)
    default void erase() {
        System.out.println("Erasing the drawing.");
    }
}

class Rectangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle.");
    }
}

class Triangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a triangle.");
    }
}

public class Main {
    public static void main(String[] args) {
        Drawable d1 = new Rectangle();
        d1.draw();
        d1.erase(); // Calls the default method from the interface
        
        Drawable d2 = new Triangle();
        d2.draw();
    }
}
```

-----

### \#\# Relationships Between Classes 🤝

#### 61\. What are the three main types of class relationships in OOP?

**Answer:** The primary relationships are:

  * **Association:** A "uses-a" relationship.
  * **Aggregation:** A weak "has-a" relationship.
  * **Composition:** A strong "part-of" relationship.

#### 62\. What is Association?

**Answer:** Association defines a relationship between two independent classes. It represents a "uses-a" connection where objects of one class use services of another. For example, a `Doctor` uses a `Stethoscope`. Both can exist independently.

#### 63\. What is Aggregation?

**Answer:** Aggregation is a specialized form of association. It represents a "has-a" relationship where one class (the whole) contains objects of another class (the part), but the part can exist independently of the whole.

#### 64\. Give an example of Aggregation.

**Answer:** A `Department` "has-a" `Professor`. If the department is closed, the professor can still exist and join another department.

**Code Example for Aggregation:**

```java
class Professor {
    String name;
    Professor(String name) { this.name = name; }
}

class Department {
    String name;
    private Professor head; // Department 'has a' Professor

    Department(String name, Professor head) {
        this.name = name;
        this.head = head;
    }
    
    public void getDepartmentInfo() {
        System.out.println("Department: " + this.name + ", Head: " + head.name);
    }
}

public class Main {
    public static void main(String[] args) {
        // Professor object can exist independently
        Professor prof = new Professor("Dr. Smith");
        Department cs = new Department("Computer Science", prof);
        cs.getDepartmentInfo();
    }
}
```

-----

#### 65\. What is Composition?

**Answer:** Composition is a more restrictive form of aggregation. It represents a strong "part-of" relationship where the part cannot exist without the whole. If the whole object is destroyed, the part objects are destroyed as well.

#### 66\. Give an example of Composition.

**Answer:** A `House` is composed of `Room`s. The rooms are an integral part of the house and cannot exist independently. If the house is demolished, the rooms are gone too.

**Code Example for Composition:**

```java
// The 'part' class. A Room is part of a House.
class Room {
    private int area;
    Room(int area) { this.area = area; }
    public int getArea() { return area; }
}

// The 'whole' class. The House manages the lifecycle of the Room.
class House {
    // The Room object is created and managed inside the House.
    private final Room kitchen;
    private final Room livingRoom;

    House() {
        // The Room objects cannot exist without the House.
        this.kitchen = new Room(150);
        this.livingRoom = new Room(300);
    }

    public int getTotalArea() {
        return kitchen.getArea() + livingRoom.getArea();
    }
}

public class Main {
    public static void main(String[] args) {
        House myHouse = new House();
        // We interact with the House, which manages its Rooms internally.
        System.out.println("Total house area: " + myHouse.getTotalArea());
    }
}
```

-----

### \#\# Design Concepts & The `Object` Class 💡

#### 67\. What is Coupling?

**Answer:** Coupling refers to the degree of direct knowledge that one class has about another.

  * **Tight Coupling:** Classes are highly dependent on each other. A change in one class often requires changes in others. This is undesirable.
  * **Loose Coupling:** Classes are independent. A change in one has little to no impact on others. This is a goal of good software design and is often achieved through interfaces.

#### 68\. What is Cohesion?

**Answer:** Cohesion refers to how well the elements (methods and variables) within a single class are related and focused on a single task.

  * **High Cohesion:** The class has a well-defined responsibility and its members work together to achieve it. This is desirable.
  * **Low Cohesion:** The class performs many unrelated tasks. This should be avoided by breaking the class into smaller, more focused classes.

-----

#### 69\. What is the root class of all classes in Java?

**Answer:** The `java.lang.Object` class is the ultimate superclass of every class in Java, whether declared explicitly or not.

#### 70\. Name some important methods of the `Object` class.

**Answer:**

  * `public boolean equals(Object obj)`: Compares two objects for equality.
  * `public int hashCode()`: Returns a hash code value for the object.
  * `public String toString()`: Returns a string representation of the object.
  * `protected Object clone()`: Creates and returns a copy of this object.
  * `public final Class<?> getClass()`: Returns the runtime class of an object.

#### 71\. What is the purpose of the `toString()` method?

**Answer:** The `toString()` method provides a human-readable string representation of an object. By default, it returns the class name followed by its hash code, but it's highly recommended to override it in your classes to provide meaningful information.

**Code Example for `toString()`:**

```java
class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Overriding the toString() method from the Object class
    @Override
    public String toString() {
        return "Book[title=" + title + ", author=" + author + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        Book myBook = new Book("The Hobbit", "J.R.R. Tolkien");
        System.out.println(myBook); // Implicitly calls myBook.toString()
    }
}
```

-----

#### 72\. What is the contract between `equals()` and `hashCode()`?

**Answer:** The contract is:

1.  If two objects are equal according to the `equals(Object)` method, then calling the `hashCode()` method on each of the two objects must produce the same integer result.
2.  However, if two objects have the same `hashCode()`, they are not necessarily equal.

This contract is crucial for the correct functioning of hash-based collections like `HashMap` and `HashSet`.

**Code Example for `equals()` and `hashCode()`:**

```java
class Customer {
    private int id;
    private String name;
    
    // constructor, getters...
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if same instance
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null and type
        Customer customer = (Customer) obj;
        return id == customer.id; // Equality based on ID
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id); // Hash code based on ID
    }
}

public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer(101, "Alice");
        Customer c2 = new Customer(101, "Alice");
        
        System.out.println("c1 equals c2: " + c1.equals(c2)); // true
        System.out.println("c1 hashCode: " + c1.hashCode());
        System.out.println("c2 hashCode: " + c2.hashCode());
    }
}
```

-----

#### 73\. What is the difference between `==` and `.equals()`?

**Answer:**

  * **`==` operator:** For primitive types, it compares values. For objects, it is a **reference comparison**; it checks if two reference variables point to the exact same object in memory.
  * **`.equals()` method:** This is a method from the `Object` class. By default, it behaves exactly like `==`. However, classes like `String`, `Integer`, etc., override it to perform a **content comparison**, checking if the objects are logically equal. It's a best practice to override `.equals()` in your own classes for meaningful comparison.

**Code Example for `==` vs `.equals()`:**

```java
public class Main {
    public static void main(String[] args) {
        String s1 = new String("HELLO");
        String s2 = new String("HELLO");
        String s3 = "HELLO"; // Stored in string pool
        String s4 = "HELLO"; // Re-uses from string pool

        System.out.println(s1 == s2);      // false (different objects in memory)
        System.out.println(s1.equals(s2)); // true (content is the same)
        
        System.out.println(s3 == s4);      // true (point to the same object in the string pool)
        System.out.println(s3.equals(s4)); // true (content is the same)
    }
}
```

-----

#### 74\. What is an immutable object?

**Answer:** An immutable object is an object whose internal state cannot be modified after it has been created. Any operation that appears to modify an immutable object actually returns a new object with the modified state. `String` is a classic example of an immutable class in Java.

#### 75\. How do you create an immutable class?

**Answer:**

1.  Declare the class as `final` so it can’t be extended.
2.  Make all fields `private` and `final`.
3.  Don't provide any "setter" methods.
4.  Initialize all fields via a constructor.
5.  If a field is a mutable object reference (like a `Date` or `List`), perform a defensive copy in the constructor and getter methods to return a copy rather than the original reference.

**Code Example for Immutable Class:**

```java
// 1. Class is final
public final class ImmutableStudent {
    // 2. Fields are private and final
    private final int id;
    private final String name;

    // 3. Initialize fields in constructor
    public ImmutableStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 4. No setter methods. Only getters.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

-----

#### 76\. What is a Singleton class?

**Answer:** A Singleton is a design pattern that ensures a class has only one instance and provides a global point of access to it. This is achieved by making the constructor `private`, creating a single static instance of the class, and providing a public static method to get that instance.
*(See code example for question 25).*

#### 77\. What is Garbage Collection?

**Answer:** Garbage Collection (GC) is the process by which the Java Virtual Machine (JVM) automatically manages memory. The garbage collector identifies and destroys objects that are no longer in use by the program, freeing up the memory they were occupying.

#### 78\. Can you force garbage collection?

**Answer:** No, you cannot force it. You can only suggest that the JVM run the garbage collector by calling `System.gc()`. However, the JVM is free to ignore this request.

#### 79\. What is an anonymous inner class?

**Answer:** An anonymous inner class is a class without a name that is declared and instantiated at the same time. It is typically used for one-time implementations of an interface or for overriding a method of a class on the fly.

**Code Example for Anonymous Inner Class:**

```java
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        // Here, we are creating an object of an anonymous class
        // that implements the Greeting interface.
        Greeting englishGreeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello!");
            }
        };

        englishGreeting.sayHello();
    }
}
```

-----

#### 80\. What is dynamic binding?

**Answer:** Dynamic binding (or late binding) is the mechanism where the method call is linked to its implementation at runtime instead of compile time. This is how runtime polymorphism (method overriding) is achieved. The actual method executed is determined by the object's type, not the reference variable's type.
*(See code example for question 48).*

-----

### \#\# Miscellaneous Java OOP Questions ❓

#### 81\. Can a Java source file have more than one public class?

**Answer:** No. A single Java source file (`.java`) can have at most one `public` class. If a public class exists, the name of the source file must exactly match the name of the public class.

#### 82\. What is a marker interface?

**Answer:** A marker interface is an interface that has no methods or constants. It is used to provide runtime type information about objects and "marks" a class as having a specific capability. Examples include `java.io.Serializable` and `java.lang.Cloneable`.

**Code Example for Marker Interface:**

```java
// A marker interface
interface Deletable {}

class User implements Deletable {
    // This class is now marked as 'Deletable'
}

class SystemAdmin {
    // This class is not marked
}

public class Main {
    void delete(Object obj) {
        if (obj instanceof Deletable) {
            System.out.println("Object is marked as Deletable. Deleting...");
        } else {
            System.out.println("Object cannot be deleted.");
        }
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        m.delete(new User());
        m.delete(new SystemAdmin());
    }
}
```

-----

#### 83\. What is `super()` used for?

**Answer:** The `super()` call is used inside a subclass constructor to invoke the constructor of its immediate parent class. If you don't explicitly call `super()`, the compiler will automatically insert a call to the superclass's no-argument constructor as the first statement.
*(See code example for question 28).*

#### 84\. What is the first line in any constructor?

**Answer:** The very first statement in any constructor is either a call to another constructor in the same class (using `this()`) or a call to the superclass constructor (using `super()`). If neither is present, the compiler implicitly adds `super()`.

#### 85\. Can an interface extend another interface?

**Answer:** Yes. An interface can extend one or more other interfaces using the `extends` keyword. This allows for creating a hierarchy of interfaces.

**Code Example for Interface Inheritance:**

```java
interface Flyable {
    void fly();
}

// Bird interface extends Flyable
interface Bird extends Flyable {
    void layEggs();
}

class Eagle implements Bird {
    @Override
    public void fly() { System.out.println("Eagle is soaring high."); }
    @Override
    public void layEggs() { System.out.println("Eagle lays eggs in a nest."); }
}
```

-----

#### 86\. What is the `instanceof` operator?

**Answer:** The `instanceof` operator is a binary operator used to test if an object is an instance of a particular class, a subclass, or an interface. It returns either `true` or `false`.

**Code Example for `instanceof`:**

```java
class Animal {}
class Cat extends Animal {}

public class Main {
    public static void main(String[] args) {
        Cat myCat = new Cat();
        System.out.println(myCat instanceof Cat);      // true
        System.out.println(myCat instanceof Animal);  // true
        System.out.println(myCat instanceof Object);  // true
    }
}
```

-----

#### 87\. What is a package in Java?

**Answer:** A package is a namespace that organizes a set of related classes and interfaces. It helps in preventing naming conflicts, controlling access, and making code more maintainable.

#### 88\. What are access modifiers?

**Answer:** Access modifiers are keywords in Java that set the visibility (accessibility) of classes, interfaces, variables, and methods. The four access modifiers are:

  * `public`: Accessible from everywhere.
  * `protected`: Accessible within the same package and by subclasses in other packages.
  * `default` (no keyword): Accessible only within the same package.
  * `private`: Accessible only within the same class.

#### 89\. What is the difference between `protected` and `default` access?

**Answer:** The key difference is subclass access outside the package. A `protected` member can be accessed by a subclass in a different package, whereas a `default` member cannot.

#### 90\. Can you declare a top-level class as `private` or `protected`?

**Answer:** No. A top-level (non-nested) class can only be `public` or `default`. `private` and `protected` modifiers can be used for nested classes.

#### 91\. What happens if you don't provide any constructor?

**Answer:** If you do not define any constructor in a class, the Java compiler will automatically provide a public, no-argument **default constructor** on your behalf.

#### 92\. Can a constructor be inherited?

**Answer:** No, constructors are not inherited by subclasses. However, a subclass constructor must call a superclass constructor using `super()`.

#### 93\. What is method hiding?

**Answer:** Method hiding occurs when a subclass defines a `static` method with the same signature as a `static` method in its superclass. The subclass method hides the superclass one. Unlike overriding, the method call is resolved at compile-time based on the reference type.

**Code Example for Method Hiding:**

```java
class Base {
    public static void show() { System.out.println("Base::show() called"); }
}

class Derived extends Base {
    // This method hides the one in Base.
    public static void show() { System.out.println("Derived::show() called"); }
}

public class Main {
    public static void main(String[] args) {
        Base b = new Derived();
        b.show(); // Calls Base.show() because the reference type is Base
    }
}
```

-----

#### 94\. What is the difference between an object and a reference?

**Answer:** An object is the actual instance of a class that resides in the heap memory. A reference is a variable that holds the memory address of an object. You can have multiple references pointing to the same single object.

#### 95\. What is pass-by-value?

**Answer:** Java is always **pass-by-value**. This means when you pass a variable to a method, a copy of that variable is passed.

  * For **primitive types**, a copy of the value is passed.
  * For **object types**, a copy of the reference (the memory address) is passed. The original reference and the copy both point to the same object.

**Code Example for Pass-by-Value:**

```java
class MyNumber {
    public int value;
    MyNumber(int value) { this.value = value; }
}

public class Main {
    public static void change(MyNumber num) {
        // This changes the 'value' field of the original object
        num.value = 100;
    }

    public static void main(String[] args) {
        MyNumber myNum = new MyNumber(10);
        System.out.println("Before: " + myNum.value);
        change(myNum); // A copy of the reference is passed
        System.out.println("After: " + myNum.value);
    }
}
```

-----

#### 96\. Can the `main` method be `final`?

**Answer:** Yes, the `main` method can be declared as `final`. It will run without any issues. The `final` keyword simply prevents it from being overridden if the class containing it were to be extended.

#### 97\. Can an abstract class be `final`?

**Answer:** No. This would be a contradiction. An `abstract` class is incomplete and must be subclassed to be used, while a `final` class cannot be subclassed. Therefore, a class cannot be both `abstract` and `final`.

#### 98\. What is the Diamond Problem?

**Answer:** The Diamond Problem refers to an ambiguity that can arise in multiple inheritance when a class inherits from two superclasses that both share a common ancestor. If a method in the common ancestor is overridden by both superclasses, the final subclass doesn't know which version of the method to inherit. Java avoids this by not allowing multiple inheritance of classes (it allows multiple inheritance of interfaces, which is resolved with `default` methods).

#### 99\. What is a functional interface?

**Answer:** A functional interface is an interface that contains exactly one abstract method. They are also known as Single Abstract Method (SAM) interfaces. They are the foundation of lambda expressions in Java 8. The `@FunctionalInterface` annotation can be used to ensure an interface meets this criterion at compile time.

**Code Example for Functional Interface and Lambda:**

```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        // Using a lambda expression to implement the functional interface
        Calculator addition = (a, b) -> a + b;
        System.out.println("Sum: " + addition.operate(10, 5));
    }
}
```

-----

#### 100\. Why are strings immutable in Java?

**Answer:** Strings are immutable in Java for several key reasons:

  * **String Pool:** The JVM saves memory by maintaining a pool of strings. If strings were mutable, changing one string reference could affect all other references pointing to it, leading to unpredictable behavior.
  * **Security:** Immutable strings are safe to use as parameters in network connections, database URLs, and file paths, as their value cannot be changed after being passed.
  * **Thread Safety:** Because they cannot be changed, strings are inherently thread-safe and can be shared among multiple threads without the need for synchronization.
  * **Caching HashCode:** The hash code of a string is calculated and cached the first time it's used. Since the string is immutable, its hash code never changes, making it fast and reliable for use in collections like `HashMap`.
