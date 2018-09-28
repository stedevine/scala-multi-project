// Multi project example
// Uses the same source files to build two different jars
// each with its own main method.

lazy val service = (project in file("."))
  .settings(
    scalaVersion := "2.12.6",
    version := "1.0",
    organization := "example",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  )
  // Don't build a jar for the service project, there's no entry point
  // and it's not used as a stand alone library.
  .disablePlugins(sbtassembly.AssemblyPlugin)

lazy val cloud  = project
  .settings(
    // Set the entry point using the fully qualified name.
    // Settings for the Compile and assembly configurations are set separately
    mainClass in Compile := Some("example.Cloud"),
    mainClass in assembly := Some("example.Cloud"),

    // Tests are defined the service project, there are no tests to execute under
    // Cloud/src/test/scala/...
    // run the service tests when we execute the test tasks in the Test or assembly configurations.
    test in Test := (service / Test / test).value,
    testOnly in Test := (service / Test / testOnly).evaluated,  // testOnly is an InputTask
    test in assembly := (service / Test / test).value,

    // Write the output under the common target directory.
    target := file("target/cloud"),
    assemblyJarName in assembly := "cloud.jar"
  )
  // The compile configuration depends on the compile configuration for service
  // because that's where the code lives.
  .dependsOn(service)

lazy val onPrem = project
    .settings(
      mainClass in Compile := Some("example.OnPrem"),
      mainClass in assembly := Some("example.OnPrem"),
      test in Test := (service / Test / test).value,
      testOnly in Test := (service / Test / testOnly).evaluated,
      test in assembly := (service / Test / test).value,
      target := file("target/onprem"),
      assemblyJarName in assembly := "onprem.jar"
    )
    .dependsOn(service)
