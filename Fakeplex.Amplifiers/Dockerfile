FROM eclipse-temurin:11-jdk-alpine

VOLUME /tmp

COPY ./database-config.dat database-config.dat
COPY ./redis-config.dat redis-config.dat
COPY ./target/Amplifiers.jar app.jar

EXPOSE 7979

ENTRYPOINT ["java","-jar","/app.jar"]