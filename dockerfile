# Stage 1: Build the Spring Boot app using Gradle
FROM gradle:8.4-jdk21-alpine as builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Stage 2: Run the Spring Boot app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 9081
ENTRYPOINT ["java", "-jar", "app.jar"]
