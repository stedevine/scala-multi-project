// Multi project example
// Uses the same source files to build two different jars
// each with its own main method.

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.12.6",
    version := "1.0",
    organization := "example",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  )
  // Don't build a jar for the root project, there's no entry point
  // and it's not used as a stand alone library
  .disablePlugins(sbtassembly.AssemblyPlugin)

lazy val Cloud  = project
  .settings(
    name:="CloudProject",

    // Set the entry point using the fully qualified name
    // Settings for the compile and package tasks are set seperately
    mainClass in (Compile, run) := Some("example.Cloud"),
    mainClass in assembly := Some("example.Cloud"),

    // Tests are defined the root project, run them whenever we
    // execute the test tasks in the Test or assembly configuration
    test in Test := (root / Test / executeTests).value,
    test in assembly := (root / Test / executeTests).value,

    // write the output under the common target directory
    target := file("target/cloud"),
    assemblyJarName in assembly := "cloud.jar"
  )
  // The compile configuration depends on the compile configuration for root
  // because that's where the code lives
  .dependsOn(root)

lazy val OnPrem = project
    .settings(
      name:="OnPremProject",
      mainClass in (Compile, run) := Some("example.OnPrem"),
      mainClass in assembly := Some("example.OnPrem"),
      test in Test := (root / Test / executeTests).value,
      test in assembly := (root / Test / executeTests).value,
      target := file("target/onprem"),
      assemblyJarName in assembly := "onprem.jar"
    )
    .dependsOn(root)
