FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/billing-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
Expose 9001
ENTRYPOINT ["java", "-jar", "app.jar"]
