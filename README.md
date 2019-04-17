# Acrolinx Java Sidebar Demo

This is a showcase for integrating the [Acrolinx](https://www.acrolinx.com/) Sidebar
into different Java UI framework-based applications (JFX, Swing, and SWT).

See: [Getting Started with Custom Integrations](https://docs.acrolinx.com/customintegrations)

## Live Demo

1. Go to [releases](https://github.com/acrolinx/acrolinx-sidebar-demo-java/releases).
2. Within the assets for the latest release you'll find a zipped version of the acrolinx-sidebar-java-demo.
   This includes a fat jar and startup scripts to run the java ui demos.
3. Download this zip file and unpack it.
   Within the `bin`-folder, you'll find the start scripts.
   For a windows environment, run the `.bat`-files and on a Unix System run the shell scripts.
4. Choose the sample, that you want to see. Use `https://test-ssl.acrolinx.com` as the Acrolinx URL.

*Note: To run the acrolinxDemoClientSWT files, you'll have to [put the Eclipse SWT library into the lib folder](#SWT).*

See Also: [Acrolinx Sidebar Web Live Demo](https://acrolinx.github.io/acrolinx-sidebar-demo/samples/index.html)

## The Acrolinx Sidebar

The Acrolinx Sidebar is designed to show up beside the window where you edit your content.
You use it for checking, reviewing, and correcting your content.
To get an impression what the Sidebar looks like in existing integrations, have a look at
[Get Started With the Sidebar](https://docs.acrolinx.com/coreplatform/latest/en/the-sidebar/get-started-with-the-sidebar).

## Prerequisites

Please contact [Acrolinx SDK support](https://github.com/acrolinx/acrolinx-coding-guidance/blob/master/topics/sdk-support.md)
for consulting and getting your integration certified.
This sample works with a test license on an internal Acrolinx URL.
This license is only meant for demonstration and developing purposes.
Once you finished your integration, you'll have to get a license for your integration from Acrolinx.
  
Acrolinx offers different other SDKs, and examples for developing integrations.

Before you start developing your own integration, you might benefit from looking into:

* [Getting Started with Custom Integrations](https://docs.acrolinx.com/customintegrations),
* the [Guidance for the Development of Acrolinx Integrations](https://github.com/acrolinx/acrolinx-coding-guidance),
* the [Acrolinx SDKs](https://github.com/acrolinx?q=sdk), and
* the [Acrolinx Demo Projects](https://github.com/acrolinx?q=demo).

## Getting Started

### Build the Project

1. You need to have a JDK 8 installed on your system, in order to run the sample with [Gradle](https://gradle.org/).
2. The project comes with Gradle Wrapper. So if you don't have Gradle installed, go into the projects root folder and run:

   ```bash
   ./gradlew build
   ```

    on an UNIX system, or

    ```batch
    gradlew build
    ```

    on a Windows computer.

3. All dependencies needed will be downloaded and the samples are ready to be run.
4. You'll find three runnable main classes inside the `demo`-folder.

### Run the Samples

Build the project first, then run:

#### JavaFX

```bash
./gradlew sidebar_demo_jfx:run
```

![Java FX Acrolinx Sidebar Sample](/doc/img/AcrolinxDemoCheck.png)

#### Swing

```bash
./gradlew sidebar_demo_swing:run
```

#### SWT

As prerequisite for the SWT sample:

1. Depending on your OS, download the right jar in the [Eclipse Download Area](http://download.eclipse.org/eclipse/downloads/).
2. Select the last stable build, then on the left you'll find the entry for "SWT binary and Source".
3. Choose the download according to your OS.
4. Unzip and put the included jar file into the `lib`-folder next to `acrolinx-sidebar-java-demo-<version>.jar`.
5. Run the `acrolinxDemoClientSWT` start script.

```bash
./gradlew sidebar_demo_swt:run
```

## Using the SDK

1. Just reference the Maven artifact `com.acrolinx.client:sidebar-sdk` that is available on [Maven Central](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.acrolinx.client%22%20a%3A%22sidebar-sdk%22%20).
   Have a look at the [`build.gradle`](build.gradle) file if you use Gradle.
2. Implement:
    + `AcrolinxIntegrationInterface`, and the
    + `InputAdapterInterface`.
    + The `AcrolinxSidebarInitParameterBuilder` helps you initialize the Acrolinx Sidebar.
3. Check out the [Sidebar SDK Java API Reference](https://acrolinx.github.io/sidebar-sdk-java/) for more details.

![Architecture and Interfaces](https://raw.githubusercontent.com/acrolinx/sidebar-sdk-java/master/img/ArchitectureInterfaces.png)

### CORS

To be able to connect to Acrolinx, you might have to enable [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing)
on the Java VM:

```bash
java -Dsun.net.http.allowRestrictedHeaders=true ...
```

Or via code:

```java
System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
```

On the Acrolinx Platform [Cross-Origin Resource Sharing](https://docs.acrolinx.com/coreplatform/latest/en/advanced/sidebar-configurations/enable-cross-origin-resource-sharing-cors),
must be enabled as well.

## SDK Features

1. Support for UI-frameworks:
    + JavaFx
    + Swing
    + SWT
2. `LookupRangesDiff` - Provides [lookup](https://github.com/acrolinx/acrolinx-coding-guidance/blob/master/topics/text-lookup.md)
  functionality.
3. **Start page**: Provides interactive way to sign in to Acrolinx with built-in error handling.
4. Provides [logging](https://github.com/acrolinx/sidebar-sdk-dotnet/blob/master/Acrolinx.Sidebar/Util/Logging/Logger.cs).
   Logging can be activated via:
    ```java
    LoggingUtils.setupLogging("AcrolinxDemoClientJFX");
    ```
5. Provides an `AcrolinxStorage` that can be used to persist Sidebar settings in the data store of the host editors.
   If not set, the SDK will default to the browsers LocalStorage.

## References

* This DEMO is built on the [Sidebar SDK Java](https://github.com/acrolinx/sidebar-sdk-java).
* [Sidebar SDK Java API Reference](https://acrolinx.github.io/sidebar-sdk-java/).
* The Sidebar SDKs are based on the [Acrolinx Sidebar Interface](https://acrolinx.github.io/sidebar-sdk-js/).

## How to release a new version of the Live Demo

Increment `currentVersion` in the gradle.properties file, commit and push on master branch.
This will trigger a build on Jenkins, which will automatically create a tag. Creating the tag will trigger a build on Travis,
which will upload the 'Live Sample' to the release assets.

## License

Copyright 2015-present Acrolinx GmbH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at:

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

For more information visit: [https://www.acrolinx.com](https://www.acrolinx.com)
