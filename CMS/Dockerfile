# Use a valid OpenJDK 17 image
FROM eclipse-temurin:17-jdk-focal

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose port for Spring Boot
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/conference-management-0.0.1-SNAPSHOT.jar"]
