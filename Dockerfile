FROM openjdk:8-jdk-alpine

VOLUME /tmp
EXPOSE 8080
ADD build/libs/pessoas-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]