FROM maven:3.9.3-eclipse-temurin-17 as build
COPY src src
COPY pom.xml pom.xml
RUN mvn -B package

FROM eclipse-temurin:17-jdk-alpine
MAINTAINER huangchengjun

ENV PARAMS=""

COPY --from=build target/akcheck-*.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]