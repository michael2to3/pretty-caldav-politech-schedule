FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY gradlew settings.gradle ./
COPY gradle gradle

COPY app /app
RUN ./gradlew build --no-daemon

EXPOSE 8080
CMD ["java", "-jar", "build/libs/app.jar"]
