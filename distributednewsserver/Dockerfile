FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/\*.jar -t spring-server .
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]