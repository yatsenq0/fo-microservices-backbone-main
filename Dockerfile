FROM openjdk:21-jdk-slim

WORKDIR /app

COPY gradle/ gradle/
COPY gradlew build.gradle settings.gradle ./
RUN chmod +x gradlew

COPY src/ src/

RUN ./gradlew build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/service-backbone-0.0.1-SNAPSHOT.jar"]