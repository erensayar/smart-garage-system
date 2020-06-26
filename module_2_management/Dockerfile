FROM openjdk:8-jdk-alpine
RUN mkdir app
ADD ./target/plateRecogModule2-0.0.1-SNAPSHOT.jar app/plateRecogModule2-0.0.1-SNAPSHOT.jar
WORKDIR app
EXPOSE 9080
ENTRYPOINT ["java","-jar","plateRecogModule2-0.0.1-SNAPSHOT.jar","--server.port=9080"]