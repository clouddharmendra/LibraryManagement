# LibraryManagement
Online LibraryManagement application: Techologies-> Java - Angular 9(front-end),Spring Boot Microservices, Mongodb
This application show the Books information Library using springboot microservices(API), MongoDB as Database and display data on angular ui. 

Prerequisites: Java 8, MongoDB, Node.js, Angular 9 and respective IDE's.
Please setup the required environment variables related to the tools f required.

To run this application, execute the following commands:

1. Run MongoDB
Command to run at command prompt - mongod 
Please note, set the classpath of Mongo to execute the above command.

2. Unzip the OnlineLibraryManagement file.
you can see below directories:
	-> OnlineLibraryManagement_ui
	-> OnlineLibraryManagement_services
	

OnlineLibraryManagement_ui: This directory contains angular ui code.
  To run this code open, command prompt in this directory and execute below commands
	 > npm install
	 > ng serve
Angular app will start's with 4200 port. You can access website via  http://localhost:4200
	
OnlineLibraryManagement_services: This directory contains back-end api's code, which is implemented on spring-boot microservice, mongodb techlogoies.
  To run this code, open command prompt in this directory and execute below command
	 >mvnw spring-boot:run

service is running fine or not by http://localhost:8080/shoppingcart/products 
This microservice loads the products items into MongoDB and the above URL shows the same entered data.

Use http://loclhost:8080/swagger-ui.html to see all exposed micro serves.


