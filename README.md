# scala-multi-project
## Using projects to configure the entry points in a single codebase

Example of how to configure sbt to build multiple projects that share a code base. Each project has a different main method :

Build the Cloud project in sbt
```sbt
sbt:root> projects
[info] In file:/home/sdevine/multiball/
[info] 	   Cloud
[info] 	   OnPrem
[info] 	 * root
sbt:root> project Cloud
[info] Set current project to CloudProject (in build file:/home/sdevine/multiball/)
sbt:CloudProject> assembly
[info] Updating root...
[info] Formatting 1 Scala source ProjectRef(uri("file:/home/sdevine/multiball/"), "root")(test) ...
[info] Done updating.
[info] Updating ...
[info] Formatting 3 Scala sources ProjectRef(uri("file:/home/sdevine/multiball/"), "root")(compile) ...
[info] Done updating.
[info] Compiling 3 Scala sources to /home/sdevine/multiball/target/scala-2.12/classes ...
[info] Done compiling.
[info] Compiling 1 Scala source to /home/sdevine/multiball/target/scala-2.12/test-classes ...
[info] Done compiling.
[info] TestFormatter:
[info] - output text is formatted
[info] Strategy 'discard' was applied to a file (Run the task at debug level to see details)
[info] Packaging /home/sdevine/multiball/target/cloud/scala-2.12/cloud.jar ...
[info] Done packaging.
[success] Total time: 2 s, completed Sep 26, 2018 4:16:16 PM

```

The jar runs the main method in example.Cloud
```bash
sdevine@sdevine-desk:~/multiball$ java -jar target/cloud/scala-2.12/cloud.jar 
This service is running in the cloud! [example.Cloud$]

```

Build the OnPrem project in sbt
```sbt
sbt:CloudProject> project OnPrem
[info] Set current project to OnPremProject (in build file:/home/sdevine/multiball/)
sbt:OnPremProject> assembly
[info] Updating ...
[info] Done updating.
[info] TestFormatter:
[info] - output text is formatted
[info] Strategy 'discard' was applied to a file (Run the task at debug level to see details)
[info] Packaging /home/sdevine/multiball/target/onprem/scala-2.12/onprem.jar ...
[info] Done packaging.
[success] Total time: 3 s, completed Sep 26, 2018 4:18:03 PM

```
The jar runs the main method in example.OnPrem
```bash
sdevine@sdevine-desk:~/multiball$ java -jar target/onprem/scala-2.12/onprem.jar
This service is running on prem [example.OnPrem$]

```

Tests are executed when each assembly is built. 
