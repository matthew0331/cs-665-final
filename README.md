| CS-665       | Software Design & Patterns |
|--------------|----------------------------|
| Name         | Lingpeng Li                |
| Date         | 12/08/2024                 |
| Course       | Fall 2024                  |
| Assignment # | Final Project              |


---
## GitHub Repository Link:
---
https://github.com/matthew0331/cs-665-final
---
---
# Project Description

## What is the Project?
This project is a Particle Visualization System, designed to demonstrate the application of software design principles in a manageable, creative, and educational context. The system allows users to customize particle effects such as color, shape, speed, and count, and then export these visualizations as images (PNG), GIFs, or videos (MP4).

The project leverages design patterns like Callback/Listener and Delegation, showcasing the ability to create modular, maintainable, and scalable code. While simple in its scope, this project provides a meaningful exercise in combining functional programming with user interaction and file management.

## Why Did I Choose This Project?
I chose this project because of my interest in both software design and visual applications. This project offered a perfect opportunity to combine these interests while working on something engaging yet achievable within the constraints of a semester.

Through this project, I wanted to:

- Develop a hands-on understanding of real-time interactivity in applications.
- Explore the integration of libraries like Processing for graphical rendering.
- Practice using software design patterns in a real-world-inspired scenario.

It also aligns with my desire to explore visual programming as a potential direction for future projects or career development.

## How Does It Relate to Real Life?
While this project is not a fully developed application with wide-scale applicability, it represents a simplified model of tools used in fields like digital media, UI/UX design, and education:

- **Creative Media:** Similar techniques are used for generating simple animations or interactive effects for presentations.
- **Learning Tools:** Basic particle systems are often utilized to illustrate physical or mathematical principles visually.
- **Personal Exploration:** This kind of project offers an accessible way for learners to explore coding principles while working on something visually rewarding.

## How Can It Be Applied?
The primary application of this project is educational and exploratory. It is an example of how theoretical concepts from the course—such as design patterns, modularity, and testing—can be applied to a creative, tangible outcome. It demonstrates:

- **Software Design Skills:** The use of Callback/Listener and Delegation patterns showcases my ability to apply theoretical concepts to practice.
- **Problem-Solving Skills:** The project required integrating multiple components like user interfaces, file management, and graphics rendering.
- **Creativity:** Working on particle effects provided a chance to develop something engaging and visually appealing.

This project also serves as a foundation for future exploration. For instance:

- Adding more complex visual features like trails or dynamic behaviors.
- Exploring interactive user controls beyond static settings adjustments.
- Enhancing export options to accommodate different resolutions or formats.

## Conclusion
As a graduate-level course project, this work allowed me to practice essential skills in software development while exploring an area of personal interest. It is not a professional-grade tool but a well-structured example of how design principles and coding techniques can create something functional and engaging. This project represents both my current skills and the direction I aim to grow in the field of software development.

---
# Implementation Description
## Flexibility
The project employs the Callback/Listener Pattern, allowing real-time updates to the application (ParticleApp) when settings are modified via the UI (SettingsUI). This design ensures that:
- Users can dynamically adjust visualization parameters without restarting the application.
- The system can easily accommodate new settings or features with minimal code changes.

## Simplicity
Responsibilities are divided into specific classes:
- **SettingsUI** handles user input.
- **ConfigLoader** manages configuration persistence.
- **ExportManager** handles file exports.

This separation ensures that each class has a clear purpose, simplifying understanding and debugging.

## Maintainability
The Delegation Pattern is applied, delegating configuration management to ConfigLoader. This makes it:
- Easy to modify configuration handling without affecting other components.
- Straightforward to add new configuration sources (e.g., databases or APIs).

## Reusability
- **ExportManager** encapsulates export logic, enabling reuse for other rendering projects.
- The modular nature of the system supports extending functionality, such as adding new export formats or visualization types.

## Design Patterns Utilized

### 1 Callback/Listener Pattern
The Callback/Listener Pattern is implemented to enable real-time updates between the settings user interface (`SettingsUI`) and the application logic (`ParticleApp`). The `SettingsUI` class uses a callback (`Runnable`) to notify `ParticleApp` whenever settings are updated. This decouples the UI logic from the core application, improving flexibility and maintainability.

**Advantages:**
- Decouples components, enabling independent updates to UI or application logic.
- Simplifies extending or modifying settings functionality.

**Drawbacks:**
- Requires careful implementation to avoid callback failures or inconsistencies.

### 2 Delegation Pattern
The Delegation Pattern is used in `ConfigLoader` to separate the responsibilities of configuration management from the core logic. `ParticleApp` delegates the task of loading and applying configurations to `ConfigLoader`.

