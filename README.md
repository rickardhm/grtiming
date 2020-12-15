# Runtimer

A cross-platform software for doing timing on running events

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

A preconfigured DB, connection configuration is done in hibernate_cfg.xml
configur log4j in log4j.properties 

### Installing

1. clone this repository
1. compile - mvn clean install (To rum without test: mvn clean install -DskipTests)

A step by step series of examples that tell you how to get a development env running

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

mvn test

## Run the function test
mvn clean compile assembly:singl
java -jar target/grtiming-1.1-jar-with-dependencies.jar

### Run the application

mvn spring-boot:run

http://localhost:8080

Endpoints:
```
/address
/address/{id}
/participants
/participant/{id}
/ping
/race
/race/{id}
/register/{id}
/raceevent
/raceevent/{id}
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Rickard Hillebrink** - *Initial work* - [rickardhm](https://github.com/rickardhm)
