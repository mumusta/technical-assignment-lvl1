# Technical Assignment


## Introduction
The system help to model and manipulate the three entities (Supplier, Product, and Order) and the relationship between each other, there is just one user in the system (superuser); who can log in to the Angular application (add/get Suppliers) and use all the restful APIs for Supplies, Products, and Orders exclusively.

## Functionality

### Base

- Add new supplier to the system
- Get all the details of all suppliers along with their products
- Get all the details of a particular supplier along with his products by ID
- Connect a supplier with a product by the ID of both
- Disconnect a supplier with a product by the ID of both
- Update supplier’s info by ID
- Delete a supplier from the system by ID
- Add new product to the system
- Get all the details of all products
- Get all the details of a particular product by ID
- Update product’s info by ID
- Delete a product from the system by ID
- Add new order to the system
- Get all the details of all orders along with their products + quantity of each
- Get all the details of a particular order along with its products + quantity of each by ID
- Add new order line to an order contains a particular product and the quantity by the ID of both
- Delete an order line of an order by the ID of both (order and product)
- Edit an order line (the quantity) of an order and a product by the ID of both
- Update order’s info by ID
- Delete an order from the system by ID

### Angular
- Login
- Logout
- Access get-all-suppliers screen
- Access get-single-supplier screen
- Delete a supplier
- Access add-new-supplier screen
- Access update-supplier screen

## Technologies

- Java 8
- Spring: SpringBoot, Data JPA, Security and DevTools
- Database: MySQL and H2
- ORM: Hibernate
- Logging: log4j
- Test: JUnit and Mockito
- Angular 2 and TypeScript
- Documentation: Swagger
- Html, CSS and Bootstrap
- Maven


 ## Quick start
 
 ### Required:
 - Java 8
 - Maven 
 - ...
 
 ### Steps:
 1. git clone https://github.com/mumusta/technical-assignment-lvl1.git
 2. mvn clean package xxx
 3. cd xxx
 4. java -jar xxx
 5. Go to http://localhost:9696/swagger-ui/index.html

## Screenshots
![1](xxx)