**Advantages:**
- Enhances code clarity by isolating configuration logic.
- Supports future extensions (e.g., loading configurations from a database).

**Drawbacks:**
- Introduces additional abstraction, requiring thorough testing of delegated components.

---

## 1. Prerequisites and Environment Setup

### System Requirements
- **Java Development Kit (JDK)**: Version 17 or later.
- **Maven**: Version 3.6.0 or later for managing project dependencies.
- **Processing Core Library**: Processing core version 4.3 for graphical rendering.
- **JCodec Library**: Version 0.2.5 for video encoding functionality.
- **GifEncoder Library**: Version 0.10.1 for GIF export support.

### Installation Steps

1**Install Dependencies**: Install dependencies using Maven.
   ```sh
   mvn clean install
   ```
2. **Download Processing Core Library**:
    - Download the Processing core library from the official website
    - Extract the downloaded file and locate the `core.jar` file.
    - Rename the `core.jar` file to `processing-core-4.3.jar` and place it in the `lib` directory of the project.
3. **Add the Required JAR Files**:
    - Place `processing-core-4.3.jar`, `jcodec-0.2.5.jar`, and `gifencoder-0.10.1.jar` in the `lib` directory.
    - Install the JAR files using Maven commands as follows:
      ```sh
      mvn install:install-file -Dfile=lib/processing-core-4.3.jar -DgroupId=org.processing -DartifactId=core -Dversion=4.3 -Dpackaging=jar
      mvn install:install-file -Dfile=lib/jcodec-0.2.5.jar -DgroupId=org.jcodec -DartifactId=jcodec -Dversion=0.2.5 -Dpackaging=jar
      mvn install:install-file -Dfile=lib/gifencoder-0.10.1.jar -DgroupId=com.squareup -DartifactId=gifencoder -Dversion=0.10.1 -Dpackaging=jar
      ```

## 2. Project Source Code Overview
## Project Directory Structure

Below is the directory structure for the project:

```plaintext
CS665_Final_Project_Lingpeng_Li_U13304702/
├── .idea/                   # IntelliJ IDEA project configuration
├── exports/                 # Contains exported frames, GIFs, and videos
├── lib/                     # Contains external libraries (JAR files)
│   ├── gifencoder-0.10.1.jar
│   ├── jcodec-0.2.5.jar
│   └── processing-core-4.3.jar
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/particlevisualization/
│   │   │       ├── ConfigLoader.java # Loads configuration settings
│   │   │       ├── ExportManager.java # Manages frame, GIF, and video exports
│   │   │       ├── Main.java # Entry point of the application
│   │   │       ├── ParticleApp.java # Core particle visualization logic
│   │   │       ├── SettingsListener.java # Handles settings UI interactions
│   │   │       └── SettingsUI.java # Provides settings UI for visualization parameters
│   │   └── resources/           # Reserved for non-Java resources, e.g., configuration files
├── test/                      # Contains 5 JUnit test classes
├── target/                   # Contains compiled classes and JAR files
├── pom.xml                     # Maven configuration file
└── README.md                   # Project documentation
```


## 3. Core Components and Detailed Workflow

### Main.java
- **Core Component**: The main entry point for the application.
- **Workflow**: Launches the `ParticleApp` class to initiate the particle visualization, setting up the graphical context and starting the main application loop.

### ParticleApp.java
- **Core Component**: Handles the core particle visualization logic.
- **Workflow**:
    - **Rendering Logic**: Utilizes the `Processing` library to draw particles based on settings such as count, shape, color, and speed.
    - **User Interaction**: Captures keyboard events to start/stop frame capture, video export, and GIF export. The shapes include circles, squares, and triangles, which are rendered based on user-defined settings.
    - **Settings Integration**: Reads configuration values from the `ConfigLoader` to determine particle attributes, providing real-time changes when updated via the settings UI.

### ExportManager.java
- **Core Component**: Responsible for exporting visualizations.
- **Workflow**:
    - **Frame Capture**: Captures individual frames of the particle system and saves them as PNG images.
    - **GIF Export**: Uses the `GifEncoder` library to create a GIF from multiple captured frames. Handles image ordering, delay, and final encoding.
    - **Video Export**: Uses the `JCodec` library for exporting particle animations as MP4 videos, ensuring the color and settings match the visualization.

### ConfigLoader.java
- **Core Component**: Loads configuration settings from a properties file.
- **Workflow**:
    - Reads settings like particle count, shape, speed, and color from a properties file (`config.properties`).
    - Provides methods to get configuration values which are used in `ParticleApp` to dynamically adjust the visualization based on user preferences.

