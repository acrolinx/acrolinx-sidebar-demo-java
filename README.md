# Acrolinx Java Sidebar Demo

This is a showcase for integrating the [Acrolinx](https://www.acrolinx.com/) Sidebar
into different Java UI framework-based applications (JFX, Swing, and SWT).

See: [Build With Acrolinx](https://support.acrolinx.com/hc/en-us/categories/10209837818770-Build-With-Acrolinx)

## The Acrolinx Sidebar

The Acrolinx Sidebar is designed to show up beside the window where you edit your content.
You use it for checking, reviewing, and correcting your content.
To get an impression what the Sidebar looks like in existing integrations, have a look at
[Sidebar Quick Start](https://support.acrolinx.com/hc/en-us/articles/10252588984594-Sidebar-Quick-Start).

## Prerequisites

Please contact [Acrolinx SDK support](https://github.com/acrolinx/acrolinx-coding-guidance/blob/main/topics/sdk-support.md)
for consulting and getting your integration certified.

This sample works with a test license on an internal Acrolinx URL.
This license is only meant for demonstration and developing purposes.
Once you finished your integration, you'll have to get a license for your integration from Acrolinx.

Before you start developing your own integration, you might benefit from looking into:

+ [Build With Acrolinx](https://support.acrolinx.com/hc/en-us/categories/10209837818770-Build-With-Acrolinx),
+ the [Guidance for the Development of Acrolinx Integrations](https://github.com/acrolinx/acrolinx-coding-guidance),
+ the [Acrolinx SDKs](https://github.com/acrolinx?q=sdk), and
+ the [Acrolinx Demo Projects](https://github.com/acrolinx?q=demo).

## Getting Started

### Build the Project

1. You need Java 11 to build this project.
2. This project uses [Gradle](https://gradle.org/).
To build this project with the [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:using_wrapper), execute the following command:

```bash
./gradlew build
```

on an UNIX system, or

```batch
gradlew build
```

on a Windows computer.

### Run the Samples

Build the project first, then run:

#### JavaFX

```bash
./gradlew sidebar-demo-jfx:run
```

#### Swing

```bash
./gradlew sidebar-demo-swing:run
```

#### SWT

```bash
./gradlew sidebar-demo-swt:run
```

### Building Integrations Based on Swing or JFX

This project uses the [Gradle plugin for JavaFX](https://plugins.gradle.org/plugin/org.openjfx.javafxplugin).
Please have a look at the [OpenJFX documentation](https://openjfx.io/openjfx-docs/).

### CORS

To be able to connect to Acrolinx, you might have to enable [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) on the Java VM:

```bash
java -Dsun.net.http.allowRestrictedHeaders=true ...
```

Or via code:

```java
System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
```

[Cross-Origin Resource Sharing](https://support.acrolinx.com/hc/en-us/articles/10211162946962-Enable-CORS), must be enabled on the Acrolinx Platform as well.

## References

+ This DEMO is built on the [Sidebar SDK Java](https://github.com/acrolinx/sidebar-sdk-java).
+ [Sidebar SDK Java API Reference](https://acrolinx.github.io/sidebar-sdk-java/).
+ The Sidebar SDKs are based on the [Acrolinx Sidebar Interface](https://acrolinx.github.io/sidebar-interface/).
