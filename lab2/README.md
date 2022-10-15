

# Lab 2-Java at the server-sideand the role of application containers

## 2.1 Server-side programming with servlets

A Servlet is a Java class that runs at the server, handles (client) requests, processes them,and replies with a response. A servlet must be deployed into a (multithreaded) Servlet Container to be usable.

### a) Prepare an application server to run your web projects:

Download Tomcat 9.0.68 Released zip, extract the zip and run the command ```./startup.sh``` 

Then try access the default page in the browser: http://localhost:8080

### b) The Tomcat installation includes a management environment (“Manager app”) you can use to control the server, including deploying and un-deploying applications you develop

Include the Manager app, and register at least one role in conf/tomcat-users.xml:

```<rolerolename="manager-gui"/>```

```<rolerolename="manager-script"/>```

```<userusername="admin" password="admin"roles="manager-gui,manager-script"/>```

Enter the http://localhost:8080/manager

### c) Create a maven-based web application

We used a Maven Central catalog, after we created the project, we run the ``` mvn install``` command. Then it is created a ```.war``` file, where the application is packed as a Web Archive.

### e) Deploy the packed application (.war) into the application server.

To deploy the packed application, we have 2 ways of doing it:

->  http://localhost:8080/manager

-> copy the .war file into <Tomcat root>/webapps

### f) Confirm that your application was successfully deployer in Tomcat:

If it is listed in the Manager app, then it is a success.

### h) Add a basic servlet to your project that takes the name of the user, passed as a parameter in the HTTP request and prints a customized message.

In older versions of the Servlet Container specifications, the developer needed to write a web.xml file with the configuration descriptors, including the mapping of Servlet classes and URL paths.

This was the last step of the 2.1 exercise, and it was successesfull

## 2.2 Server-side programming with embedded servers

Run the web container from within your app. In this case, you will be using an “embedded server”, since its lifecycle (start, stop) and the deployment of the artifacts is controlled by your application code. 

## 2.3 Introduction to web apps with a full-featured framework (Spring Boot)

Spring Boot is a rapid application development platform built on top of the popular Spring Framework. SpringBoot is a convention-over-configuration addition to the Spring platform,useful to get started with minimum effort and create stand-alone, production-grade applications.

### a) Use the Spring Initializr to create a new (maven-supported, Spring Boot)project for your web app.

Spring Initializr templates contain a collection of all the relevant transitive dependencies that are needed to start a particular functionality and will simplify the setup of the POM.

The generated seed project also includes a convenient Maven wrapper script(mvnw).

