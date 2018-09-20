# scala-multi-project
## Using projects to configure the entry points in a single codebase

Example of how to configure sbt to build multiple projects that share a code base. Each project has a different main method :

Build the Cloud project in sbt
```sbt
sbt:root> project Cloud
[info] Set current project to CloudProject (in build file:/home/sdevine/multiball/)
sbt:CloudProject> assembly
[warn] Multiple main classes detected.  Run 'show discoveredMainClasses'to see the list
<snip>
[info] Packaging /home/sdevine/multiball/target/scala-2.12/root-assembly-1.0.jar ...
[success] Total time: 2 s, completed Sep 20, 2018 11:45:00 AM
```

The jar runs the main method in example.Cloud
```bash
sdevine@sdevine-desk:~/multiball$ java -jar /home/sdevine/multiball/Cloud/target/scala-2.12/cloud.jar
This service is running in the cloud! [example.Cloud$]
```

Build the OnPrem project in sbt
```sbt
sbt:CloudProject> project OnPrem
[info] Set current project to OnPremProject (in build file:/home/sdevine/multiball/)
sbt:OnPremProject> assembly
<snip>
[info] Packaging /home/sdevine/multiball/target/scala-2.12/root-assembly-1.0.jar ...
[success] Total time: 2 s, completed Sep 20, 2018 11:49:05 AM
```
The jar runs the main method in example.OnPrem
```bash
sdevine@sdevine-desk:~/multiball$ java -jar /home/sdevine/multiball/OnPrem/target/scala-2.12/onprem.jar
This service is running on prem [example.OnPrem$]
```
