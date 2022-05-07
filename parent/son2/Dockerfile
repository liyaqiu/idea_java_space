FROM openjdk:8-jdk-alpine
ARG filepath
COPY ${filepath} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]