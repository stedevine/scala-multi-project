name:="Test"
scalaVersion := "2.12.6"
version := "1.0"
set mainClass in assembly := Some("example.OnPrem")
assemblyJarName in assembly := "onprem.jar"

/*
val Cloud = project
  .settings(
    name:="cloud",
    scalaVersion := "2.12.6",
    version := "1.0",
    mainClass in (Compile,assembly) := Some("example.Cloud")
  )
*/

/*
lazy val OnPrem = project
  .settings(commonSettings:_*)
  .settings(libraries: _*)
  .settings(
    name:="OnPremProject",
    mainClass in (Compile,run) := Some("example.OnPrem")
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
