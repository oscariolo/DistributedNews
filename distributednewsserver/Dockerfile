# Use a multi-stage build to reduce the final image size
FROM gradle:jdk17-corretto AS builder
WORKDIR /app
# Copy files with proper ownership
COPY --chown=gradle:gradle . .
# Clean and build without tests and disable the Gradle daemon
RUN gradle clean build -x test --no-daemon

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]