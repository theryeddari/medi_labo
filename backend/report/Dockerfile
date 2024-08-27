# Utiliser une image de base pour Java
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR construit dans le conteneur
COPY target/report-0.0.1.jar app.jar

# Exposer le port utilisé par le microservice
EXPOSE 8083

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]