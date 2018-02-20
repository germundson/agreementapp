# Application-App

## Requirements

Building this application requires [Maven](https://maven.apache.org/) to be installed.

#Running the application

Before you can use this application, the agreementsoap application needs to be running
When the agreementsoap is running, you can build this application with maven

```shell
mvn clean install
```

When the application is build an all tests have run successfully
enter the application-app folder and use maven to run the application

```shell
mvn spring-boot:run
```

A gui [swagger](http://localhost:8090) should be available at [http://localhost:8090](http://localhost:8090)


### Application frameworks

- Spring
- liquibase
- h2-database
- guava
- jackson
- swagger
- junit