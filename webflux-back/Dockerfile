FROM openjdk:17-jdk-alpine as builder
LABEL maintainer="6whistle"
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE=/build/libs/*.jar
RUN mkdir /app
COPY ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]