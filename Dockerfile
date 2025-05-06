# ---------- Stage 1: Build ----------
FROM gradle:8.4-jdk17 AS builder

# Copy project files
WORKDIR /app
COPY --chown=gradle:gradle . .

# Build the Spring Boot app (fat JAR)
RUN gradle build -Pdb=postgres -x test --no-daemon

# ---------- Stage 2: Run ----------
FROM openjdk:17-jdk
LABEL authors="rahulojha"
# Create non-root user for security

# Copy the JAR from builder stage
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Expose port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar" , "/app/app.jar"]