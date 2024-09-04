# Use a base image for Java
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Expose the port used by the microservice
EXPOSE 8082

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/patient-0.0.1.jar"]