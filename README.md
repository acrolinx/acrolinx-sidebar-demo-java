# Acrolinx Java Sidebar Demo

This repository contains examples to integrate the Acrolinx Sidebar with
different Java UI Frameworks.

The Acrolinx Sidebar helps authors to review, correct and analyze their content.
It is designed to show up in their editor or editing environment.

## Table of contents

* [Table of contents](#table-of-contents)
* [The Acrolinx Sidebar](#the-acrolinx-sidebar)
* [Prerequisites](#prerequisites)
* [Quickstart](#quickstart)
* [Setup the Project](#setup-the-project)
* [Running the Acrolinx Samples](#running-the-acrolinx-samples)
* [License](#license)


## The Acrolinx Sidebar

To use the Acrolinx Sidebar you need to have an Acrolinx Server set up.
The Acrolinx Sidebar comes in two flavours.

There is a publicly available Acrolinx Sidebar which will enable you to always use the newest
version of the Acrolinx Sidebar.
To use it you need an Acrolinx Server running via https and with CORS enabled.
Check the [Acrolinx Support](https://support.acrolinx.com/hc/en-us/articles/203851132-Setting-up-the-Acrolinx-Sidebar#Enable_Cross_Origin_Resource_Sharing_CORS_on_your_Core_Server_)
for more information on how to enable CORS on your Acrolinx Server.

Acrolinx Server 4.7 and later come with the Acrolinx Sidebar installed.
The Acrolinx Sidebar is then available under
`http(s)://<AcrolinxServerHostName>:port/sidebar/v14/index.html`.

For more information on Acrolinx check [www.acrolinx.com](https://www.acrolinx.com).

[Back to top](#acrolinx-java-sidebar-demo)

## Prerequisites

Please contact Acrolinx SDK support (sdk-support@acrolinx.com) for consulting and getting your integration certified.
This sample works with a test license on an internal acrolinx server.
This license is only meant for demonstration and developing purposes.
Once you finished your integration you'll have to get a license for your integration from Acrolinx.

Please note that this is a demo project for Java Applications using the Sidebar Java SDK.
Acrolinx offers different other SDKs for developing integrations that might suit your purposes better.

Before you start developing your own integration, you might benefit from looking into the samples given within the demo folder.

[Back to top](#acrolinx-java-sidebar-demo)

## Quickstart

1. Select the latest release version from tags.

2. Within the dist folder you will find a zipped version of the acrolinx-sidebar-java-sdk. This includes a fat jar and
startup scripts to run the java ui demos.

3. Download this zip file and unpack it. Within the bin folder you'll find the start scripts.
For a windows environment run the .bat files and on a Unix System run the shell scripts.

**Important:** To run the acrolinxDemoClientSWT files you'll have to put the **Eclipse SWT library** into the lib folder. Depending on
your OS download the right jar in the [eclipse download area](http://download.eclipse.org/eclipse/downloads/). Select
the last stable build, then on the left you'll find the entry for "SWT binary and Source". Then choose the download
according to you OS. Unzip and  put the included jar file into the "lib" folder next to acrolinx-sidebar-java-sdk jar.
Run the acrolinxDemoClientSWT start script.

[Back to top](#acrolinx-java-sidebar-demo)

## Setup the Project

This repository contains a Java API and samples for JFX, Swing and SWT Applications.

This project is implemented with Java 8 and build with [Gradle](https://gradle.org/).

You need to have a JDK 8 installed on your system in order to run the sample with Gradle.
If you just interested to see the demo application in action please refer to the instructions below
[Quickstart](#quickstart).

The project comes with Gradle Wrapper. So if you don't have Gradle installed, go into the projects root folder and run:

`./gradlew build`

on an UNIX system or

`./gradlew.bat build`

on a Windows machine.

All dependencies needed will be downloaded and the samples are ready to be run.

You'll find three runnable main classes inside the 'demo' folder.

[Back to top](#acrolinx-java-sidebar-demo)

## Running the Acrolinx Samples

These samples run with the Acrolinx Sidebar hosted publicly.

After building the project run `./gradlew sidebar_demo_jfx:run` for the JavaFX sample.
Run `./gradlew sidebar_demo_swing:run` for the Java Swing sample
and `./gradlew sidebar_demo_swt:run` for the Java SWT sample.

On the start you'll the a text box next to the Acrolinx Sidebar.
Enter a Acrolinx Server address along with your credentials.
Note that you'll have to provide a server address starting with 'https'.

![Java FX Acrolinx Sidebar Sample: Server Address](/doc/img/ServerAddress.png)

![Java FX Acrolinx Sidebar Sample: Login](/doc/img/LoginScreen.png)

![Java FX Acrolinx Sidebar Sample: Loged in](/doc/img/LoggedIn.png)

Once you successfully logged in you'll see:

![Java FX Acrolinx Sidebar Sample: After login](/doc/img/ScreenshotIntegration.png)

You can now type some text in the left side text box. Then click the 'Check' button in the Acrolinx Sidebar.

![Java FX Acrolinx Sidebar Sample: After check](/doc/img/AcrolinxDemoCheck.png)

Click on a card on the left, to select the issue in the text.

![Java FX Acrolinx Sidebar Sample: Issue selected](/doc/img/ScreenIssueSelected.png)

Click on the green suggestion to replace the issue in the text with the suggestion.
The card will disappear from the issue list in the sidebar and the text will be replaced.
The Acrolinx Sidebar will then automatically select the next issue in line.

![Java FX Acrolinx Sidebar Sample: Issue replaced](/doc/img/IssueReplaced.png)

[Back to top](#acrolinx-java-sidebar-demo)

## License

Copyright 2015-2018 Acrolinx GmbH

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

For more information visit: [http://www.acrolinx.com](http://www.acrolinx.com)

[Back to top](#acrolinx-java-sidebar-demo)
