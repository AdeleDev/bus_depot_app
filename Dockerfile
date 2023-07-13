FROM gradle:7.2.0-jdk17 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM bellsoft/liberica-openjdk-alpine:17
EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/src.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]