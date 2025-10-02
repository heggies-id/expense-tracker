# Stage 1: Build the application
FROM openjdk:17-jdk-slim as build

WORKDIR /app

# Copy the Maven wrapper and the project definition file
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download project dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application JAR file
RUN ./mvnw package -DskipTests

# Stage 2: Create the final, smaller image
FROM openjdk:17-jre-slim

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-jar","app.jar"]
