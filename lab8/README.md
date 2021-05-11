## Jenkins

### Initialization
#### Default pulgins installed
#### Admin user

### 1 e) create new job 
### lab4 ex2 used

build sucessefull
--------------------------------
Waiting for Jenkins to finish collecting data
[JENKINS] Archiving /var/lib/jenkins/workspace/lab8-ex1/lab4/P2CarManager/pom.xml to tqs.lab4/carApi/0.0.1-SNAPSHOT/carApi-0.0.1-SNAPSHOT.pom
[JENKINS] Archiving /var/lib/jenkins/workspace/lab8-ex1/lab4/P2CarManager/target/carApi-0.0.1-SNAPSHOT.jar to tqs.lab4/carApi/0.0.1-SNAPSHOT/carApi-0.0.1-SNAPSHOT.jar
channel stopped
Finished: SUCCESS
-------------------------------

### 2 j) pipeline
### lab4 ex2 used

build output
--------------------------------------------
[Pipeline] }
[Pipeline] // stage
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Declarative: Tool Install)
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] }
[Pipeline] // stage
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (test java installation)
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ java -version
java version "11.0.4" 2019-07-16 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.4+10-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.4+10-LTS, mixed mode)
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (test maven installation)
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ mvn -version
[1mApache Maven 3.6.3[m
Maven home: /usr/share/maven
Java version: 11.0.4, vendor: Oracle Corporation, runtime: /usr/lib/jvm/jdk-11.0.4
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.4.0-72-generic", arch: "amd64", family: "unix"
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
---------------------------------------------------------