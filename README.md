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
 
 ### Steps:
 0. Username: "Admin", and Password: "123".
 1. git clone https://github.com/mumusta/technical-assignment-lvl1.git
 2. Run *AssignmentApplication* from Backend then run `ng serve` from Frontend.
 3. You can navigate to `http://localhost:9696/swagger-ui/index.html` for APIs.
 4. You can navigate to `http://localhost:4200/` for Angular app.

## Screenshots
![s0](https://user-images.githubusercontent.com/33350449/132582532-ac220a09-e8f2-4c08-9aef-0d83f140ddc5.png)
![s1](https://user-images.githubusercontent.com/33350449/132582524-1a88898e-8666-4616-be8f-f2de91e1c4e5.png)
![s2](https://user-images.githubusercontent.com/33350449/132582521-9df5acc3-e042-4e26-adec-41c8c9bb945a.png)
![s3](https://user-images.githubusercontent.com/33350449/132582518-d805a738-81f0-426b-a6d0-44c7077857e1.png)
![a-login](https://user-images.githubusercontent.com/33350449/132582545-02240f7b-5ffa-4ab1-8d31-3e0f4a929156.png)
![a-all](https://user-images.githubusercontent.com/33350449/132582542-d064a4fe-19c1-467f-9123-78cdbcd1f891.png)
![a-add](https://user-images.githubusercontent.com/33350449/132582547-d5b3b89a-3789-467f-a7a6-78eaacf8e055.png)
![a-update](https://user-images.githubusercontent.com/33350449/132582537-5c05dbc6-9256-46eb-befd-d99cb2f231ae.png)
![a-del](https://user-images.githubusercontent.com/33350449/132582535-8d52a651-0195-498b-8a08-8e9994fdd729.png)
