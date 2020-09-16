FROM openjdk:8-jdk-alpine 
COPY /target/spring-mvc-intro-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","spring-mvc-intro-0.0.1-SNAPSHOT.jar"]
