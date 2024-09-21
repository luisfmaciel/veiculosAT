FROM amazoncorretto:21
LABEL author="luisbarbosa"
WORKDIR /app
COPY eureka-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]