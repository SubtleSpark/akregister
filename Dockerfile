FROM eclipse-temurin:17-jdk-alpine
MAINTAINER huangchengjun

ENV PARAMS=""

ADD target/akcheck-*.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]