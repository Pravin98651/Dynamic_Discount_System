# Use OpenJDK 17 for the build phase
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build the application
RUN mvn clean package -DskipTests

# Run phase
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/discount-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
