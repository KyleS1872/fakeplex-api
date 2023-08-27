FROM eclipse-temurin:11-jdk-alpine

VOLUME /tmp

COPY ./database-config.dat database-config.dat
COPY ./target/AntiSpam.jar app.jar

EXPOSE 8181

ENTRYPOINT ["java","-jar","/app.jar"]