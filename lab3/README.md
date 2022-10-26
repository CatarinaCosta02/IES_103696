# Review questions

## A) Explain the differences between the RestController and Controller components used in different parts of this lab.

### @Controller :

Spring @Controller annotation is also a specialization of [@Component](https://www.geeksforgeeks.org/spring-component-annotation-with-example/) annotation. The @Controller annotation indicates that a particular class serves the role of a **controller**. Spring Controller annotation is typically used in combination with annotated handler methods based on the [@RequestMapping](https://www.geeksforgeeks.org/spring-requestmapping-annotation-with-example/) annotation. It can be applied to classes only. 

### @RestController :

RestController is used for making restful web services with the help of  the @RestController annotation. This annotation is used at the class  level and allows the class to handle the requests made by the client.  Let’s understand @RestController annotation using an example. The  RestController allows to handle all REST APIs such as GET, POST, Delete, and PUT requests. 

## B) Create a visualization of the Spring Boot layers (UML diagram or similar), displaying the key abstractions in the solution of 3.3, in particular: entities, repositories, services and REST controllers.
Describe the role of the elements modeled in the diagram.

FAZER AINDA

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


