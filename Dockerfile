# Build stage
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Production stage
FROM eclipse-temurin:21.0.2_13-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar Application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Application.jar"]
