# vpn_keep_alive
Simple program to perform periodic http GET requests in order to prevent Pulse Secure VPN disconnection.

## Requirements
- Gradle
- Java 8

## How to run
### Standalone jar
You can find the latest standalone jar in the [releases of this repository](https://github.com/Tale152/vpn_keep_alive/releases).  
Navigate to the directory where you stored the jar and launch:
```
java -jar .\<JAR_NAME>.jar <URL> <INTERVAL_MILLISECONDS> 
```

Example with Google and 600000ms (10 minutes) as interval:
```
java -jar .\vpn_keep_alive.jar https://www.google.com 600000 
```

### Development
Navigate to the project's directory and launch:
```
.\gradlew run --args='<URL> <INTERVAL_MILLISECONDS>'  
```

Example with Google and 600000ms (10 minutes) as interval:
```
.\gradlew run --args='[<URL>](https://www.google.com/) 600000'  
```

## How to build the standalone jar
Navigate to the project's directory and launch:
```
.\gradlew build  
```
this will create a standalone jar named "vpn_keep_alive.jar" inside the directory app/build/libs.
