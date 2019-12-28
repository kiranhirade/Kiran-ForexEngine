# Spring Boot, H2, JPA, Hibernate Rest API Tutorial

Build Restful CRUD API for a forex-engine application using Spring Boot, H2 databse, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. H2 databse

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/kiranhirade/Kiran-ForexEngine.git
```

**2. Create H2 database**
```bash
create databse testdb and table forex
```


**4. Build and run the app using maven**

```bash
mvn package
java -jar target/forex-engine-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/forexs
    
    POST /api/forexs
    
    GET /api/forexs/{forexId}
    
    PUT /api/forexs/{forexId}
    
    DELETE /api/forexs/{forexId}

You can test them using postman or any other rest client.