### SettingsUI.java and SettingsListener.java
- **Core Component**: Provides a graphical user interface for setting visualization parameters.
- **Workflow**:
    - **Settings UI**: Displays input fields, dropdowns, and sliders to adjust particle visualization parameters.
    - **Settings Listener**: Captures user input from the UI and updates the `ConfigLoader`. This allows dynamic adjustments of particle attributes such as count, speed, shape, and color spectrum without restarting the application.

## 4. How to Use and Control the Project

### Running the Application
- Run the project using Maven:
 you can use your IDE to run `Main.java` directly.

### User Controls
- **`c` Key**: Start/Stop capturing frames as individual PNG files.
- **`g` Key**: Export all captured frames as a GIF animation.
- **`v` Key**: Start/Stop recording video as an MP4 file.
- **`s` Key**: Launches the settings UI for adjusting particle visualization parameters.

### Settings UI
- **Adjustable Parameters**:
    - **Particle Count**: Set the number of particles (e.g., range from 10 to 500).
    - **Shape**: Choose between different shapes (circle, square, triangle).
    - **Speed**: Control the movement speed of particles (e.g., range from 1 to 20).
    - **Color Spectrum**: Define the colors for particle visualization (e.g., select from RGB ranges from 1 to 255).
- **Dynamic Updates**: The settings UI allows dynamic changes that apply immediately to the particle visualization without needing to restart the application.

### Note on Visualization Updates
- To see changes made in the settings UI without restarting the program, ensure you click "Apply" in the settings window. This triggers an update in real-time, applying new particle shapes, colors, and other settings directly to the visualization.

# Maven Commands

