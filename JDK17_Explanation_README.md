
# Why Use JDK 17+ for This Project?

This project uses JDK 17+, instead of JDK 1.8 as specified in the course requirements, due to the significant benefits offered by modern Java versions. These benefits directly support the functionality and implementation of the project. Below are the key reasons for this decision:

## 1. Text Blocks for Readability
Text blocks (introduced in JDK 15) simplify handling multi-line strings, which are used in this project for:
- **Debug Messages**: Logging detailed frame-by-frame progress in `ExportManager`.
- **Configuration Templates**: Improved readability when dealing with settings or error messages in `ConfigLoader`.

Example:
```java
String logMessage = """
    Exporting frame %d of %d
    Output path: %s
""".formatted(currentFrame, totalFrames, outputPath);
```
This approach enhances maintainability and reduces potential errors.

---

## 2. Pattern Matching for Type Checks
Pattern matching for `instanceof` (introduced in JDK 16) simplifies type checks in `ConfigLoader`, especially when handling configuration values with dynamic types.

Example:
```java
Object value = properties.get("particleSpeed");
if (value instanceof Integer speed) {
    System.out.println("Speed: " + speed);
} else {
    System.out.println("Default Speed: " + defaultSpeed);
}
```
This feature eliminates boilerplate code, making type validation cleaner and more efficient.

---

## 3. Sealed Classes for Improved Type Safety
Sealed classes (introduced in JDK 17) provide enhanced control over inheritance hierarchies. While not currently used in the project, this feature is valuable for future enhancements, ensuring strict control over subclassing and improving type safety.

---

## 4. Enhanced Switch Statements
Switch expressions (introduced in JDK 14) simplify managing particle shapes in `ParticleApp`, making the code more concise and easier to extend.

Example:
```java
switch (particleShape) {
    case "circle" -> ellipse(x, y, particleSpeed, particleSpeed);
    case "square" -> rect(x, y, particleSpeed, particleSpeed);
    case "triangle" -> triangle(x, y, x + particleSpeed, y, x + particleSpeed / 2, y - particleSpeed);
    default -> ellipse(x, y, particleSpeed, particleSpeed);
}
```

---

## 5. Improved Garbage Collection and Performance
JDK 17+ includes advanced garbage collection (e.g., ZGC) and performance optimizations that improve the efficiency of memory-intensive tasks such as:
- Real-time rendering of particles.
- Exporting frames, GIFs, and videos.

---

## Conclusion
The decision to use JDK 17+ is directly tied to the functionality and implementation of this project. Features like text blocks, pattern matching, and enhanced switch expressions simplify the code while ensuring better performance and maintainability. This choice aligns with modern development practices and ensures the project remains relevant and extendable in the future.
