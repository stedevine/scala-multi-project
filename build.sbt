lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.12.6",
    version := "1.0",
    organization := "example",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )

lazy val Cloud  = project
  .settings(
    name:="CloudProject",
    // Set the entry point using the fully qualified name
    // Settings for the compile and package tasks are set seperately
    mainClass in (Compile, run) := Some("example.Cloud"),
    mainClass in assembly := Some("example.Cloud"),
    assemblyJarName in assembly := "cloud.jar"
  )
  // Include all the code in the root project in this project
  .dependsOn(root)
  // Any task performed on this project (for example running tests) will also be performed on root
  .aggregate(root)

lazy val OnPrem = project
    .settings(
      name:="OnPremProject",
      mainClass in (Compile, run) := Some("example.OnPrem"),
      mainClass in assembly := Some("example.OnPrem"),
      assemblyJarName in assembly := "onprem.jar"
    )
    .dependsOn(root)
    .aggregate(root)