We'll use Apache Maven to compile and run this project. You'll need to install Apache Maven (https://maven.apache.org/) on your system.

Apache Maven is a build automation tool and a project management tool for Java-based projects. Maven provides a standardized way to build, package, and deploy Java applications.

Maven uses a Project Object Model (POM) file to manage the build process and its dependencies. The POM file contains information about the project, such as its dependencies, the build configuration, and the plugins used for building and packaging the project.

Maven provides a centralized repository for storing and accessing dependencies, which makes it easier to manage the dependencies of a project. It also provides a standardized way to build and deploy projects, which helps to ensure that builds are consistent and repeatable.

Maven also integrates with other development tools, such as IDEs and continuous integration systems, making it easier to use as part of a development workflow.

Maven provides a large number of plugins for various tasks, such as compiling code, running tests, generating reports, and creating JAR files. This makes it a versatile tool that can be used for many different types of Java projects.

## Compile
Type on the command line:

```bash
mvn clean compile 
```

**Run the Project:**
After compiling, you can run the project by executing the `Main.java` class:

## JUnit Tests
JUnit is a popular testing framework for Java. JUnit tests are automated tests that are written to verify that the behavior of a piece of code is as expected.

In JUnit, tests are written as methods within a test class. Each test method tests a specific aspect of the code and is annotated with the @Test annotation. JUnit provides a range of assertions that can be used to verify the behavior of the code being tested.

JUnit tests are executed automatically and the results of the tests are reported. This allows developers to quickly and easily check if their code is working as expected, and make any necessary changes to fix any issues that are found.

The use of JUnit tests is an important part of Test-Driven Development (TDD), where tests are written before the code they are testing is written. This helps to ensure that the code is written in a way that is easily testable and that all required functionality is covered by tests.

JUnit tests can be run as part of a continuous integration pipeline, where tests are automatically run every time changes are made to the code. This helps to catch any issues as soon as they are introduced, reducing the need for manual testing and making it easier to ensure that the code is always in a releasable state.

The project includes the following JUnit test cases:

1. **ConfigLoaderTest**  
   **Purpose:**  
   Validates the ability of ConfigLoader to load configuration values from a file.  
   Tests error handling for scenarios such as missing files or invalid configuration keys.  
   **Key Tests:**
    - Ensures correct values are loaded for valid configuration keys.
    - Verifies the behavior when a configuration file is missing.

2. **SettingsUITest**  
   **Purpose:**  
   Confirms that the callback mechanism in SettingsUI properly triggers when settings are saved.  
   **Key Tests:**
    - Ensures the onSettingsUpdated callback executes successfully after the user saves settings.
    - Simulates UI interactions to test real-time updates.

3. **ExportManagerTest**  
   **Purpose:**  
   Verifies the functionality of frame capture and GIF export operations in ExportManager.  
   Tests error handling for edge cases such as exporting from an empty directory.  
   **Key Tests:**
    - Ensures frame capture saves images correctly.
    - Confirms GIF export handles missing or empty frames gracefully.

4. **ParticleAppTest**  
   **Purpose:**  
   Validates the behavior of ParticleApp, including configuration loading and key-based actions.  
   **Key Tests:**
    - Ensures configuration values are loaded correctly from ConfigLoader.
    - Simulates key presses (c, v) and verifies changes in application states (e.g., capturing frames, recording video).

5. **ConfigLoaderEdgeCaseTest**  
   **Purpose:**  
   Tests ConfigLoader's robustness against edge cases.  
   **Key Tests:**
    - Ensures default values are returned for missing configuration keys.
    - Handles invalid integer values in configuration files without crashing.

To run, use the following command:
```bash
mvn clean test
```
---

## Spotbugs

SpotBugs is a static code analysis tool for Java that detects potential bugs in your code. It is an open-source tool that can be used as a standalone application or integrated into development tools such as Eclipse, IntelliJ, and Gradle.

SpotBugs performs an analysis of the bytecode generated from your Java source code and reports on any potential problems or issues that it finds. This includes things like null pointer exceptions, resource leaks, misused collections, and other common bugs.

The tool uses data flow analysis to examine the behavior of the code and detect issues that might not be immediately obvious from just reading the source code. SpotBugs is able to identify a wide range of issues and can be customized to meet the needs of your specific project.

Using SpotBugs can help to improve the quality and reliability of your code by catching potential bugs early in the development process. This can save time and effort in the long run by reducing the need for debugging and fixing issues later in the development cycle. SpotBugs can also help to ensure that your code is secure by identifying potential security vulnerabilities.

Use the following command:

```bash
mvn spotbugs:gui 
```

For more info see
https://spotbugs.readthedocs.io/en/latest/maven.html

SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.



## Checkstyle

Checkstyle is a development tool for checking Java source code against a set of coding standards. It is an open-source tool that can be integrated into various integrated development environments (IDEs), such as Eclipse and IntelliJ, as well as build tools like Maven and Gradle.

Checkstyle performs static code analysis, which means it examines the source code without executing it, and reports on any issues or violations of the coding standards defined in its configuration. This includes issues like code style, code indentation, naming conventions, code structure, and many others.

By using Checkstyle, developers can ensure that their code adheres to a consistent style and follows best practices, making it easier for other developers to read and maintain. It can also help to identify potential issues before the code is actually run, reducing the risk of runtime errors or unexpected behavior.

Checkstyle is highly configurable and can be customized to fit the needs of your team or organization. It supports a wide range of coding standards and can be integrated with other tools, such as code coverage and automated testing tools, to create a comprehensive and automated software development process.

The following command will generate a report in HTML format that you can open in a web browser.

```bash
mvn checkstyle:checkstyle
```

The HTML page will be found at the following location:
`target/site/checkstyle.html`

---
# Explaination of why Use JDK 17+ for This Project?

This project uses JDK 17+, instead of JDK 1.8 as specified in the course requirements, due to the significant benefits offered by modern Java versions. These benefits directly support the functionality and implementation of the project. Below are the key reasons for this decision:

## 1. Text Blocks for Readability
Text blocks (introduced in JDK 15) simplify handling multi-line strings, which are used in this project for:
- **Debug Messages**: Logging detailed frame-by-frame progress in `ExportManager`.
- **Configuration Templates**: Improved readability when dealing with settings or error messages in `ConfigLoader`.
---

## 2. Pattern Matching for Type Checks
Pattern matching for `instanceof` (introduced in JDK 16) simplifies type checks in `ConfigLoader`, especially when handling configuration values with dynamic types.

---

## 3. Sealed Classes for Improved Type Safety
Sealed classes (introduced in JDK 17) provide enhanced control over inheritance hierarchies. While not currently used in the project, this feature is valuable for future enhancements, ensuring strict control over subclassing and improving type safety.

---

## 4. Enhanced Switch Statements
Switch expressions (introduced in JDK 14) simplify managing particle shapes in `ParticleApp`, making the code more concise and easier to extend.

---

## 5. Improved Garbage Collection and Performance
JDK 17+ includes advanced garbage collection (e.g., ZGC) and performance optimizations that improve the efficiency of memory-intensive tasks such as:
- Real-time rendering of particles.
- Exporting frames, GIFs, and videos.

---

## Conclusion
The decision to use JDK 17+ is directly tied to the functionality and implementation of this project. Features like text blocks, pattern matching, and enhanced switch expressions simplify the code while ensuring better performance and maintainability. This choice aligns with modern development practices and ensures the project remains relevant and extendable in the future.

---
