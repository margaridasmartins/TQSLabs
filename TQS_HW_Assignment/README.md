# TQS_HW_Assignment


### Api Used: Open Weather Map API

**website:** https://home.openweathermap.org/
**api key:** f7ddcbe1a3d60790175e59092cd49713 
**api documentation** https://openweathermap.org/api/air-pollution

**get current air metrics** http://api.openweathermap.org/data/2.5/air_pollution?lat=50&lon=50&appid=f7ddcbe1a3d60790175e59092cd49713
**get forecast air metrics** http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=50&lon=50&appid=f7ddcbe1a3d60790175e59092cd49713
**get past air metrics** http://api.openweathermap.org/data/2.5/air_pollution/history?lat=508&lon=50&start=1606223802&end=1606482999&appid=f7ddcbe1a3d60790175e59092cd49713

**sonar** mvn sonar:sonar \
  -Dsonar.projectKey=tqs_hw \
  -Dsonar.host.url=http://127.0.0.1:9000 \
  -Dsonar.login=0a5cc7b6d9264fe7a0e157aca3c2e0c66c0389a2

**sonar cloud** mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar

ENV SONAR_TOKEN=c67bc9403aeaa25697d0457e33262864bad8ccb2