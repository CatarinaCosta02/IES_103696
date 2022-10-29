# Lab 3 Multi-layer web applications with Spring Boot

# 3.1 Accessing databases in SpringBoot

## a) Complete the guided exercise available from “Spring Boot CRUD Application with Thymeleaf”.

We followed the guide and then we access the http://localhost:8080/, where we can add the name and the email of a new User.

The implementation of [DAO](https://www.baeldung.com/java-dao-pattern) layers that provide CRUD functionality on [JPA](https://en.wikipedia.org/wiki/Java_Persistence_API) entities can be a repetitive, time-consuming task that we want to avoid in most cases.

Luckily, [Spring Boot](https://www.baeldung.com/spring-boot) makes it easy to create CRUD applications through a layer of standard JPA-based CRUD repositories.

### The Domain Layer:

*@Entity* annotation, the JPA implementation, which is Hibernate, in this case, will be able to perform CRUD operations on the domain entities. 

*@NotBlank* constraint. This implies that we can use Hibernate  Validator for validating the constrained fields before persisting or  updating an entity in the database.

### The Repository Layer:

### **Spring Data JPA** **allows us to implement JPA-based repositories (a fancy name for the DAO pattern implementation) with minimal fuss.**

[Spring Data JPA](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa) is a key component of Spring Boot's *spring-boot-starter-data-jpa* that makes it easy to add CRUD functionality through a powerful layer of  abstraction placed on top of a JPA implementation. This abstraction  layer allows us to access the persistence layer without having to  provide our own DAO implementations from scratch.

### The Controller Layer:

**Thanks to the layer of abstraction that \*spring-boot-starter-data-jpa\* places on top of the underlying JPA implementation, we can easily add  some CRUD functionality to our web application through a basic web tier.**

In this case, a single controller class will suffice for handling GET  and POST HTTP requests and then mapping them to calls to our *UserRepository* implementation.

The controller class relies on some of Spring MVC's key features.

## b) Walkthrough the available solution and answers these questions:

### \- The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?

The new repository is instantiated by the @Autowired annotation to the UserController constructor.

### \- List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?

findAll(); findById(Long id); delete(User user); save(User user); This methods are defined in the CrudRepository interface.

### \- Where is the data being saved?

The data is being saved in the H2 database.

Small defenition : **H2** is an **embedded, open-source,** and **in-memory** database. It is a relational database management system written in [Java](https://www.javatpoint.com/java-tutorial). It is a **client/server** application. It is generally used in **unit testing**. It stores data in memory, not persist the data on disk.

### \- Where is the rule for the “not empty” email address defined?

 It is defined in the User class, with a @NotBlank annotation.

Small Definititon: The @NotBlank annotation uses the NotBlankValidator class, which **checks that a character sequence's trimmed length is not empty**

## c)Extend the solution by adding a new field to the User entity (e.g.: Phone)and refactor the code accordingly.

In this exercise we added the phone field to the User entity, so when we were about to add a new User, there was also an input for the phone.

# 3.2 Multilayer applications: exposingdata with RESTinterface

## a) In this exercise you will need an instance of MySQLserver(stick with version 5.7). Is this statement, note thar we are mapping the container standard MySql port (3306) into a different one in our host.

```$ docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1-e MYSQL_DATABASE=demo-e MYSQL_USER=demo-e MYSQL_PASSWORD=secret2-p 33060:3306-d mysql/mysql-server:5.7```

## from b - g

We followed the guided exercise whre we built a **CRUD RESTFul APIs** for a Simple *Employee Management System* using Spring Boot 2 JPA and MySQL.

In this exercise, we created a JPA Entity - Employee.java, a Spring Data Repository - EmployeeRepositury.java and a Spring Rest Controller - EmployeeController.java. To solve the Exception(Error) for RESTful Services we created a *ResourceNotFoundException.java* class, where we can specify the Response Status for a specific exception along with the definition of the Exception with *‘@ResponseStatus’* annotation.

After all that, we test the REST API'S via Postman Client, trying the POST, GET and DELETE methods.

In this exercise folder there are some pictures of the results given by those methods.

# 3.3 Wrapping-up and integrating concepts

## a) Work with a persistent database of quotes4.

To complete this exercise, we adapted the exercise 4 from de lab2 and used the https://www.sourcecodeexamples.net/2021/08/spring-boot-project-with-controller.html to come closer to the privious exercise.

## b)Separate the “boundary” of the problem (@RestController) from the repository, by addingan intermediary @Service component, like in this example. The RestController provides the wiring for HTTP but requires the Service to answer all requests; the Service holds the business logic and interacts with the Repository(or other components) as needed.

### Notes:

@Table(name = "..."): associates an entity with a table

@ManyToOne(...): indicates a 1-to-many relationship

### API layer:

Presents the application's features and data to the users

### Bussiness Layer:

Contains the business logic that drives the application's functionalities.

### Presistence Logic:

This layer interacts with the database so can save and restore the application data

# Review questions

## A) Explain the differences between the RestController and Controller components used in different parts of this lab.

### @Controller :

Spring @Controller annotation is also a specialization of [@Component](https://www.geeksforgeeks.org/spring-component-annotation-with-example/) annotation. The @Controller annotation indicates that a particular class serves the role of a **controller**. Spring Controller annotation is typically used in combination with annotated handler methods based on the [@RequestMapping](https://www.geeksforgeeks.org/spring-requestmapping-annotation-with-example/) annotation. It can be applied to classes only. 

### @RestController :

RestController is used for making restful web services with the help of  the @RestController annotation. This annotation is used at the class  level and allows the class to handle the requests made by the client.  Let’s understand @RestController annotation using an example. The  RestController allows to handle all REST APIs such as GET, POST, Delete, and PUT requests. 

## B) Create a visualization of the Spring Boot layers (UML diagram or similar), displaying the key abstractions in the solution of 3.3, in particular: entities, repositories, services and REST controllers.

Describe the role of the elements modeled in the diagram.

![Diagram](https://user-images.githubusercontent.com/101096418/198854838-4fb2406b-21c0-4e52-a52f-b2177a580359.png)

### Entity:

Specifies that the following classes are entities and is mapped to a database table.

### RestController (GreetingController):

Defines a controller with ResponseBody implied on the handler methods

### JpaRepository:

Is a **JPA (Java Persistence API)** specific extension of Repository. It contains the full API of **CrudRepository and PagingAndSortingRepository**. 

## C) Explain the annotations @Table, @Colum, @Id found in the Employee entity.

### @Table: 

The @Table annotation allows you to specify the details of the table that will be used to persist the entity in the database. So, this annotation specifies the primary table in the database for the Employee entity.

### @Colum:

The @Colum annotation specifies the details of a column in the table created for the Employee  entity. In other words, it specifies the fields of Employee entity. If we don't include this annotation, the name of the field will be  considered the name of the column.

### @Id:

This annotation **specifies the primary key of the entity**. This annotation defines the primary key of the Employee entity.

## D) Explain the use of the annotation @AutoWired (in the Rest Controller class).

The Spring framework enables automatic dependency injection. In other words, **by declaring all the bean dependencies in a Spring configuration file,  Spring container can autowire relationships between collaborating beans**. This is called ***Spring bean autowiring\***.

The @Autowired annotation is used to auto wire a bean on the setter method, constructor or a field, while telling Spring where an injection needs to occur.

In this case, by anotating @Autowired to the constructor of Rest Controller (with  Service as a parameter), the Service will be automatically injected.


