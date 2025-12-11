# Use an official Maven image with JDK
FROM maven:3.9.5-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy only pom.xml first to cache dependencies
COPY pom.xml .

# Pre-download dependencies (Docker layer caching)
RUN mvn -B dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Default build command
CMD ["mvn", "-B", "clean", "install"]
