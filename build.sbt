lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.12.6",
    version := "1.0",
    organization := "example",
  )


lazy val Cloud = project
  .settings(
    name:="CloudProject",
    // Set the entry point using the fully qualified name
    // Settings for the compile and package tasks are set seperately
    mainClass in (Compile, run) := Some("example.Cloud"),
    mainClass in assembly := Some("example.Cloud"),
    assemblyJarName in assembly := "cloud.jar"
  )
  .dependsOn(root)

lazy val OnPrem = project
    .settings(
      name:="OnPremProject",
      mainClass in (Compile, run) := Some("example.OnPrem"),
      mainClass in assembly := Some("example.OnPrem"),
      assemblyJarName in assembly := "onprem.jar"
    )
    .dependsOn(root)

/*
lazy val OnPremProject = (project in file("."))
  .settings(
    name:="OnPrem",
    organization := "example",
    scalaVersion := "2.12.6",
    version := "1.0",
    mainClass in (Compile,assembly) := Some("example.OnPrem"),
    assemblyJarName in assembly := "onprem.jar"
  )
*/

/*
lazy val commonSettings = Seq(
  scalaVersion := "2.12.6",
  version := "1.0"
)

lazy val libraries = Seq(

)
*/
