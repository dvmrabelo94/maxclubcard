FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle /app/gradle
RUN chmod +x gradlew
RUN ./gradlew dependencies
COPY src /app/src
RUN ./gradlew build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/maxclubcard-1.0.0.jar"]