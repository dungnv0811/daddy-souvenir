FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/daddy-souvenir-0.0.1-SNAPSHOT.war
ADD ${JAR_FILE} daddysouvenir.war
ENTRYPOINT ["java","-jar","/daddysouvenir.war"]