The [Maven Wrapper](https://maven.apache.org/wrapper/) is an  excellent choice for projects that need a specific version of Maven (or  for users that don't want to install Maven at all). Instead of installing many versions of it in the operating system, we can just use the project-specific wrapper script.

After generating the project, run the command ```$./mvnwspring-boot:run``` then access http://localhost:8080/. The result should be a white label error.

### b) Build a simple application to serve web contentas detailed in this Getting Started article.

Here we are building a Serving Web Content with Spring MVC, the goal is to build an application that has a static home page and that will also accept HTTP GET requests at: `http://localhost:8080/greeting`. If the application is a success, then It will respond with a web page that displays HTML. The body of the HTML will contain a greeting: “Hello, World!”

### GreetingController: 

In Spring’s approach to building web sites, HTTP requests are handled by a controller. You can easily identify the controller by the @Controller. The GreetingController handles GET requests for `/greeting` by returning the name of a [`View`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/View.html) (in this case, `greeting`). A `View` is responsible for rendering the HTML content. 

The `@GetMapping` annotation ensures that HTTP GET requests to `/greeting` are mapped to the `greeting()` method.

The implementation of the method body relies on a view technology (in this case, [Thymeleaf](http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html)) to perform server-side rendering of the HTML. Thymeleaf parses the `greeting.html` template and evaluates the `th:text` expression to render the value of the `${name}` parameter that was set in the controller.

### ServingWebContentApplication:

`@SpringBootApplication` is a convenience annotation that adds all of the following:

-> `@Configuration`: Tags the class as a source of bean definitions for the application context;

-> `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property  settings. For example, if `spring-webmvc` is on the  classpath, this annotation flags the application as a web application  and activates key behaviors, such as setting up a `DispatcherServlet`;

-> `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the `com/example` package, letting it find the controllers.

### Build an executable JAR:

Run the command ```./mvnw spring-boot:run``` and the test the application `http://localhost:8080/greeting.

### c) Extend you project to create a REST endpoint, which listen to the HTTP request, and answer with a JSON result(a greeting message).

### Create a Resource Representation Class

The service will handle `GET` requests for `/greeting`, optionally with a `name` parameter in the query string.

```{"id": 1,"content": "Hello, World!"}```

To model the greeting representation, create a resource representation  class. To do so, provide a plain old Java object with fields,  constructors, and accessors for the `id` and `content` data. -> Class Greeting

### Create a Resource Controller:

In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller. These components are identified by the [`@RestController`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html) annotation, and the `GreetingController` shown in the following listing (from `src/main/java/com/example/restservice/GreetingController.java`) handles `GET` requests for `/greeting` by returning a new instance of the `Greeting` class.

The `@GetMapping` annotation ensures that HTTP GET requests to `/greeting` are mapped to the `greeting()` method.

`@RequestParam` binds the value of the query string parameter `name` into the `name` parameter of the `greeting()` method. If the `name` parameter is absent in the request, the `defaultValue` of `World` is used.

https://spring.io/guides/gs/serving-web-content/

https://www.baeldung.com/maven-wrapper

https://spring.io/guides/gs/rest-service/

## 2.4 Wrapping-up & integrating concepts

### a) Create a web service (REST API) to offer random quotes from movies/shows.You should support the three endpoints listed below.

In this exercise we wrap up  all the concepts of this lab.

Link that I used to take somo quotes of movies: https://blog.hubspot.com/sales/famous-movie-quotes

Endpoints:

```api/quote``` -> Returns a random quote from a random show/film

```api/shows``` -> List of all available shows (for which some quote exists).For convenience, a show should have some identifier/code

```api/quotes?show=<show_id>``` -> Returns a random quote for the specified show/film

I created a Quote and a Greeting class( the Greeting class was pre generated, but represents the movies/shows), that represents the entities.

Then, a GreetingController, that has all the endpoints and functionalities, and a pre generated RestServiceApplication.

# REVIEW QUESTIONS

## A) What are the responsibilities/services of a “servlet container”?

### What is a servlet container?

A servlet container is **an implementation of the Jakarta Servlet specification, used primarily for hosting servlets**. A web server is a server designed to serve files from the local system, like Apache. A Java enterprise application server is a full-blown  implementation of the Jakarta EE specification.

### Resposibilities / services:

Servlet containers have a lot of resposibilities and services like:

-> Manage the servlets lifecycles, creating servlet instances and calls, construct a request object to pass to the servlets, and a response object for the servlet. Invokes the servlet `service()` method, It calls the `destroy()` method of the servlet to discard it when appropriate.

-> Map a certain URL to a particular servlet

-> Ensure that the URL requester has the correct access-rights

## B) Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)

### Spring Web Model-View-Controller:

A Spring MVC is a Java framework which is used to build web  applications. It follows the Model-View-Controller design pattern and implements all the basic features of a core spring framework like: Inversion of Control and Dependency Injection.

A Spring MVC provides an elegant solution to use MVC in spring framework by the help of **DispatcherServlet**. Here, **DispatcherServlet** is a class that receives and handles the incoming request and maps it to the right resource such as controllers, models, and views.  

The default handler is based on the @Controller and @RequestMapping annotations, offering a wide range of flexible handling methods.

In summary, the DispatcherServlet acts as the main Controller to route requests to their intended destination. Model is the data of our application, and the view is represented by any of the various template engines.

## not done

## C) Inspect the POM.xml for the previous SpringBoot projects. What is the role of the “starters” dependencies?

Spring Boot staters were invented to reduce the number of manually added dependencies just by adding one dependency. So instead of manually specifying the dependencies, you can just add one starter, saving a lot of time. That way we can increase the POM manageability and decrease the overall configuration time for the project. We also, have tested, Production-ready, and supported dependency configurations.

## D) Which annotations are transitively included in the @SpringBootApplication?

Many Spring Boot developers prefer their apps to use auto-configuration, component scan and be able to define extra configuration on their "application class". A single `@SpringBootApplication` annotation can be used to enable those three features, that are:

`@EnableAutoConfiguration`: enable Spring Boot’s auto-configuration mechanism

`@ComponentScan`: enable `@Component` scan on the package where the application is located 

`@Configuration`: allow to register extra beans in the context or import additional configuration classes

## E) Search online for the topic “Best practices for REST APIdesign”. From what you could learn, select your “top 5”practices,and briefly explain them in you own words.

### 1. Use JSON as the Format for Sending and Receiving Data: 

Jason has largely become the de-facto format for sending and receiving API data these days, because with XML, for example, itś often a bit of a hassle to decode and encode data, so XML isn widely supported by frameworks anymore. JavaScript, for example, has an inbuilt method to parse JSON data through the fetch API because JSON was primarily made for it.

### 2. Name Collections with Plural Nouns:

The data of API'S can be perceived as a collection of different resources from the consumers. If you have to develop collections in REST API, you should name them with plural nouns, that way it prevents ambiguity, and it makes it easier to understand the meaning of collection.

### 3. Use Status Codes in Error Handling:

You should always use regular HTTP status codes in responses to requests made to your API.This will help your users to know what is going on – whether the request is successful, or if it fails, or something else.

### 4. Use Nesting on Endpoints to Show Relationships:

Oftentimes, different endpoints can be interlinked, so you should nest them to make them easier to understand. You should avoid nesting that is more than 3 levels deep as this can make the API less elegant and readable.

### 5. Use Filtering, Sorting, and Pagination to Retrieve the Data Requested:

Sometimes, an API's database can get incredibly large. If this happens, retrieving data from such a database could be very slow. Filtering, sorting and pagination are all actions that can be performed on the collection of a REST API. This lets it only retrieve, sort, and arrange the necessary data into pages so the server doesn't get too occupied with request.
