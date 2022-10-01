# IES_103696

# 1.1 Basic setupfor Java development   

The recommended Java version was  OpenJDK17LTS, but i used ```javac --version ```  result: javac 11.0.16
Test the Maven installation: ```mvn  --version ``` result: Apache Maven 3.6.3 Java version: 11.0.16
Setting up Git: ```git --version```
                ```git config --list```
The use of graphical Gitclients is optional (e.g.:  SourceTree,GitKraken).In addition, IDEs have built-in support for git commands

# 1.2 Build management with the Maven tool

## What is Maven?

Maven is a popular open-source build tool developed by the Apache Group to build, publish, and deploy several projects at once for better project management. The tool provides allows developers to build and document the lifecycle framework. 
The regular “build” of a (large) project takesseveral steps, such as obtaining dependencies (required external components/libraries), compiling source code, packagingartifacts, updating documentation, installing on the server, etc.

Create a Maven project: ``` mvn archetype:generate -DgroupId=com.pt.ua.deti.app -DartifactId=MyWeatherRadar -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false```

## API request:

### Whats an API request?

An API call, or API request, is a message sent to a server asking an API to provide a service or information.

Start by analyzingthe struct of the API requests and the replies (e.g.: check the 5-days aggregateforecastfor a location).You may use any HTTP client, such as the browseror the curlutility. For example, to get a 5-day forecast for Aveiro(which has the internal id=1010500)

```$ curl http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1010500.json|json_pp```

## POM.xml:

### What's POM.xml?

A Project Object Model or POM is the fundamental unit of work in Maven. It is an XML file that contains information about the project and configuration details used by Maven to build the project. It contains default values for most projects.

Check the content of the POM.xml and the folder structure that was created.Change/add some additional properties in your project, such as the development team, character encodingor the Java versionto useby the compiler.E.g.:

```
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
```

We can also add dependencies in POM.xml, like retrofit, Gson and converter-gson.

```
<dependency>
      <groupId>com.squareup.retrofit2</groupId>
      <artifactId>retrofit</artifactId>
      <version>2.9.0</version>
    </dependency>
    
    <dependency>
      <groupId>com.squareup.retrofit2</groupId>
      <artifactId>converter-gson</artifactId>
      <version>2.9.0</version>
    </dependency>
    
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.9.1</version>
    </dependency>
  </dependencies>
  ```
  In POM, we declare direct dependencies; these artifacts will usually require other dependencies, forming a graph of project dependencies. For example, Retrofit will requireOkHTTP.
  
  ```
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
```
  
```
  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
```

## Compile and run the project:

```$ mvn package```
```$ mvn exec:java-Dexec.mainClass="pt.ua.deti.Weather"```

Change the implementation to receive the city code as a parameter

``` mvn exec:java -Dexec.mainClass="pt.ua.deti.Weather" -Dexec.args="1010500"```

# 1.3 Source code management using Git

Add a .gitignore: https://gist.github.com/icoPT/31b343c987cb45ba0cde2bbee8cbd7ea

Import your projectinto the Git versioncontrol and synchronizewith to the cloud:

``` $ cd project_folder ```
```$ git init```
```$ git remote add origin <REMOTE_URL>```
```$ git add.```
```$ git commit -m "Initial project setup for exercise 1_3"```
```$ git push -u origin main```

Let’s simulatethe existenceof multiple contributors to the project.Let’s call the main IES root as“location1” and another auxiliar folder as “location2” (should be a temporary space, outsider the main root of IES).
Clone your (remote) repositoryinto location2.

Using “location2” as your current working directory, add a new feature;specifically, create a log for your application, i.e.,the application should write a logtracking the operations that are executed, as well as any problems that have occurred.

```
<dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-api</artifactId>
      <version>2.6.1</version>
    </dependency>
    
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.6.1</version>
    </dependency>  
```

Once you are satisfied with the implementation, be sure to commit the changes into to the main line (remote repository) and then synchronize your project at the initial “location1”.
Have a look at your repository history: ```$ git log --reverse --oneline```

# 1.4 Introductionto Docker 

Clone a repository in a container: ```docker run --name repo alpine/git clone \ url docker cp repo:/git/getting-started/```

Build a image: ```cd getting-started docker build -t docker101tutorial ```

Orientation and Setup:

``` $  docker run -d -p 80:80 docker/getting-started```

## What's a container?

A container is a sandboxed process on your machine that is isolated from all other processes on the host machine. 

Install the Portainer CE server appusing the “Docker” deployment option.

```docker volume create portainer_data``` -> create the volume that Portainer Server will use to store its database

```docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:latest``` ->  download and install the Portainer Server container

```docker ps``` -> You can check to see whether the Portainer Server container has started by running 

you can log into your Portainer Server instance by opening a web browser and going to: https://localhost:9443

### Define your own image (Dockerfile):

### Dockerfile:
A Dockerfile is a text document that contains all the commands a user could call on the command line to assemble an image.

