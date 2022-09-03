FROM openjdk:17-ea-jdk-oracle
ARG JAR_FILE=target/*.jar
ADD target/movierama-0.0.1-SNAPSHOT.jar movierama.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/movierama.jar"]