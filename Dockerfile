FROM openjdk:8-jdk-alpine
MAINTAINER BARATH
VOLUME /tmp
RUN adduser -D barath
USER barath
ADD target/swagger-springboot-code-generator-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]