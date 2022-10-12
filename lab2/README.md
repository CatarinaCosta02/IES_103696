# REVIEW QUESTIONS

## A) What are the responsibilities/services of a “servlet container”?

### What is a servlet container?

A servlet container is **an implementation of the Jakarta Servlet specification, used primarily for hosting servlets**. A web server is a server designed to serve files from the local system, like Apache. A Java enterprise application server is a full-blown  implementation of the Jakarta EE specification.

### Resposibilities/services:

-> Manege the servlets lifecycles, creating servlet instances and calls, construct a request object to pass to the servlets, construct a response object for the servlet, invokes the servlet `service()` method, It calls the `destroy()` method of the servlet to discard it when appropriate.

-> Mapp a certain URL to a particular servlet

-> Ensure that the URL requester has the correct access-rights

## B) Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)

### Spring Web Model-View-Controller:

**A model contains the data of the application**. A data can be a single object or a collection of objects. Controller - A controller contains the business logic of an application.

A Spring MVC is a Java framework which is used to build web  applications. It follows the Model-View-Controller design pattern. It  implements all the basic features of a core spring framework like  Inversion of Control, Dependency Injection.

A Spring MVC provides an elegant solution to use MVC in spring framework by the help of **DispatcherServlet**. Here, **DispatcherServlet** is a class that receives the incoming request and maps it to the right resource such as controllers, models, and views.  

## not done

## C) Inspect the POM.xml for the previous SpringBoot projects. What is the role of the “starters” dependencies?

Spring Boot staters were invented to reduce the number of manually added dependencies just by adding one dependency. So instead of manually specifying the dependencies you can just add one starter, saving a lot of time. That way we can increase pom manageability and decrease the overall configuration time for the project. We also, have tested, Production-ready, and supported dependency configurations.

## D) Which annotations are transitively included in the @SpringBootApplication?

Many Spring Boot developers like their apps to use auto-configuration, component scan and be able to define extra configuration on their "application class". A single `@SpringBootApplication` annotation can be used to enable those three features, that is:

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

Sometimes, an API's database can get incredibly large. If this happens, retrieving data from such a database could be very slow. Filtering, sorting and pagination are alll actions that can be performed on the collection of a REST API. This lets it only retrieve, sort, and arrange the necessary data into pages so the server doesn't get too occupied with requests.
