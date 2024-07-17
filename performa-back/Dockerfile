FROM maven:3-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
