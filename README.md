# channelpilot-productconnect

## Introduction

> Receive product data from a “shop system” as JSON Payload. Call a REST Service with two endpoints, The first endpoint is to authenticate the client for future requests and The second endpoint is to receive product data in a JSON payload. The “product” endpoint shall be versioned, so v1 has mandatory fields x,y,z. The remaining fields are optional. v2 has mandatory fields x,y,z, a,b with the remaining fields optional. 




## Requirements

> For building and running the application you need:

-  JDK 1.8

-   Maven 4

## Installation

> There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.channelpilot.productconnect.ChannelpilotConnectApplication class from your IDE.

> Alternatively you can use the Spring Boot Maven plugin like so:

>  - mvn spring-boot:run

## Configure application
> data persistence properties in resources/application.properties
- spring.datasource.url=jdbc:h2:file:./channeldb;DB_CLOSE_DELAY=-1
- spring.datasource.username=sa
- spring.datasource.password=

> Create schema and insert data on initialization
>  We may want to initialize database with some fixed schema (DDL) and insert default data (DML) into tables before the application is ready is run business usecases.We can achieve this by putting sql files into resources folder (/src/main/resources/).

> - schema.sql – To initialize the schema ie.create tables and dependencies.
> - data.sql – To insert default data rows.

> ## schema.sql#
>
 DROP TABLE IF EXISTS PRODUCT;

> CREATE TABLE PRODUCT (

 >  id INT AUTO_INCREMENT  PRIMARY KEY,

 >  x VARCHAR(250) NOT NULL,

 >  y VARCHAR(250) NOT NULL,

 > z VARCHAR(250) DEFAULT NULL,

 > a VARCHAR(250),

 > b VARCHAR(250));

> ## H2 Console
### Enable H2 console
By default, the console view of H2 database is disabled. We must enable it in application.properties file to view and access it in browser. Note that we can customize the URL of H2 console which, by default, is '/h2'

> - Enabling H2 Console : spring.h2.console.enabled=true
> - Custom H2 Console URL : spring.h2.console.path=/h2
> local url : http://localhost:8080/h2




 ### Authorization & security

> - authentication API endpoint URL is :  /api/user

> every request has to be sent over HTTPS and must :

>  Contain a valid API JWT Token get from /api/user endpoint . You'll have to provide your API token via Authorization-Header: Authorization: Bearer yourToken.Every call to API is secured by an API token

>  ### Get API token

> To enabe access to the REST API, a user has to be get token by username and password(in this case for simplicity you can pass any user and password ) from /api/user API Afterwards, a randomly generated API access key will be provided, which needs to be included in your API requests for authentication.

> ### sample call : 

> curl -L -X POST 'http://localhost:8080/api/user' -H 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'user=nazila' --data-urlencode 'password=golchin'

> ###  sample response : 

> {"user":"nazila","pwd":null,"token":"Bearer GeneratedToken"}

## Version options : V1 - V2
> accessible via swagger UI 

> Base URL : http://localhost:8080/swagger-ui.html

>  v1 has mandatory fields x,y,z. The remaining fields are optional. v2 has mandatory fields x,y,z, a,b with the remaining fields optional. 

> ### Endpoints : 



>  - addProduct API version 1 endpoint URL is : /api/v1/addProduct

>  - addProduct API version 1 endpoint URL is : /api/v2/addProduct

> ### sample call : 

>curl -L -X POST 'http://localhost:8080/api/v1/addProduct' -H 'Content-Type: application/json' -H 'Authorization: Bearer APIToken' --data-raw '{"x": "x1","y": "y1","z": "z1"}'

> ###  sample response : 

> {"payload":"Product {id=363, x=x1, y=y1, z=z1}","success":true}

 ### testcase

> test case for checking all APIs existe in \src\test\java\ChannelpilotConnectApplicationTests