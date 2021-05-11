## Jenkins

### Initialization
#### Default pulgins installed
#### Admin user

### e) create new job 
### lab4 ex2 used

console output
--------------------------
Started by user Margarida
Running as SYSTEM
Building in workspace /var/lib/jenkins/workspace/lab8-ex1
The recommended git tool is: NONE
No credentials specified
Cloning the remote Git repository
Cloning repository /home/guids/Desktop/TQS/TQSLabs
 > git init /var/lib/jenkins/workspace/lab8-ex1 # timeout=10
Fetching upstream changes from /home/guids/Desktop/TQS/TQSLabs
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- /home/guids/Desktop/TQS/TQSLabs +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git config remote.origin.url /home/guids/Desktop/TQS/TQSLabs # timeout=10
 > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10
Avoid second fetch
 > git rev-parse refs/remotes/origin/master^{commit} # timeout=10
 > git rev-parse origin/master^{commit} # timeout=10
ERROR: Couldn't find any revision to build. Verify the repository and branch configuration for this job.
Finished: FAILURE 
-------------------------
