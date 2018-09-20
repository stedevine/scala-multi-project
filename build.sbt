//name:="Test"
//scalaVersion := "2.12.6"
//version := "1.0"
//organization := "example"
// Switch the entry point of the app by setting this value
//mainClass in assembly := Some("example.OnPrem")
//mainClass in assembly := Some("example.Cloud")
//assemblyJarName in assembly := "test.jar"
// Test that the entry point is set correctly :
// java -jar target/scala-2.12/test.jar

lazy val root = (project in file("."))
  .settings(
    name:="multiball",
    scalaVersion := "2.12.6",
    version := "1.0",
    organization := "example",
    // Switch the entry point of the app by setting this value
    mainClass in assembly := Some("example.OnPrem"),
    //mainClass in assembly := Some("example.Cloud")
    assemblyJarName in assembly := "test.jar"
    // Test that the entry point is set correctly :
    // java -jar target/scala-2.12/test.jar
  )


lazy val Cloud = project
  .settings(
    name:="CloudProject",
    organization := "example",
    scalaVersion := "2.12.6",
    version := "1.0",
    mainClass in assembly := Some("example.Cloud"),
    assemblyJarName in assembly := "cloud.jar"
  )
  .dependsOn(root)

lazy val OnPrem = project
    .settings(
      name:="OnPremProject",
      organization := "example",
      scalaVersion := "2.12.6",
      version := "1.0",
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