### Docker container image:
A lightweight, standalone, executable package of software that includes everything needed to run an application: code, runtime, system tools, system libraries and settings.

 Run PostgresSQL using a pre-made image: ```docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres```
 
 Connect to PSQL server: https://dev.to/shree_j/how-to-install-and-run-psql-using-docker-41j2
 
 After all the steps, manage your postgres from the browser by launching: http://localhost:5050
 ```docker run --rm -p 5050:5050 thajeztah/pgadmin4```
 
### Multiple services (Docker compose):

https://docs.docker.com/compose/gettingstarted/ You will use a “composition” of two containers (Flask service, depending in Redis).

# 1.5 Wrapping-up& integrating concepts

This exercise is composed by two small, idependent projects: IpmaApiClient and WeatherForecastByCity

In the IpmaApiClient was executed the command ``` mvn install``` 

In the WeatherForecastByCity was executed the command ``` mvn package```

Unfortunately in this exercise I had some issues, so i could't complete it in the best way possible.

# Review Questions

## A)Maven has three lifecycles: clean, site and default. Explain the main phases in the default lifecycle.

**Clean lifecycle:** Handles project cleaning
**Site lifecycle:** Handles the creation of the project's wed site
**Default lifecycle:** Handles the project deployment

In the three maven lifecycles, every single one of them is composed by a sequence of phases. The default lifecycle has 23 phases, as it's the main built lifecycle. The main phases of the default lifecycle are:

1) ```validate```-> validate the project is correct and all necessary information is available
2) ```compile``` -> compile the source code of the project
3) ```test-compile``` -> compile the test source code into the test destination directory
4) ```test``` -> run tests using a suitable unit testing framework. These tests should not require the code be packaged or deployed
5) ```package``` -> take the compiled code and package it in its distributable format, such as a JAR
6) ```integration-test``` -> process and deploy the package if necessary into an environment where integration tests can be run
7) ```verify``` -> run any checks to verify the package is valid and meets quality criteria
8) ```install``` -> install the package into the local repository, for use as a dependency in other projects locally
9) ```deploy``` -> done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects

It's important to know that when executing a specific phase (```mvn <phase>```) we are also executing all previous phases as well.

## B)Maven is a build tool; is it appropriate to run your project to?

Build tools are the tools or programs that help create an executable application from the source code. As the name suggests, it’s essential for building or scripting a wide variety of tasks. With that, build tools such as Maven, are good for helping to download dependencies, which refers to the libraries or JAR files,updating documentation,compiling source code,installing the packaged code in the local repository, server, or central repository,etc.

Maven has also a lot of advantages, like:
>Simplifies the process of project building;
>Increases the performance of the project and the building process;
>In Maven, it’s easy to add new dependencies by writing the dependency code in the pom file;
>Provides easy access to all the required information.

Maven is a project management tool, that focuses on the simplification and standardization of the building process, taking care of the following:

>* Builds
>* Documentation
>* Reporting
>* Dependencies
>* SCMs
>* Releases
>* Distribution

## C)What wouldbe a likely sequence of Git commands required to contribute with a new feature to a given project? (i.e., get a fresh copy, develop some increment, post back the added functionality)

1) ``` git pull origin``` -> get a copy
2) add a certain feature
3) ``` git init``` -> if there's no git repository estabilished
4) ```git remote add origin <REMOTE_URL>```
5) ``` git status ```
6) ``` git add <NEW_FEATURE> .``` 
7) ``` git commit -m "message" ```
8) ``` git push -u origin main```

## D)There are strong opinions on how to write Git commitmessages... Find some best practices online and give your own informed recommendationson how to write good commit messages(in a teamproject).

The most importante things when writing commit messages are clarity and simplicity. If a commit agrees with these to points, the people that are working on the same project, will undersand quickly what has been done. That way, working with the others turns out to be more easy and less hardworking.

So to have a good guideline for creating commit messages while colaborating with th others:

1) Separate subject from body with a blank line;
2) Limit the subject line to 50 characters;
3) Capitalize the subject line;
4) Do not end the subject line with a period;
5) Use the imperative mood in the subject line;
6) Wrap the body at 72 characters;
7) Use the body to explain what and why vs. how;

(https://cbea.ms/git-commit/)

Example of a good commit message:

``` git commit -m "lab1_4 IES done"```

## E)Docker automatically prepares the required volume space as you start a container. Why is it important that you take an extra step configuring the volumes for a (production) database?

Docker, by default, already prepares the required volume space for the starting container. Because volumes are the preferd mechanism for persisting data generated and used by Docker containers, while bind mounts are dependent on the directory structure and OS of the host machine, volumes are completely managed by Docker.

In addition, volumes are often a better choice than persisting data in a container’s writable layer, because a volume does not increase the size of the containers using it, and the volume’s contents exist outside the lifecycle of a given container.

However, we must take an extra step configuring the volumes for a (production) database, because the volumes size can grow exponentially and, even when no running container is using a volume, that same volume is still available to Docker and is not removed automatically.
