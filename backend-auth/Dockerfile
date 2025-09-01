# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Install dependencies & build the jar
RUN mvn clean package -DskipTests

# Stage 2: Run JAR
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/backend-auth-*.jar app.jar

# Expose the Spring Boot port
EXPOSE 8083

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
