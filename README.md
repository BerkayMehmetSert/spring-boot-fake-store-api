# Spring Boot Fake Store API

This is a fake store API built with Spring Boot.

## Getting Started

### Prerequisites

* Java 19
* JDK 19
* Maven


### Installing

* Clone the project
* Install the dependencies with `mvn install`
* Run the project with `mvn spring-boot:run`

### Usage

* The API is available at [http://localhost:8080](http://localhost:8080)
* The Swagger UI is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Database

PostgreSQL is used as the database.

### Application properties

The application properties are located in `src/main/resources/application.properties`.

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/FakeStore
spring.datasource.username=_yourUsername_
spring.datasource.password=_yourPassword_
spring.jpa.properties.javax.persistence.validation.mode=none
```

### Database creation

The database can be created with the following command:

```sql
CREATE DATABASE FakeStore;
```

### Database tables

The database tables are created automatically by Hibernate.


## Endpoints

### Products

* `localhost:8080/api/products` - GET - Get all products
* `localhost:8080/api/products/{id}` - GET - Get a product by id
* `localhost:8080/api/products` - POST - Create a product
* `localhost:8080/api/products/{id}` - PUT - Update a product
* `localhost:8080/api/products/{id}` - DELETE - Delete a product

### Categories

* `localhost:8080/api/categories` - GET - Get all categories
* `localhost:8080/api/categories/{id}` - GET - Get a category by id
* `localhost:8080/api/categories` - POST - Create a category
* `localhost:8080/api/categories/{id}` - PUT - Update a category
* `localhost:8080/api/categories/{id}` - DELETE - Delete a category

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Swagger](https://swagger.io/) - API Documentation
* [PostgreSQL](https://www.postgresql.org/) - Database


## License

This project is licensed under the MIT License - see the [LICENSE©](LICENSE) file for details
