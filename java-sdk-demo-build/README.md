# Releasing a New Version of the `acrolinx-sidebar-java-sdk`

In the gradle.properties file, remove "SNAPSHOT" from the version number.
Commit the updated file.

Find the build job on Jenkins find project `acrolinx-sidebar-javasdk` and start a build (or wait for its automated started).

Once the build finished, you have a new release in GitHub.

Afterwards:

* increment the version number,
* add `SNAPSHOT`,
* commit, and
* push it.
