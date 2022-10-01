# IES_103696

# Review Questions

## A)Maven has three lifecycles: clean, site and default. Explain the main phases in the default lifecycle.

**Clean lifecycle:** Handles project cleaning
**Site lifecycle:** Handles the creation of the project's wed site
**Default lifecycle:** Handles the project deployment

In the three maven lifecycles, every single one of them is composed by a sequence of phases. The default lifecycle has 23 phases, as it's the main built lifecycle. The main phases of the default lifecycle are:

```validate```-> validate the project is correct and all necessary information is available
```compile``` -> compile the source code of the project
```test-compile``` -> compile the test source code into the test destination directory
```test``` -> run tests using a suitable unit testing framework. These tests should not require the code be packaged or deployed
```package``` -> take the compiled code and package it in its distributable format, such as a JAR
```integration-test``` -> process and deploy the package if necessary into an environment where integration tests can be run
```verify``` -> run any checks to verify the package is valid and meets quality criteria
```install``` -> install the package into the local repository, for use as a dependency in other projects locally
```deploy``` -> done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects

It's important to know that when executing a specific phase (```mvn <phase>```) we are also executing all previous phases as well.

## B)Maven is a build tool; is it appropriate to run your project to?

Build tools are the tools or programs that help create an executable application from the source code. As the name suggests, it’s essential for building or scripting a wide variety of tasks. With that, build tools such as Maven, are good for helping to download dependencies, which refers to the libraries or JAR files,updating documentation,compiling source code,installing the packaged code in the local repository, server, or central repository,etc.

Maven has also a lot of advantages:
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

The most importante things when writing commit messages are clarity and simplicity. If a commit agrees with these to points, the others will undersand quickly what has been done, and will make work with the others more easy.

So to have a good guideline for creating commit messages while colaborating with th others:

1) Separate subject from body with a blank line
2) Limit the subject line to 50 characters
3) Capitalize the subject line
4) Do not end the subject line with a period
5) Use the imperative mood in the subject line
6) Wrap the body at 72 characters
7) Use the body to explain what and why vs. how

(https://cbea.ms/git-commit/)

Example of a good commit message:

``` git commit -m "lab1_4 IES done"```

## E)Docker automatically prepares the required volume space as you start a container. Why is it important that you take an extra step configuring the volumes for a (production) database?

Docker, by default, already prepares the required volume space for the starting container. Because volumes are the preferd mechanism for persisting data generated and used by Docker containers, while bind mounts are dependent on the directory structure and OS of the host machine, volumes are completely managed by Docker.

In addition, volumes are often a better choice than persisting data in a container’s writable layer, because a volume does not increase the size of the containers using it, and the volume’s contents exist outside the lifecycle of a given container. You can create a volume explicitly using the docker volume create command, or Docker can create a volume during container or service creation.

However, we must take an extra step configuring the volumes for a (production) database, because the volumes size can grow exponentially and, even when no running container is using a volume, that same volume is still available to Docker and is not removed automatically.
