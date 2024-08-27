# Use a base image for Java
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/patient-0.0.1.jar app.jar

# Expose the port used by the microservice
EXPOSE 8082

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]