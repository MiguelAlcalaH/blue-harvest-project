# Use an OpenJDK base image with Java 21
FROM openjdk:21-jdk-slim

# Set a working directory in the container
WORKDIR /app

# Copy the Maven POM file and source code to the container
COPY pom.xml .
COPY src ./src

# Resolve dependencies and build the Spring Boot application
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package -DskipTests

# Copy the JAR file to the image
COPY target/blueharvest-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
