FROM eclipse-temurin:11-jdk-alpine

VOLUME /tmp

COPY ./target/Banner.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]