# Use the official OpenJDK 17 image
FROM openjdk:17-jdk-alpine

VOLUME /tmp

COPY target/customer-api.jar customer-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "customer-api.jar"]
