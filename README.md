# Bus Depot Backend App 

Bus Depot CRUD Application on SpringBoot. Working with h2 DB via Hibernate.
Working in pair with [Frontend React App](https://github.com/AdeleDev/bus_depot_react).

### Built With

* [![Java][Java.io]][Java-url]
* [![SpringBoot][SpringBoot.io]][SpringBoot-url]
* [![Hibernate][Hibernate.io]][Hibernate-url]
* [![OpenApi][OpenApi.io]][OpenApi-url]

## Pre-installations

#### Clone the repo:

```sh
git clone https://github.com/AdeleDev/bus_depot_app.git
```

#### Build project:

```sh
gradle clean
```

```sh
gradle build
```

## Usage

### Without frontend
```sh
gradle bootRun
```

### With frontend

 Setup address for client in application.yaml:

```
resources/application.yaml
```

Run service:

```sh
gradle bootRun
```

### Via Docker
 Switch in terminal to folder where Dockerfile 
```sh
docker build . -t bus-depot-backend  
```

Start without frontend:
```sh
docker run -d -p 8080:8080 --name backend-server bus-depot-backend
```

Start with frontend: set right path in docker compose for both images firstly
```sh
 docker-compose up -d
 ```

#### API request :
```
http://localhost:8080/v1/buses/
```

<!-- MARKDOWN LINKS & IMAGES -->

[Java.io]: https://img.shields.io/badge/-☕%20Java-blue?style=for-the-badge

[Java-url]: https://www.java.com/ru/

[SpringBoot.io]: https://img.shields.io/badge/-Springboot-green?style=for-the-badge&logo=springboot

[SpringBoot-url]: https://spring.io/projects/spring-boot

[Hibernate.io]: https://img.shields.io/badge/-Hibernate-gray?style=for-the-badge&logo=hibernate

[Hibernate-url]: https://hibernate.org/

[OpenApi.io]: https://img.shields.io/badge/-OpenApi-blueviolet?style=for-the-badge&logo=openapiinitiative

[OpenApi-url]: https://www.openapis.org/

[Wiremock.io]: https://img.shields.io/badge/-🍊%20Wiremock-lightblue?style=for-the-badge


