// Multi project example
// Uses the same source files to build two different jars
// each with its own defined main method.

// the OnPrem main class is defined in the service directory, the clound main class is in it's own source tree
// This means the cloud jar will actually have two main methods:

// java -cp /home/sdevine/multiball/target/cloud/scala-2.12/cloud.jar example.Cloud
// This service is running in the cloud! [example.Cloud$]
// java -cp /home/sdevine/multiball/target/cloud/scala-2.12/cloud.jar example.OnPrem
// This service is running on prem [example.OnPrem$]

// It only has one default main method
// java -jar /home/sdevine/multiball/target/cloud/scala-2.12/cloud.jar
// This service is running in the cloud! [example.Cloud$]

// The OnPrem jar will only have one:
// java -cp /home/sdevine/multiball/target/onprem/scala-2.12/onprem.jar example.OnPrem
// This service is running on prem [example.OnPrem$]
// java -cp /home/sdevine/multiball/target/onprem/scala-2.12/onprem.jar example.Cloud
// Error: Could not find or load main class example.Cloud


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
  // because that's where the common code lives.
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
    // For on prem all the code lives under service
    .dependsOn(service)
