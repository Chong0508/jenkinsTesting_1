# Stage 1: Build
FROM maven:3.9.3-eclipse-temurin-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Copy wait-for-it script
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
# Wait for MySQL before starting
ENTRYPOINT ["/wait-for-it.sh", "mysql-db:3306", "--", "java", "-jar", "/app.jar"]

