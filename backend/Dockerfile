# Stage 1: Build the application
FROM eclipse-temurin:21 AS build

# Set the working directory
WORKDIR /app

# Copy Maven Wrapper and related files
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./

# Make the Maven Wrapper executable
RUN chmod +x mvnw

# Download dependencies to go offline
RUN ./mvnw dependency:go-offline

# Copy the source code to the image
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the Spring Boot app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

