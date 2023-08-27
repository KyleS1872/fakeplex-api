FROM eclipse-temurin:11-jdk-alpine

VOLUME /tmp

COPY ./target/ChatFilter.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]