# Java Rest Service
This is a demo JAVA REST server for demonstrating my understanding on Java. It is currently on a POC stage.
Live location: To Be Deployed.

Dependencies used:
- Web
- JPA

Roadmap:
- Spring Security

## APIS

GET - /memes/original - Gets memes from MySQL database
GET - /memes/internet - Gets memes from the Internet.


## Run server locally
```bash
./mvnw spring-boot:run
```

## Build and execute .jar file
```bash
./mvnw clean package

# Execute
java -jar target/rest-service-0.0.1-SNAPSHOT.jar
```