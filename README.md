# scala-multi-project
## Using projects to configure the entry points in a single codebase

Example of how to configure sbt to build multiple projects that share a code base. Each project has a different main method :

Build the cloud project in sbt
```sbt
sbt:service> projects
[info] In file:/home/sdevine/multiball/
[info] 	   cloud
[info] 	   onPrem
[info] 	 * service

sbt:service> project cloud
[info] Set current project to cloud (in build file:/home/sdevine/multiball/)


sbt:cloud> assembly
[info] Updating ...
[info] Done updating.
[info] TestService:
[info] - output text is formatted
[info] Run completed in 276 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[info] Strategy 'discard' was applied to a file (Run the task at debug level to see details)
[info] Packaging /home/sdevine/multiball/target/cloud/scala-2.12/cloud.jar ...
[info] Done packaging.
[success] Total time: 2 s, completed Sep 28, 2018 10:32:35 AM

```

The jar runs the main method in example.Cloud
```bash
sdevine@sdevine-desk:~/multiball$ java -jar target/cloud/scala-2.12/cloud.jar
This service is running in the cloud! [example.Cloud$]

```

Build the OnPrem project in sbt
```sbt
sbt:cloud> project onPrem
[info] Set current project to onPrem (in build file:/home/sdevine/multiball/)
sbt:onPrem> clean / assembly
[info] TestService:
[info] - output text is formatted
[info] Run completed in 98 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[info] Strategy 'discard' was applied to a file (Run the task at debug level to see details)
[info] Packaging /home/sdevine/multiball/target/onprem/scala-2.12/onprem.jar ...
[info] Done packaging.
[success] Total time: 1 s, completed Sep 28, 2018 10:34:00 AM

```
The jar runs the main method in example.OnPrem
```bash
sdevine@sdevine-desk:~/multiball$ java -jar target/onprem/scala-2.12/onprem.jar
This service is running on prem [example.OnPrem$]

```

Tests are executed when each assembly is built.
