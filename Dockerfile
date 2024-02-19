FROM gradle:8-jdk17 AS build
ARG AWS_REGION=us-east-2
ARG AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
WORKDIR /app
COPY build.gradle .
COPY src ./src
RUN gradle clean build
RUN ls -l /app/build/libs/


FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build app/build/libs/app-0.0.1-SNAPSHOT.jar ./demo-app.jar
EXPOSE 8080
CMD ["java", "-jar", "demo-app.jar"]