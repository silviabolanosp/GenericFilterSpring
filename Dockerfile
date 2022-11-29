FROM adoptopenjdk/maven-openjdk11 as base
WORKDIR /app

COPY pom.xml .

RUN ["mvn", "dependency:go-offline", "--batch-mode"]

COPY src src

FROM base as test
RUN ["mvn", "test"]

FROM base as build
RUN ["mvn", "-Dmaven.test.skip", "package"]

FROM openjdk:11-jre-slim-buster as deploy
COPY --from=build /app/target/recruiting-platform-service-0.0.1-SNAPSHOT.jar \
                  /app/target/recruiting-platform-service.jar

EXPOSE 8086

ARG PROFILE
ENV profile="-Dspring.profiles.active=$PROFILE"
ENTRYPOINT java $profile -jar /app/target/recruiting-platform-service.jar
