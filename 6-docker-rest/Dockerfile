FROM maven:3.8.1-openjdk-15 AS build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN --mount=type=cache,target=/root/.m2 mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:15-alpine
COPY --from=build /usr/src/app/target/6-docker-rest-1.0-SNAPHSHOT.jar /usr/app/6-docker-rest-1.0-SNAPHSHOT.jar
CMD ["java","-jar","/usr/app/6-docker-rest-1.0-SNAPHSHOT.jar"]