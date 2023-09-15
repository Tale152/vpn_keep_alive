![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

![GitHub last commit](https://img.shields.io/github/last-commit/Tale152/vpn_keep_alive)
![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/Tale152/vpn_keep_alive?include_prereleases)
![GitHub Release Date](https://img.shields.io/github/release-date/Tale152/vpn_keep_alive)
![GitHub commits since latest release (by date)](https://img.shields.io/github/commits-since/Tale152/vpn_keep_alive/latest)

# vpn_keep_alive
Simple program to perform periodic http GET requests; this simple project was born out of frustration at work since Pulse Secure VPN disconnects if, after a while, no traffic happens between my computer and something behind the VPN.  

Why not using a simple script you might ask? Company policies prevent me from doing that for security reasons, so I needed to be a little more creative.

Whether you also need to keep a VPN alive or you just need to periodically perform periodic http GET requests, that's the solution for you.

## Table of contents
- [How to run the project](#how-to-run-the-project)
  - [Run the Docker image](#run-the-docker-image)
  - [Run the standalone jar](#run-the-standalone-jar)
  - [Compile it yourself](#compile-it-yourself)
- [Future development ideas](#future-development-ideas)
- [How to create a new release](#how-to-create-a-new-release)

## How to run the project
Here there is a list of various ways of running this project.  

Every run method requires two arguments:
- __URL__: the url that will be contacted when the http GET is performed  
  (e.g. : _https://www.google.com_)
- __INTERVAL_MILLISECONDS__: the amount of time (expressed in milliseconds) between one http GET request and the next one
- (e.g. : _600000_, which is 10 minutes)

You might need (depending on which method you choose):
- Docker
- Java 8
- Gradle

### Run the Docker image
[Here](https://hub.docker.com/repository/docker/alessandrotalmi/vpn_keep_alive/general) you can find a Docker Hub repository containing working Docker images for this project.

#### Run it once
Assuming you want to call the container _"vpn_keep_alive"_, run this command to execute the application:
```console
docker run -e URL=<URL> -e INTERVAL=<INTERVAL_MILLISECONDS> --name vpn_keep_alive alessandrotalmi/vpn_keep_alive
```

#### Autostart
I personally let Docker autostart on my system boot and, by launching this command just once (note the _"--restart=always"_ parameter, I don't need to think about it ever again:
```console
docker run -e URL=<URL> -e INTERVAL=<INTERVAL_MILLISECONDS> --restart=always --name vpn_keep_alive alessandrotalmi/vpn_keep_alive
```

### Run the standalone JAR
You can find the latest standalone jar in the [releases of this repository](https://github.com/Tale152/vpn_keep_alive/releases); for every release, a new jar file will be published, changing the file name according to the release's version itself.  
In this example I will use the __v0.1.0__, with the jar file named __vpn_keep_alive_v0.1.0.jar__.

Once you have downloaded the jar file, navigate to the directory where you stored it and launch the command:
```console
java -jar .\vpn_keep_alive_v0.1.0.jar <URL> <INTERVAL_MILLISECONDS> 
```

### Compile it yourself
If you want to get your hands dirty, clone the repository and, once you have placed yourself in the project's directory, run the following command:
```console
.\gradlew build  
```
this will create a standalone jar named _"vpn_keep_alive.jar"_ inside the directory _app/build/libs_; you can then run the jar file the same way as previously described.

If you simply want to run the project, just execute this command:
```console
.\gradlew run --args='<URL> <INTERVAL_MILLISECONDS>'  
```

If, on the other hand, you want to build your own Docker image, just use the regular Docker image creation commands and then run the image remembering to add the aguments as previously described.

## Future development ideas
I organized the code to easily add a GUI if this project needs to be used by someone that is not very confident when using the command line.

I might change the implementation from Java to Kotlin just to make practice with the language.

If you want to add something to the project, feel free to create a fork and create a pull request :D !

## How to create a new release
If you fork this repository and you would like to create your own releases, you first need to create some Repository Secrets that will be used by the GitHub actions to automate the process:
- __DOCKERHUB_USERNAME__: your Docker Hub username
- __DOCKERHUB_PASSWORD__: your Docker Hub password
These credentials will be used to publish a working Docker image on your Docker Hub profile.

You also need to grant __write permissions__ to the repository's __GITHUB_TOKEN__ in order to allow the creation of an automatic release.  

Check the files in _.github/workflows_ to manipulate what happens when you trigger the pipeline. 

Once you satisfy the previous requirements, if you want to create a new release set __master__ as your current branch and run:
```console
git tag -a v<VERSION> -m "<RELEASE NOTES>" 
```
to create a new tag for the release and then run:
```console
git push origin --tags
```
to push the new tag.  
This will trigger the repository's actions that will create a new release (with a standalone jar attached) and publish a new Docker image on your Docker Hub profile.
