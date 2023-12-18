# RETAIL-STORE

This is a sample Java / Maven / Spring Boot application that exposes REST endpoint for creating basic Retail Store.
The Objective is to be able to give discount based on the following;

* Give 30% discount to Customers who are employees
* Give 10% discount to Affiliate Customers
* A customer for over 2 years, will get a 5% discount.
* For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45
  as a discount)
* The percentage based discounts do not apply on groceries.
* A user can get only one of the percentage based discounts on a bill.

## How to Run
This application is packaged as a jar which has Tomcat embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository
* Make sure you are using JDK 11 and Maven 2.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=prod target/retail-store-v1-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=prod"
```
## About the Service

The service is just a simple retail store REST service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in ```com.joa.retailstorev1``` on **port 8040**. (see below)

More interestingly, you can start calling some of the operational endpoints (see full list below) 

You can use this sample service as a basic starter to a retail or POS solution

Here is what this little application demonstrates:

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Dynamic CDI ar runtime
* Packaging as a single jar with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports JSON request / response;
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations.
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations

Here are some endpoints you can call:

### Get information about system customer, category, product etc.

```
http://localhost:8084/customer
http://localhost:8091/category
http://localhost:8091/product
http://localhost:8091/sales
http://localhost:8091/billing
```
### Create a customer resource

```
POST /customer
Accept: application/json
Content-Type: application/json

{
   "firstName": "David",
   "lastName": "Hamsel",
   "email": "david@gmail.com",
   "userType": "EMPLOYEE"
},

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8040/customer
```
### Retrieve list of customers

```
http://localhost:8040/customer

Response: HTTP 200
Content: Customer list 
```

### Retrieve customer based on id

```
http://localhost:8040/customer/{id}

Response: HTTP 200
Content: Customer
```

### Post Discount
```
POST http://localhost:8040/billing

{
    "id": 1,
    "product": [
        {
            "id": 1,
            "name": "Product 1",
            "description": "Product 1 Description",
            "price": 600.00,
            "category": {
                "id": 2,
                "name": "grocery"           
            }
        },
        {
            "id": 2,
            "name": "Product 2",
            "description": "Product 2 Description",
            "price": 800.00,
            "category": {
                "id": 2,
                "name": "furniture"
            }
        }
    ],
    "customer": {
        "id": 1,
        "firstName": "Jeffrey",
        "lastName": "Adams",
        "email": "joa@gmail.com",
        "userType": "AFFILIATE",
        "createdAt": [
            2023,
            12,
            17,
            15,
            48,
            30,
            529059000
        ]
    },
    "totalCost": 1000.00
}

Response: HTTP 200
Content: Customer
```

### To view Swagger 2 API docs

Run the server and browse to http://localhost:8084/swagger-ui/index.html

## Running the test and coverage report

You can either use ``` mvn surefire-report:report``` and go to target/site/surefire-report.html

or

You can run ``` mvn clean jacoco:prepare-agent install jacoco:report ``` and got to target/site/jacoco/index.html