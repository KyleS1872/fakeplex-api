FROM eclipse-temurin:11-jdk-alpine

VOLUME /tmp

COPY ./target/Enderchest.jar app.jar

EXPOSE 8010

ENTRYPOINT ["java","-jar","/app.jar"]