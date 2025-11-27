FROM openjdk:11-alpine
MAINTAINER edgarrt
COPY target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]