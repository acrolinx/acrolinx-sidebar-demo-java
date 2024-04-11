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

Acrolinx offers different other SDKs, and examples for developing integrations.

Before you start developing your own integration, you might benefit from looking into:

+ [Build With Acrolinx](https://support.acrolinx.com/hc/en-us/categories/10209837818770-Build-With-Acrolinx),
+ the [Guidance for the Development of Acrolinx Integrations](https://github.com/acrolinx/acrolinx-coding-guidance),
+ the [Acrolinx SDKs](https://github.com/acrolinx?q=sdk), and
+ the [Acrolinx Demo Projects](https://github.com/acrolinx?q=demo).

## Getting Started

### Build the Project

1. You need to have Java Version >= 11 installed on your system, in order to run the sample with [Gradle](https://gradle.org/).
2. The project comes with Gradle Wrapper. Just go into the projects root folder and run:

```bash
./gradlew build
```

on an UNIX system, or

```batch
gradlew build
```

on a Windows computer.

3. All dependencies needed will be downloaded and the samples are ready to be run.

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

## Using the SDK

1. Just reference the Maven artifact `com.acrolinx.client:sidebar-sdk-java` that is available on [Maven Central](https://central.sonatype.com/artifact/com.acrolinx.client/sidebar-sdk-java).
2. Implement:
    * `AcrolinxIntegrationInterface`, and the
    * `InputAdapterInterface`.
    * The `AcrolinxSidebarInitParameterBuilder` helps you initialize the Acrolinx Sidebar.
3. Check out the [Sidebar SDK Java API Reference](https://acrolinx.github.io/sidebar-sdk-java/) for more details.


### Building Integrations Based on Swing or JFX

Since Java 11 JavaFX isn't part of the JDK anymore, therefore this project uses [the gradle plugin for JFX](https://plugins.gradle.org/plugin/org.openjfx.javafxplugin).
Depending on what you want to achieve there are multiple options available on how to bundle your application.
Please have a look into the build.gradle file as well as the [OpenJFX documentation](https://openjfx.io/openjfx-docs/).

### CORS

To be able to connect to Acrolinx, you might have to enable [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) on the Java VM:

```bash
java -Dsun.net.http.allowRestrictedHeaders=true ...
```

Or via code:

```java
System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
```

On the Acrolinx Platform [Cross-Origin Resource Sharing](https://support.acrolinx.com/hc/en-us/articles/10211162946962-Enable-CORS), must be enabled as well.


## References

+ This DEMO is built on the [Sidebar SDK Java](https://github.com/acrolinx/sidebar-sdk-java).
+ [Sidebar SDK Java API Reference](https://acrolinx.github.io/sidebar-sdk-java/).
+ The Sidebar SDKs are based on the [Acrolinx Sidebar Interface](https://acrolinx.github.io/sidebar-interface/).

## License

Copyright 2015-present Acrolinx GmbH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at:

[https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

For more information visit: [https://www.acrolinx.com](https://www.acrolinx.com)
