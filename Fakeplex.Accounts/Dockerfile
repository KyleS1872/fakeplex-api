FROM eclipse-temurin:11-jdk-alpine

VOLUME /tmp

COPY ./database-config.dat database-config.dat
COPY ./target/Accounts.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]