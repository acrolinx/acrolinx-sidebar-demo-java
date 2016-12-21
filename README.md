# Acrolinx Java Sidebar Demo

This repository contains libraries and examples to integrate the Acrolinx Sidebar with
different Java UI Frameworks.

The Acrolinx Sidebar helps authors to review, correct and analyze their content.
It is designed to show up in their editor or editing environment.

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

## Prerequisites

This sample works with a test license on an internal Acrolinx Server. The license is provided
for developing and demonstrating purposes only. Once you finished your integration you will
need to get a license for your integration from Acrolinx.

To work with this sample you will need to have Acrolinx Server Credentials.
Please contact Acrolinx SDK support (sdk-support@acrolinx.com) to get these credentials.

## Setup the Project

This repository contains a Java API and samples for JFX, Swing and SWT Applications.

This project is implemented with Java 8 and build with Gradle.

By default the project is set up to be used with IntelliJ.

In the `build.gradle` file comment out the lines containing `apply plugin: 'idea'`
and comment in `apply plugin: 'eclipse'` to build the project with Eclipse.

For gradle support with other IDEs ask Google.

The project comes with Gradle Wrapper. So if you don't have Gradle installed, go into the projects root folder and run:

`./ gradlew build`

on an UNIX system or

`./gradlew.bat build`

on a Windows machine.

All dependencies needed will be downloaded and the samples are ready to be run.

