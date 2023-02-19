FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]